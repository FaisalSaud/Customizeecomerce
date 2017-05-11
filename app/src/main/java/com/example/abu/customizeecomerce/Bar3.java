package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Bar3 extends AppCompatActivity {
    TextView item1NameBar3;
    TextView item2NameBar3;
    TextView item3NameBar3;
    TextView item4NameBar3;
    TextView item5NameBar3;

    TextView item1DescBar3;
    TextView item2DescBar3;
    TextView item3DescBar3;
    TextView item4DescBar3;
    TextView item5DescBar3;

    TextView item1PriceBar3;
    TextView item2PriceBar3;
    TextView item3PriceBar3;
    TextView item4PriceBar3;
    TextView item5PriceBar3;

    EditText item1ImageBar3;
    EditText item2ImageBar3;
    EditText item3ImageBar3;
    EditText item4ImageBar3;
    EditText item5ImageBar3;

    CardView cardView31;
    CardView cardView32;
    CardView cardView33;
    CardView cardView34;
    CardView cardView35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar3);
        getSupportActionBar().setBackgroundDrawable(UserMainPage.background);
        item1NameBar3 = (TextView) findViewById(R.id.itemNameTextBarItem31);
        item2NameBar3 = (TextView) findViewById(R.id.itemNameTextBarItem32);
        item3NameBar3 = (TextView) findViewById(R.id.itemNameTextBarItem33);
        item4NameBar3 = (TextView) findViewById(R.id.itemNameTextBarItem34);
        item5NameBar3 = (TextView) findViewById(R.id.itemNameTextBarItem35);

        item1DescBar3 = (TextView) findViewById(R.id.itemDescBarItem31);
        item2DescBar3 = (TextView) findViewById(R.id.itemDescBarItem32);
        item3DescBar3 = (TextView) findViewById(R.id.itemDescBarItem33);
        item4DescBar3 = (TextView) findViewById(R.id.itemDescBarItem34);
        item5DescBar3 = (TextView) findViewById(R.id.itemDescBarItem35);

        item1PriceBar3 = (TextView) findViewById(R.id.itemPriceBarItem31);
        item2PriceBar3 = (TextView) findViewById(R.id.itemPriceBarItem32);
        item3PriceBar3 = (TextView) findViewById(R.id.itemPriceBarItem33);
        item4PriceBar3 = (TextView) findViewById(R.id.itemPriceBarItem34);
        item5PriceBar3 = (TextView) findViewById(R.id.itemPriceBarItem35);

        item1ImageBar3 = (EditText) findViewById(R.id.editTextBarItem31);
        item2ImageBar3 = (EditText) findViewById(R.id.editTextBarItem32);
        item3ImageBar3 = (EditText) findViewById(R.id.editTextBarItem33);
        item4ImageBar3 = (EditText) findViewById(R.id.editTextBarItem34);
        item5ImageBar3 = (EditText) findViewById(R.id.editTextBarItem35);

        cardView31 = (CardView) findViewById(R.id.cardView31);
        cardView32 = (CardView) findViewById(R.id.cardView32);
        cardView33 = (CardView) findViewById(R.id.cardView33);
        cardView34 = (CardView) findViewById(R.id.cardView34);
        cardView35 = (CardView) findViewById(R.id.cardView35);


        createThisPage();// MOST TO BE IN THE FIRST !!! test
    }


    public void createThisPage (){

        try {
            switch (UserMainPage.B2IteamC) {
                case 3 : //item1     0 -> item name       1 -> item description        3 -> item price
                    item1NameBar3.setText(UserMainPage.itemsBar3[0]);
                    item1DescBar3.setText(UserMainPage.itemsBar3[1]);
                    item1PriceBar3.setText(UserMainPage.itemsBar3[2]);
                    item1ImageBar3.setBackground(UserMainPage.imgItemsBar3[0]);

                    cardView31.setVisibility(View.VISIBLE);
                    cardView32.setVisibility(View.INVISIBLE);
                    cardView33.setVisibility(View.INVISIBLE);
                    cardView34.setVisibility(View.INVISIBLE);
                    cardView35.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar3.setText(UserMainPage.itemsBar3[0]);
                    item1DescBar3.setText(UserMainPage.itemsBar3[1]);
                    item1PriceBar3.setText(UserMainPage.itemsBar3[2]);
                    item1ImageBar3.setBackground(UserMainPage.imgItemsBar3[0]);

                    //second item
                    item2NameBar3.setText(UserMainPage.itemsBar3[3]);
                    item2DescBar3.setText(UserMainPage.itemsBar3[4]);
                    item2PriceBar3.setText(UserMainPage.itemsBar3[5]);
                    item2ImageBar3.setBackground(UserMainPage.imgItemsBar3[1]);

                    cardView31.setVisibility(View.VISIBLE);
                    cardView32.setVisibility(View.VISIBLE);
                    cardView33.setVisibility(View.INVISIBLE);
                    cardView34.setVisibility(View.INVISIBLE);
                    cardView35.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar3.setText(UserMainPage.itemsBar3[0]);
                    item1DescBar3.setText(UserMainPage.itemsBar3[1]);
                    item1PriceBar3.setText(UserMainPage.itemsBar3[2]);
                    item1ImageBar3.setBackground(UserMainPage.imgItemsBar3[0]);

                    //second item
                    item2NameBar3.setText(UserMainPage.itemsBar3[3]);
                    item2DescBar3.setText(UserMainPage.itemsBar3[4]);
                    item2PriceBar3.setText(UserMainPage.itemsBar3[5]);
                    item2ImageBar3.setBackground(UserMainPage.imgItemsBar3[1]);

                    //third item
                    item3NameBar3.setText(UserMainPage.itemsBar3[6]);
                    item3DescBar3.setText(UserMainPage.itemsBar3[7]);
                    item3PriceBar3.setText(UserMainPage.itemsBar3[8]);
                    item3ImageBar3.setBackground(UserMainPage.imgItemsBar3[2]);

                    cardView31.setVisibility(View.VISIBLE);
                    cardView32.setVisibility(View.VISIBLE);
                    cardView33.setVisibility(View.VISIBLE);
                    cardView34.setVisibility(View.INVISIBLE);
                    cardView35.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar3.setText(UserMainPage.itemsBar3[0]);
                    item1DescBar3.setText(UserMainPage.itemsBar3[1]);
                    item1PriceBar3.setText(UserMainPage.itemsBar3[2]);
                    item1ImageBar3.setBackground(UserMainPage.imgItemsBar3[0]);

                    //second item
                    item2NameBar3.setText(UserMainPage.itemsBar3[3]);
                    item2DescBar3.setText(UserMainPage.itemsBar3[4]);
                    item2PriceBar3.setText(UserMainPage.itemsBar3[5]);
                    item2ImageBar3.setBackground(UserMainPage.imgItemsBar3[1]);

                    //third item
                    item3NameBar3.setText(UserMainPage.itemsBar3[6]);
                    item3DescBar3.setText(UserMainPage.itemsBar3[7]);
                    item3PriceBar3.setText(UserMainPage.itemsBar3[8]);
                    item3ImageBar3.setBackground(UserMainPage.imgItemsBar3[2]);

                    //forth item
                    item4NameBar3.setText(UserMainPage.itemsBar3[9]);
                    item4DescBar3.setText(UserMainPage.itemsBar3[10]);
                    item4PriceBar3.setText(UserMainPage.itemsBar3[11]);
                    item4ImageBar3.setBackground(UserMainPage.imgItemsBar3[3]);

                    cardView31.setVisibility(View.VISIBLE);
                    cardView32.setVisibility(View.VISIBLE);
                    cardView33.setVisibility(View.VISIBLE);
                    cardView34.setVisibility(View.VISIBLE);
                    cardView35.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar3.setText(UserMainPage.itemsBar3[0]);
                    item1DescBar3.setText(UserMainPage.itemsBar3[1]);
                    item1PriceBar3.setText(UserMainPage.itemsBar3[2]);
                    item1ImageBar3.setBackground(UserMainPage.imgItemsBar3[0]);

                    //second item
                    item2NameBar3.setText(UserMainPage.itemsBar3[3]);
                    item2DescBar3.setText(UserMainPage.itemsBar3[4]);
                    item2PriceBar3.setText(UserMainPage.itemsBar3[5]);
                    item2ImageBar3.setBackground(UserMainPage.imgItemsBar3[1]);

                    //third item
                    item3NameBar3.setText(UserMainPage.itemsBar3[6]);
                    item3DescBar3.setText(UserMainPage.itemsBar3[7]);
                    item3PriceBar3.setText(UserMainPage.itemsBar3[8]);
                    item3ImageBar3.setBackground(UserMainPage.imgItemsBar3[2]);

                    //forth item
                    item4NameBar3.setText(UserMainPage.itemsBar3[9]);
                    item4DescBar3.setText(UserMainPage.itemsBar3[10]);
                    item4PriceBar3.setText(UserMainPage.itemsBar3[11]);
                    item4ImageBar3.setBackground(UserMainPage.imgItemsBar3[3]);

                    //fifth item
                    item5NameBar3.setText(UserMainPage.itemsBar2[12]);
                    item5DescBar3.setText(UserMainPage.itemsBar3[13]);
                    item5PriceBar3.setText(UserMainPage.itemsBar3[14]);
                    item5ImageBar3.setBackground(UserMainPage.imgItemsBar3[4]);

                    cardView31.setVisibility(View.VISIBLE);
                    cardView32.setVisibility(View.VISIBLE);
                    cardView33.setVisibility(View.VISIBLE);
                    cardView34.setVisibility(View.VISIBLE);
                    cardView35.setVisibility(View.VISIBLE);

                    break;
                default:
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this,"غير ممكن في صفحة العنوان الفرعي 3 ",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, "this is not possible Bar3 page", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void addToPurchaseBasketItem31 (View view){

        UserMainPage.addItemToPurchaseBasket(item1NameBar3.getText().toString(),item1DescBar3.getText().toString(),
                item1PriceBar3.getText().toString(), item1ImageBar3.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem32 (View view){

        UserMainPage.addItemToPurchaseBasket(item2NameBar3.getText().toString(),item2DescBar3.getText().toString(),
                item2PriceBar3.getText().toString(), item2ImageBar3.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem33 (View view){

        UserMainPage.addItemToPurchaseBasket(item3NameBar3.getText().toString(),item3DescBar3.getText().toString(),
                item3PriceBar3.getText().toString(), item3ImageBar3.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem34 (View view){

        UserMainPage.addItemToPurchaseBasket(item4NameBar3.getText().toString(),item4DescBar3.getText().toString(),
                item4PriceBar3.getText().toString(), item4ImageBar3.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem35 (View view){

        UserMainPage.addItemToPurchaseBasket(item5NameBar3.getText().toString(),item5DescBar3.getText().toString(),
                item5PriceBar3.getText().toString(), item5ImageBar3.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
}
