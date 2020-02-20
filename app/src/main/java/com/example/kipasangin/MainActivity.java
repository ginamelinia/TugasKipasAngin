package com.example.kipasangin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;

    final int SPEED[] = {0,5000,3000,1000,900};

    GradientDrawable gd = new GradientDrawable();

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        switchButton = (Switch) findViewById(R.id.light);
        imageView = (ImageView) findViewById(R.id.imageView);

        rotateAnimator=ObjectAnimator.ofFloat(imageView,"rotation",0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked() == true) {
                    rotateAnimator.setDuration(SPEED[1]);
                    rotateAnimator.start();
                } else {
                    rotateAnimator.end();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser && toggleButton.isChecked() ){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchButton.isChecked()){
                    gd.setColors(new int[]{Color.BLUE, Color.TRANSPARENT});
                    imageView.setBackground(gd);
                }else{
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }


}
