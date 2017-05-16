package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseBasket extends AppCompatActivity {


    Button PurchaseOptions;


    CardView cardView1;
    CardView cardView2;
    CardView cardView3;

    TextView itemName1;
    TextView itemName2;
    TextView itemName3;

    TextView itemDescription1;
    TextView itemDescription2;
    TextView itemDescription3;

    TextView itemPrice1;
    TextView itemPrice2;
    TextView itemPrice3;

    EditText itemImage1;
    EditText itemImage2;
    EditText itemImage3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_basket);

        getSupportActionBar().setBackgroundDrawable(UserMainPage.background);

        cardView1 = (CardView) findViewById(R.id.viewCard11PB);
        cardView2 = (CardView) findViewById(R.id.viewCard12PB);
        cardView3 = (CardView) findViewById(R.id.viewCard13PB);

        itemName1 = (TextView) findViewById(R.id.itemNameTextPBItem11);
        itemName2 = (TextView) findViewById(R.id.itemNameTextPBItem12);
        itemName3 = (TextView) findViewById(R.id.itemNameTextPBItem13);

        itemDescription1 = (TextView) findViewById(R.id.itemDescPurchaseBasItem11);
        itemDescription2 = (TextView) findViewById(R.id.itemDescPurchaseBasItem12);
        itemDescription3 = (TextView) findViewById(R.id.itemDescPurchaseBasItem13);

        itemPrice1 = (TextView) findViewById(R.id.itemPricePBItem11);
        itemPrice2 = (TextView) findViewById(R.id.itemPricePBItem12);
        itemPrice3 = (TextView) findViewById(R.id.itemPricePBItem13);

        itemImage1 = (EditText) findViewById(R.id.editTextPBItem11);
        itemImage2 = (EditText) findViewById(R.id.editTextPBItem12);
        itemImage3 = (EditText) findViewById(R.id.editTextPBItem13);


        createThisPage();

        PurchaseOptions = (Button) findViewById(R.id.purchaseOptionsButton);

    }

    public void toPurchaseOptions(View view) {

        try {
            Intent i = new Intent(PurchaseBasket.this, PaymentAndDelaviry.class);
            UserMainPage.fromPurchaseB = true;
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(PurchaseBasket.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void createThisPage() {

        switch (UserMainPage.purchaseBasketCounter) {

            case 0:
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);

                break;
            case 3:
                itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                itemPrice1.setText(UserMainPage.purchaseBasketItems[2]);
                itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);

                cardView1.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);

                break;
            case 6:
                itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                itemPrice1.setText(UserMainPage.purchaseBasketItems[2]);
                itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);

                itemName2.setText(UserMainPage.purchaseBasketItems[3]);
                itemDescription2.setText(UserMainPage.purchaseBasketItems[4]);
                itemPrice2.setText(UserMainPage.purchaseBasketItems[5]);
                itemImage2.setBackground(UserMainPage.purchaseBasketItemsImages[1]);

                cardView1.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.VISIBLE);
                cardView3.setVisibility(View.GONE);

                break;
            case 9:
                itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                itemPrice1.setText(UserMainPage.purchaseBasketItems[2]);
                itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);

                itemName2.setText(UserMainPage.purchaseBasketItems[3]);
                itemDescription2.setText(UserMainPage.purchaseBasketItems[4]);
                itemPrice2.setText(UserMainPage.purchaseBasketItems[5]);
                itemImage2.setBackground(UserMainPage.purchaseBasketItemsImages[1]);

                itemName3.setText(UserMainPage.purchaseBasketItems[6]);
                itemDescription3.setText(UserMainPage.purchaseBasketItems[7]);
                itemPrice3.setText(UserMainPage.purchaseBasketItems[8]);
                itemImage3.setBackground(UserMainPage.purchaseBasketItemsImages[2]);


                cardView1.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.VISIBLE);
                cardView3.setVisibility(View.VISIBLE);

                break;
            default:
                Toast.makeText(this, "This is impossible to display 'PurchaseBasket'", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeFirstItem (View view){


        try {
            UserMainPage.purchaseBasketItems[0] = UserMainPage.purchaseBasketItems[3];
            UserMainPage.purchaseBasketItems[1] = UserMainPage.purchaseBasketItems[4];
            UserMainPage.purchaseBasketItems[2] = UserMainPage.purchaseBasketItems[5];
            UserMainPage.purchaseBasketItemsImages[0] = UserMainPage.purchaseBasketItemsImages[1];

            UserMainPage.purchaseBasketItems[3] = UserMainPage.purchaseBasketItems[6];
            UserMainPage.purchaseBasketItems[4] = UserMainPage.purchaseBasketItems[7];
            UserMainPage.purchaseBasketItems[5] = UserMainPage.purchaseBasketItems[8];
            UserMainPage.purchaseBasketItemsImages[1] = UserMainPage.purchaseBasketItemsImages[2];

            UserMainPage.purchaseBasketItems[6] = null;
            UserMainPage.purchaseBasketItems[7] = null;
            UserMainPage.purchaseBasketItems[8] = null;
            UserMainPage.purchaseBasketItemsImages[2] = null;
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        UserMainPage.purchaseBasketCounter = UserMainPage.purchaseBasketCounter - 3;
        createThisPage();
    }

    public void removeSecondItem (View view){

        try{
            UserMainPage.purchaseBasketItems[3] = UserMainPage.purchaseBasketItems[6];
            UserMainPage.purchaseBasketItems[4] = UserMainPage.purchaseBasketItems[7];
            UserMainPage.purchaseBasketItems[5] = UserMainPage.purchaseBasketItems[8];
            UserMainPage.purchaseBasketItemsImages[1] = UserMainPage.purchaseBasketItemsImages[2];

            UserMainPage.purchaseBasketItems[6] = null;
            UserMainPage.purchaseBasketItems[7] = null;
            UserMainPage.purchaseBasketItems[8] = null;
            UserMainPage.purchaseBasketItemsImages[2] = null;
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        UserMainPage.purchaseBasketCounter = UserMainPage.purchaseBasketCounter - 3;
        createThisPage();
    }

    public void removeThirdItem(View view){

        try {
            UserMainPage.purchaseBasketItems[6] = null;
            UserMainPage.purchaseBasketItems[7] = null;
            UserMainPage.purchaseBasketItems[8] = null;
            UserMainPage.purchaseBasketItemsImages[2] = null;
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        UserMainPage.purchaseBasketCounter = UserMainPage.purchaseBasketCounter - 3;
        createThisPage();
    }
}