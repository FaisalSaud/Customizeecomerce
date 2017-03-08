package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starte_page);
        Button Create = (Button) findViewById(R.id.CreateAccount);
        Button Login = (Button) findViewById(R.id.Login);
        Button AboutUs = (Button) findViewById(R.id.AboutUs);
        ImageButton purchaseBasket = (ImageButton) findViewById(R.id.PurchaseBasket);
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
//        purchaseBasket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(StartPage.this, PurchaseBasket.class);
//                startActivity(i);
//            }
//        });
    }
}



