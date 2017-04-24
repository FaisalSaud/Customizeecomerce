package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseBackground extends AppCompatActivity {


    ImageView orange;
    ImageView blue;
    ImageView green;
    ImageView red;
    ImageView pur;
    ImageView black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_background);

        orange = (ImageView) findViewById(R.id.Background1Orange);
        blue = (ImageView) findViewById(R.id.Background2Blue);
        green = (ImageView) findViewById(R.id.Background3Green);
        red = (ImageView) findViewById(R.id.Background4Red);
        pur = (ImageView) findViewById(R.id.Background5P);
        black = (ImageView) findViewById(R.id.Background6Black);


        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(orange.getBackground());
                finish();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(blue.getBackground());
                finish();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(green.getBackground());
                finish();
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(red.getBackground());
                finish();
            }
        });

        pur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(pur.getBackground());
                finish();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAppStep2.backgroundImg.setBackground(black.getBackground());
                finish();
            }
        });

    }
}
