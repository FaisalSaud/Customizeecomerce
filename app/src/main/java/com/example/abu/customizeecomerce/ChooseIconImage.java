package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;

public class ChooseIconImage extends AppCompatActivity {


    ImageView icon1;
    ImageView icon2;
    ImageView icon3;
    ImageView icon4;
    ImageView icon5;
    ImageView icon6;
    ImageView icon7;
    ImageView m;

    Drawable icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon_image);

        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
        icon4 = (ImageView) findViewById(R.id.icon4);
        icon5 = (ImageView) findViewById(R.id.icon5);
        icon6 = (ImageView) findViewById(R.id.icon6);
        icon7 = (ImageView) findViewById(R.id.icon7);



        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.food_icon2);

                //CreateAppStep1.img.setBackground(icon1.getDrawable());    // Or like this
                finish();

            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.bookshelf_icon);
                finish();

            }

        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.car_icon);
                finish();

            }

        });
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.computer_icon);
                finish();

            }

        });
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.flower_icon);
                finish();

            }

        });
        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.gamecontroller_icon);
                finish();

            }

        });
        icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                CreateAppStep1.img.setBackgroundResource(R.drawable.paintcan_icon);
                finish();

            }

        });

           }
}
