package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CreateAppStep4 extends AppCompatActivity {
    CheckBox PromoSlides;
    CheckBox ShopingCart;


    public static Activity step4;//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step4);

        step4=this;//test

        Button next = (Button) findViewById(R.id.NextStep4);
        PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
         ShopingCart = (CheckBox) findViewById(R.id.checkBox);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep4.this, CreateAppStep5.class);
                putOnMainPage();
                startActivity(i);
                if(UserMainPage.isCom)
                finish();

            }
        });

        }
    public void putOnMainPage(){
        if (PromoSlides.isChecked())
            UserMainPage.promoSlide.setVisibility(View.VISIBLE);
        else
            UserMainPage.promoSlide.setVisibility(View.INVISIBLE);


        if(ShopingCart.isChecked())
            UserMainPage.ShoppingCart.setVisibility(View.VISIBLE);
        else
            UserMainPage.ShoppingCart.setVisibility(View.INVISIBLE);
    }

    }
