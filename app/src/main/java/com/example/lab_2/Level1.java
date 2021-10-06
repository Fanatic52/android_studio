package com.example.lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Level1 extends AppCompatActivity {

    private Dialog dialog;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //rename TV 'current name'
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        //round corners for images left and right
        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

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
                    Intent intent = new Intent(Level1.this, GameLevels.class); // create action for jump into another class
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
                    Intent intent = new Intent(Level1.this, GameLevels.class); // create action for jump into another class
                    startActivity(intent); // use action
                    finish(); // close this class
                }
                catch (Exception e) {}
            }
        });
    }

    // System button "back"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class); // create action for jump into another class
            startActivity(intent); // use action
            finish(); // close this class
        }
        catch (Exception e) {}
    }

}