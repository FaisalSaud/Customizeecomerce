package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PurchaseBasket extends AppCompatActivity {


    Button PurchaseOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_basket);

        PurchaseOptions = (Button) findViewById(R.id.purchaseOptionsButton);

    }

    public void toPurchaseOptions (View view){

        try{
            Intent i = new Intent(PurchaseBasket.this, PaymentAndDelaviry.class);
            startActivity(i);
        }catch(Exception e){
            Toast.makeText(PurchaseBasket.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
