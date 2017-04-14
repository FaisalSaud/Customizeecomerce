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
        m = (ImageView) findViewById(R.id.imageView);



        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bd = (BitmapDrawable) icon1.getDrawable();
                Bitmap bitmap = (Bitmap) bd.getBitmap();

                Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                i.putExtra("bitmap", bitmap);
                setResult(2,i);
                //finish();

            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bd = (BitmapDrawable) icon2.getDrawable();
                Bitmap bitmap = (Bitmap) bd.getBitmap();

                Intent i = new Intent();
                i.putExtra("bitmap", bitmap);
                setResult(2,i);
                finish();
            }

        });

        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bd = (BitmapDrawable) icon6.getDrawable();
                Bitmap bitmap = (Bitmap) bd.getBitmap();

                Intent i = new Intent();
                i.putExtra("bitmap", bitmap);
                setResult(2,i);
                finish();
            }

        });




















        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChooseIconImage.this,"111111111",Toast.LENGTH_LONG).show();
                //icon1.buildDrawingCache();

                icon1.setDrawingCacheEnabled(true);
                Bitmap bit =icon1.getDrawingCache();

                //icon1.setImageBitmap(bit);
                try {
                    Intent i = new Intent(ChooseIconImage.this,CreateAppStep1.class);
                    //Intent i = new Intent();

                    i.putExtra("icon", bitmap);
                    //startActivityForResult(i, 2);
                    setResult(Activity.RESULT_FIRST_USER, i);
                }catch(Exception e){
                    Toast.makeText(ChooseIconImage.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                finish();
                //BitmapDrawable bd = (BitmapDrawable) icon1.;





//                try {
//                    icon = icon1.getDrawable();
//
//                    BitmapDrawable yourIcon = (BitmapDrawable) icon ;
//
//                    Toast.makeText(ChooseIconImage.this,"22222",Toast.LENGTH_LONG).show();
//
//
//
//                    Toast.makeText(ChooseIconImage.this,"333333333333",Toast.LENGTH_LONG).show();
//                    Bitmap b = ((BitmapDrawable) icon).getBitmap();
//
//                    Toast.makeText(ChooseIconImage.this,"4",Toast.LENGTH_LONG).show();
//                    i.putExtra("userIcon", b);
//                    Toast.makeText(ChooseIconImage.this,"5",Toast.LENGTH_LONG).show();
//                    setResult(2, i);
//                    Toast.makeText(ChooseIconImage.this,"6",Toast.LENGTH_LONG).show();
//                    finish();
//                    Toast.makeText(ChooseIconImage.this,"7",Toast.LENGTH_LONG).show();
//
//                }catch (Exception e){
//                    Toast.makeText(ChooseIconImage.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                }

              /*
                Bitmap b = ((BitmapDrawable) icon).getBitmap();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG,100,bs);
                i.putExtra("userIcon",bs.toByteArray());
                finish();
                */
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        icon7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
