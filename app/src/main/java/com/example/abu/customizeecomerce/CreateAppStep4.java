package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CreateAppStep4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step4);
        final UserMainPage userMainPage = new UserMainPage();
        Button next = (Button) findViewById(R.id.NextStep4);
        CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox ShopingCart = (CheckBox) findViewById(R.id.checkBox);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep4.this, CreateAppStep5.class);
                startActivity(i);
                finish();

            }
        });

            PromoSlides.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        userMainPage.findViewById(R.id.textView42).setVisibility(View.VISIBLE);
                    } else {
                        userMainPage.findViewById(R.id.textView42).setVisibility(View.INVISIBLE);
                    }
                }
            });
            ShopingCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
               userMainPage.findViewById(R.id.imageButton).setVisibility(View.VISIBLE);
               }
                    else {
                userMainPage.findViewById(R.id.imageButton).setVisibility(View.INVISIBLE);
                 }
               }
            });

        }
    }
