package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PaymentAndDelaviry extends AppCompatActivity {
    public String[] arraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_delaviry);
        try{
            Spinner Payment =(Spinner) findViewById(R.id.Paymentspinner);
            Spinner delivery =(Spinner) findViewById(R.id.Deliveryspinner);
//          Payment.setPrompt("Select your payment methode needed");
//          delivery.setPrompt("Select your payment methode needed");
            this.arraySpinner = new String[3];
            arraySpinner[0]="Please Select";
          if (UserMainPage.Payment == 0) {
              arraySpinner[1]="Mada";
              arraySpinner[2]="";
          }
          else if (UserMainPage.Payment == 1) {
              arraySpinner[1]="Cash";
              arraySpinner[2]="";
          }
          else if (UserMainPage.Payment == 2) {
              arraySpinner[1]="Mada";
              arraySpinner[2]="Cash";
          }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arraySpinner);
            Payment.setAdapter(adapter);
        }
        catch(Exception e){
            Toast.makeText(PaymentAndDelaviry.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
