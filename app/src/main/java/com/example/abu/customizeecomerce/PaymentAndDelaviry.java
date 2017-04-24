package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PaymentAndDelaviry extends AppCompatActivity {
    public String[] ParraySpinner;
    public String[] DarraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_delaviry);
        try{
            Spinner Payment =(Spinner) findViewById(R.id.Paymentspinner);
            Spinner Delivery =(Spinner) findViewById(R.id.Deliveryspinner);
//          Payment.setPrompt("Select your payment methode needed");
//          delivery.setPrompt("Select your payment methode needed");
            this.ParraySpinner = new String[3];
            ParraySpinner[0]="Please Select";
          if (UserMainPage.Payment == 0) {
              ParraySpinner[1]="Mada";
              ParraySpinner[2]="";
          }
          else if (UserMainPage.Payment == 1) {
              ParraySpinner[1]="Cash";
              ParraySpinner[2]="";
          }
          else if (UserMainPage.Payment == 2) {
              ParraySpinner[1]="Mada";
              ParraySpinner[2]="Cash";
          }
            ArrayAdapter<String> Padapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ParraySpinner);
            Payment.setAdapter(Padapter);
            this.DarraySpinner = new String[3];
            ParraySpinner[0]="Please Select";
            if (UserMainPage.Delivery == 0) {
                DarraySpinner[1]="By hand";
                DarraySpinner[2]="";
            }
            else if (UserMainPage.Delivery == 1) {
                DarraySpinner[1]="In store";
                DarraySpinner[2]="";
            }
            else if (UserMainPage.Delivery == 2) {
                DarraySpinner[1]="By Hand";
                DarraySpinner[2]="In Store";
            }
            ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ParraySpinner);
            Delivery.setAdapter(Dadapter);
        }
        catch(Exception e){
            Toast.makeText(PaymentAndDelaviry.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
