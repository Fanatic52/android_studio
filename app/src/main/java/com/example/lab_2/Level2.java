package com.example.lab_2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    private Dialog dialog;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //rename TV 'current name'
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);


        //round corners for images left and right
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);
        final ImageView img_left_background = (ImageView) findViewById(R.id.img_left_background);
        img_left_background.setClipToOutline(true);
        final ImageView img_right_background = (ImageView) findViewById(R.id.img_right_background);
        img_right_background.setClipToOutline(true);

        // path to left and right TV
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


        //expand the game to full screen and delete line of setting's phone
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        //call dialog window in start game
        dialog = new Dialog(this); // create dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //add opacity for dialog window
        dialog.setCancelable(false); // window cannot close with button 'back'

        //button for close dialog window
        TextView btnClose = (TextView) dialog.findViewById(R.id.btnclose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class); // create action for jump into another class
                    startActivity(intent); // use action
                    finish(); // close this class
                }
                catch (Exception e) {}
                dialog.dismiss(); // close dialog window
            }
        });

        //button for continue
        Button btnContinue = (Button) dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // close dialog window
            }
        });

        // show dialog window
        dialog.show();


        //button 'back'
        Button btnBack = (Button) findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class); // create action for jump into another class
                    startActivity(intent); // use action
                    finish(); // close this class
                }
                catch (Exception e) {}
            }
        });


        //array for processing game
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };


        //connect animation
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);


        //set random left/right image and left/right TV
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        text_left.setText(array.texts1[numLeft]);

        do {
            numRight = random.nextInt(10);
        } while (numRight == numLeft);
        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);


        //processing click on left image
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //if user touch screen
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false); // block right image
                    if(numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    }
                    else {
                        img_left.setImageResource(R.drawable.img_false );
                    }
                }
                //if user stop touch screen
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(numLeft > numRight) {
                        if(count < 20) {
                            count += 1;
                        }
                        //draw all progress in gray
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //draw correct progress in green
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    else {
                        if(count > 0) {
                            if(count == 1) {
                                count = 0;
                            }
                            else {
                                count -= 2;
                            }
                        }
                        //draw all progress in gray
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //draw correct progress in green
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    // exit from game if level is 20
                    if(count == 20) {

                    }
                    else {
                        //set random left/right image and left/right TV
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        do {
                            numRight = random.nextInt(10);
                        } while (numRight == numLeft);
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        //unblock right image
                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        //processing click on right image
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //if user touch screen
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false); // block right image
                    if(numRight > numLeft) {
                        img_right.setImageResource(R.drawable.img_true);
                    }
                    else {
                        img_right.setImageResource(R.drawable.img_false );
                    }
                }
                //if user stop touch screen
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(numRight > numLeft) {
                        if(count < 20) {
                            count += 1;
                        }
                        //draw all progress in gray
                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //draw correct progress in green
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    else {
                        if(count > 0) {
                            if(count == 1) {
                                count = 0;
                            }
                            else {
                                count -= 2;
                            }
                        }
                        //draw all progress in gray
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //draw correct progress in green
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    // exit from game if level is 20
                    if(count == 20) {

                    }
                    else {
                        //set random left/right image and left/right TV
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        do {
                            numRight = random.nextInt(10);
                        } while (numRight == numLeft);
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        //unblock right image
                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }

    // System button "back"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class); // create action for jump into another class
            startActivity(intent); // use action
            finish(); // close this class
        }
        catch (Exception e) {}
    }

}