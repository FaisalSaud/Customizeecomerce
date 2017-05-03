package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class StartPage extends AppCompatActivity {

    public static int  Try=0;
    public static Button Create;
    Button Login;
    Button AboutUs;
    Button AasA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starte_page);
        Create = (Button) findViewById(R.id.CreateAccount);
        Login = (Button) findViewById(R.id.Login);
        AboutUs = (Button) findViewById(R.id.AboutUs);
        AasA = (Button) findViewById(R.id.button11);
        //ImageButton purchaseBasket = (ImageButton) findViewById(R.id.PurchaseBasket);
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, CreateAccount.class);
                startActivity(i);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, Login.class);
                startActivity(i);
            }
        });
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, AboutUs.class);
                startActivity(i);
            }
        });
        AasA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartPage.this, MainActivity.class);
                startActivity(i);
            }
        });
//        purchaseBasket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(StartPage.this, PurchaseBasket.class);
//                startActivity(i);
//            }
//        });
    }
}



