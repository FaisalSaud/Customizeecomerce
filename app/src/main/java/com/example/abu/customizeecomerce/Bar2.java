package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Bar2 extends AppCompatActivity {

    TextView item1NameBar2;
    TextView item2NameBar2;
    TextView item3NameBar2;
    TextView item4NameBar2;
    TextView item5NameBar2;

    TextView item1DescBar2;
    TextView item2DescBar2;
    TextView item3DescBar2;
    TextView item4DescBar2;
    TextView item5DescBar2;

    TextView item1PriceBar2;
    TextView item2PriceBar2;
    TextView item3PriceBar2;
    TextView item4PriceBar2;
    TextView item5PriceBar2;

    EditText item1ImageBar2;
    EditText item2ImageBar2;
    EditText item3ImageBar2;
    EditText item4ImageBar2;
    EditText item5ImageBar2;

    CardView cardView21;
    CardView cardView22;
    CardView cardView23;
    CardView cardView24;
    CardView cardView25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar2);
        getSupportActionBar().setBackgroundDrawable(UserMainPage.background);
        item1NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem21);
        item2NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem22);
        item3NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem23);
        item4NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem24);
        item5NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem25);

        item1DescBar2 = (TextView) findViewById(R.id.itemDescBarItem21);
        item2DescBar2 = (TextView) findViewById(R.id.itemDescBarItem22);
        item3DescBar2 = (TextView) findViewById(R.id.itemDescBarItem23);
        item4DescBar2 = (TextView) findViewById(R.id.itemDescBarItem24);
        item5DescBar2 = (TextView) findViewById(R.id.itemDescBarItem25);

        item1PriceBar2 = (TextView) findViewById(R.id.itemPriceBarItem21);
        item2PriceBar2 = (TextView) findViewById(R.id.itemPriceBarItem22);
        item3PriceBar2 = (TextView) findViewById(R.id.itemPriceBarItem23);
        item4PriceBar2 = (TextView) findViewById(R.id.itemPriceBarItem24);
        item5PriceBar2 = (TextView) findViewById(R.id.itemPriceBarItem25);

        item1ImageBar2 = (EditText) findViewById(R.id.editTextBarItem21);
        item2ImageBar2 = (EditText) findViewById(R.id.editTextBarItem22);
        item3ImageBar2 = (EditText) findViewById(R.id.editTextBarItem23);
        item4ImageBar2 = (EditText) findViewById(R.id.editTextBarItem24);
        item5ImageBar2 = (EditText) findViewById(R.id.editTextBarItem25);

        cardView21 = (CardView) findViewById(R.id.cardView21);
        cardView22 = (CardView) findViewById(R.id.cardView22);
        cardView23 = (CardView) findViewById(R.id.cardView23);
        cardView24 = (CardView) findViewById(R.id.cardView24);
        cardView25 = (CardView) findViewById(R.id.cardView25);


        createThisPage();// MOST TO BE IN THE FIRST !!! test
    }


    public void createThisPage (){

        try {
            switch (UserMainPage.B2IteamC) {
                case 3 : //item1     0 -> item name       1 -> item description        3 -> item price
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar2.setText(UserMainPage.itemsBar2[1]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0]);

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.INVISIBLE);
                    cardView23.setVisibility(View.INVISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar2.setText(UserMainPage.itemsBar2[1]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0]);

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2DescBar2.setText(UserMainPage.itemsBar2[4]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1]);

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.INVISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar2.setText(UserMainPage.itemsBar2[1]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0]);

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2DescBar2.setText(UserMainPage.itemsBar2[4]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1]);

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3DescBar2.setText(UserMainPage.itemsBar2[7]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2]);

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar2.setText(UserMainPage.itemsBar2[1]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0]);

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2DescBar2.setText(UserMainPage.itemsBar2[4]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1]);

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3DescBar2.setText(UserMainPage.itemsBar2[7]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2]);

                    //forth item
                    item4NameBar2.setText(UserMainPage.itemsBar2[9]);
                    item4DescBar2.setText(UserMainPage.itemsBar2[10]);
                    item4PriceBar2.setText(UserMainPage.itemsBar2[11]);
                    item4ImageBar2.setBackground(UserMainPage.imgItemsBar2[3]);

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.VISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar2.setText(UserMainPage.itemsBar2[1]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0]);

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2DescBar2.setText(UserMainPage.itemsBar2[4]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1]);

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3DescBar2.setText(UserMainPage.itemsBar2[7]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2]);

                    //forth item
                    item4NameBar2.setText(UserMainPage.itemsBar2[9]);
                    item4DescBar2.setText(UserMainPage.itemsBar2[10]);
                    item4PriceBar2.setText(UserMainPage.itemsBar2[11]);
                    item4ImageBar2.setBackground(UserMainPage.imgItemsBar2[3]);

                    //fifth item
                    item5NameBar2.setText(UserMainPage.itemsBar2[12]);
                    item5DescBar2.setText(UserMainPage.itemsBar2[13]);
                    item5PriceBar2.setText(UserMainPage.itemsBar2[14]);
                    item5ImageBar2.setBackground(UserMainPage.imgItemsBar2[4]);

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.VISIBLE);
                    cardView25.setVisibility(View.VISIBLE);

                    break;
                default:
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this,"غير ممكن في صفحة العنوان الفرعي 2 ",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, "this is not possible Bar2 page", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addToPurchaseBasketItem21 (View view){

        UserMainPage.addItemToPurchaseBasket(item1NameBar2.getText().toString(),item1DescBar2.getText().toString(),
                item1PriceBar2.getText().toString(), item1ImageBar2.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem22 (View view){

        UserMainPage.addItemToPurchaseBasket(item2NameBar2.getText().toString(),item2DescBar2.getText().toString(),
                item2PriceBar2.getText().toString(), item2ImageBar2.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem23 (View view){

        UserMainPage.addItemToPurchaseBasket(item3NameBar2.getText().toString(),item3DescBar2.getText().toString(),
                item3PriceBar2.getText().toString(), item3ImageBar2.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem24 (View view){

        UserMainPage.addItemToPurchaseBasket(item4NameBar2.getText().toString(),item4DescBar2.getText().toString(),
                item4PriceBar2.getText().toString(), item4ImageBar2.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem25 (View view){

        UserMainPage.addItemToPurchaseBasket(item5NameBar2.getText().toString(),item5DescBar2.getText().toString(),
                item5PriceBar2.getText().toString(), item5ImageBar2.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }

    public void buyItem1 (View view){
        Intent i = new Intent(this,PaymentAndDelaviry.class);

        UserMainPage.itemNameFromBars = item1NameBar2.getText().toString();
        UserMainPage.itemDescriptionFromBars = item1DescBar2.getText().toString();
        UserMainPage.itemPriceFromBars = item1PriceBar2.getText().toString();
        UserMainPage.itemImageFromBars = item1ImageBar2.getBackground();

        UserMainPage.fromPurchaseB = false;
        startActivity(i);
    }
    public void buyItem2 (View view){
        Intent i = new Intent(this,PaymentAndDelaviry.class);

        UserMainPage.itemNameFromBars = item2NameBar2.getText().toString();
        UserMainPage.itemDescriptionFromBars = item2DescBar2.getText().toString();
        UserMainPage.itemPriceFromBars = item2PriceBar2.getText().toString();
        UserMainPage.itemImageFromBars = item2ImageBar2.getBackground();

        UserMainPage.fromPurchaseB = false;
        startActivity(i);
    }
    public void buyItem3 (View view){
        Intent i = new Intent(this,PaymentAndDelaviry.class);

        UserMainPage.itemNameFromBars = item3NameBar2.getText().toString();
        UserMainPage.itemDescriptionFromBars = item3DescBar2.getText().toString();
        UserMainPage.itemPriceFromBars = item3PriceBar2.getText().toString();
        UserMainPage.itemImageFromBars = item3ImageBar2.getBackground();

        UserMainPage.fromPurchaseB = false;
        startActivity(i);
    }
    public void buyItem4 (View view){
        Intent i = new Intent(this,PaymentAndDelaviry.class);

        UserMainPage.itemNameFromBars = item4NameBar2.getText().toString();
        UserMainPage.itemDescriptionFromBars = item4DescBar2.getText().toString();
        UserMainPage.itemPriceFromBars = item4PriceBar2.getText().toString();
        UserMainPage.itemImageFromBars = item4ImageBar2.getBackground();

        UserMainPage.fromPurchaseB = false;
        startActivity(i);
    }
    public void buyItem5 (View view){
        Intent i = new Intent(this,PaymentAndDelaviry.class);

        UserMainPage.itemNameFromBars = item5NameBar2.getText().toString();
        UserMainPage.itemDescriptionFromBars = item5DescBar2.getText().toString();
        UserMainPage.itemPriceFromBars = item5PriceBar2.getText().toString();
        UserMainPage.itemImageFromBars = item5ImageBar2.getBackground();

        UserMainPage.fromPurchaseB = false;
        startActivity(i);
    }
}
