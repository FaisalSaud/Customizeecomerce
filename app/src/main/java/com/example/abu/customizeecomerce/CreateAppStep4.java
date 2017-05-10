package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAppStep4 extends AppCompatActivity {
    CheckBox PromoSlides;
    static CheckBox ShopingCart;
    CheckBox Mada;
    CheckBox Cash;
    CheckBox InStore;
    CheckBox byHand;
    public static Activity step4;//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step4);
        step4=this;//test
        Button next = (Button) findViewById(R.id.NextStep4);
        PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        Mada = (CheckBox) findViewById(R.id.checkBox7);
        ShopingCart = (CheckBox) findViewById(R.id.checkBox);
        Cash = (CheckBox) findViewById(R.id.checkBox5);
        InStore = (CheckBox) findViewById(R.id.checkBox6);
        byHand = (CheckBox) findViewById(R.id.checkBox2);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep4.this, CreateAppStep5.class);
                putOnMainPage();
                if(UserMainPage.Payment >= 0 && UserMainPage.Delivery >= 0)
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

//        if(ShopingCart.isChecked())
//            UserMainPage.ShoppingCart.setVisibility(View.VISIBLE);
//        else
//            UserMainPage.ShoppingCart.setVisibility(View.INVISIBLE);
        if(Cash.isChecked() && Mada.isChecked()){
            UserMainPage.Payment = 2;
        }
        else if(Cash.isChecked() && !Mada.isChecked()){
            UserMainPage.Payment = 1;
        }
        else if(!Cash.isChecked() && Mada.isChecked()){
            UserMainPage.Payment = 0;
        }
        else{
            UserMainPage.Payment = -1;
            Toast.makeText(CreateAppStep4.this ,"Please Select a payment method" , Toast.LENGTH_SHORT).show();
        }
        if(InStore.isChecked() && byHand.isChecked()){
            UserMainPage.Delivery = 2;
        }
        else if(InStore.isChecked() && !byHand.isChecked()){
            UserMainPage.Delivery = 1;
        }
        else if(!InStore.isChecked() && byHand.isChecked()){
            UserMainPage.Delivery = 0;
        }
        else{
            UserMainPage.Delivery = -1;
            Toast.makeText(CreateAppStep4.this ,"Please Select a delaviry method" , Toast.LENGTH_SHORT).show();}
    }

    }
