package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class CreateAppStep4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step4);
        final UserMainPage userMainPage = new UserMainPage();
        Button next = (Button) findViewById(R.id.NextStep4);
        CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        //CheckBox PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep4.this, CreateAppStep5.class);
                startActivity(i);
            }
        });
        PromoSlides.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userMainPage.findViewById(R.id.textView42).setVisibility(View.VISIBLE);
            }
        });
    }
}
