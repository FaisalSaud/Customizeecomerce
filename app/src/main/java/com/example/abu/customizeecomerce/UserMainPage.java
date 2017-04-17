package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserMainPage extends AppCompatActivity {
    public static Button Bar1;
    public static Button Bar2;
    public static Button Bar3;
    public static Button Bar4;
    public static Boolean isCom=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        Toast.makeText(UserMainPage.this, "Start ump" , Toast.LENGTH_SHORT).show();
        if(!isCom) {
            Intent i = new Intent(this,CreateAppStep1.class);
            moveTaskToBack(true);
            startActivity(i);
        }
        Bar1 = (Button) findViewById(R.id.bar1Button);
        Bar2 = (Button) findViewById(R.id.Bar2Button);
        Bar3 = (Button) findViewById(R.id.Bar3Button);
        Bar4 = (Button) findViewById(R.id.Bar4Button);
        ImageButton ShoppingCart = (ImageButton) findViewById(R.id.imageButton);
        Bar1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent i = new Intent(UserMainPage.this, Bar1.class);
            startActivity(i);
            }
        });
        Bar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar2.class);
                startActivity(i);
            }
        });
        Bar3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar3.class);
                startActivity(i);
            }
        });
        Bar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar4.class);
                startActivity(i);
            }
        });
        ShoppingCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, PurchaseBasket.class);

//                Bundle b = new Bundle();
//                String storeName = b.getString("storeName");
//                b.getParcelable("storeIcon");

                startActivity(i);
            }
        });
    }
    }
