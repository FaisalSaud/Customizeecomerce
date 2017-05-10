package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Bar1 extends AppCompatActivity {

    TextView item1NameBar1;
    TextView item2NameBar1;
    TextView item3NameBar1;
    TextView item4NameBar1;
    TextView item5NameBar1;

    TextView item1DescBar1;
    TextView item2DescBar1;
    TextView item3DescBar1;
    TextView item4DescBar1;
    TextView item5DescBar1;

    TextView item1PriceBar1;
    TextView item2PriceBar1;
    TextView item3PriceBar1;
    TextView item4PriceBar1;
    TextView item5PriceBar1;

    EditText item1ImageBar1;
    EditText item2ImageBar1;
    EditText item3ImageBar1;
    EditText item4ImageBar1;
    EditText item5ImageBar1;

    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    CardView cardView4;
    CardView cardView5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar1);
        getSupportActionBar().setBackgroundDrawable(UserMainPage.background);
        item1NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem11);
        item2NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem12);
        item3NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem13);
        item4NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem14);
        item5NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem15);

        item1DescBar1 = (TextView) findViewById(R.id.ItemDescBarItem11);
        item2DescBar1 = (TextView) findViewById(R.id.ItemDescBarItem12);
        item3DescBar1 = (TextView) findViewById(R.id.ItemDescBarItem13);
        item4DescBar1 = (TextView) findViewById(R.id.ItemDescBarItem14);
        item5DescBar1 = (TextView) findViewById(R.id.ItemDescBarItem15);

        item1PriceBar1 = (TextView) findViewById(R.id.itemPriceBarItem11);
        item2PriceBar1 = (TextView) findViewById(R.id.itemPriceBarItem12);
        item3PriceBar1 = (TextView) findViewById(R.id.itemPriceBarItem13);
        item4PriceBar1 = (TextView) findViewById(R.id.itemPriceBarItem14);
        item5PriceBar1 = (TextView) findViewById(R.id.itemPriceBarItem15);

        item1ImageBar1 = (EditText) findViewById(R.id.editTextBarItem11);
        item2ImageBar1 = (EditText) findViewById(R.id.editTextBarItem12);
        item3ImageBar1 = (EditText) findViewById(R.id.editTextBarItem13);
        item4ImageBar1 = (EditText) findViewById(R.id.editTextBarItem14);
        item5ImageBar1 = (EditText) findViewById(R.id.editTextBarItem15);

        cardView1 = (CardView) findViewById(R.id.cardView11);
        cardView2 = (CardView) findViewById(R.id.cardView12);
        cardView3 = (CardView) findViewById(R.id.cardView13);
        cardView4 = (CardView) findViewById(R.id.cardView14);
        cardView5 = (CardView) findViewById(R.id.cardView15);



        createThisPage();// MOST TO BE IN THE FIRST !!! test


    }

    public void createThisPage (){

        int numberItem = UserMainPage.itemsBar1.length;

//        //test
//        try {
//
//            cardView1.setVisibility(View.VISIBLE);
//            cardView2.setVisibility(View.INVISIBLE);
//            cardView3.setVisibility(View.INVISIBLE);
//            cardView4.setVisibility(View.INVISIBLE);
//            cardView5.setVisibility(View.INVISIBLE);
//
//            item1NameBar1.setText(UserMainPage.itemsBar1[0]);
//            item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
//            item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());
//
////            Toast.makeText(this, "4" , Toast.LENGTH_LONG).show();
//
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage() , Toast.LENGTH_LONG).show();
//        }
//        //test

        try {
            switch (UserMainPage.B1IteamC) {
                case 3 : //item1     0 -> item name       1 -> item description        3 -> item price
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);



                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.INVISIBLE);
                    cardView3.setVisibility(View.INVISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);


                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2DescBar1.setText(UserMainPage.itemsBar1[4]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);



                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.INVISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2DescBar1.setText(UserMainPage.itemsBar1[2]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3DescBar1.setText(UserMainPage.itemsBar1[7]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2DescBar1.setText(UserMainPage.itemsBar1[4]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3DescBar1.setText(UserMainPage.itemsBar1[7]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);

                    //forth item
                    item4NameBar1.setText(UserMainPage.itemsBar1[9]);
                    item4DescBar1.setText(UserMainPage.itemsBar1[10]);
                    item4PriceBar1.setText(UserMainPage.itemsBar1[11]);
                    item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3]);

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.VISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2DescBar1.setText(UserMainPage.itemsBar1[4]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3DescBar1.setText(UserMainPage.itemsBar1[7]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);

                    //forth item
                    item4NameBar1.setText(UserMainPage.itemsBar1[9]);
                    item4DescBar1.setText(UserMainPage.itemsBar1[10]);
                    item4PriceBar1.setText(UserMainPage.itemsBar1[11]);
                    item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3]);

                    //fifth item
                    item5NameBar1.setText(UserMainPage.itemsBar1[12]);
                    item5DescBar1.setText(UserMainPage.itemsBar1[13]);
                    item5PriceBar1.setText(UserMainPage.itemsBar1[14]);
                    item5ImageBar1.setBackground(UserMainPage.imgItemsBar1[4]);

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.VISIBLE);
                    cardView5.setVisibility(View.VISIBLE);

                    break;
                default:
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this,"غير ممكن في صفحة العنوان الفرعي 1 ",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, "this is not possible Bar1 page", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addToPurchaseBasketItem11 (View view){

        UserMainPage.addItemToPurchaseBasket(item1NameBar1.getText().toString(),item1DescBar1.getText().toString(),
                item1PriceBar1.getText().toString(), item1ImageBar1.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem12 (View view){

        UserMainPage.addItemToPurchaseBasket(item2NameBar1.getText().toString(),item2DescBar1.getText().toString(),
                item2PriceBar1.getText().toString(), item2ImageBar1.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem13 (View view){

        UserMainPage.addItemToPurchaseBasket(item3NameBar1.getText().toString(),item3DescBar1.getText().toString(),
                item3PriceBar1.getText().toString(), item3ImageBar1.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem14 (View view){

        UserMainPage.addItemToPurchaseBasket(item4NameBar1.getText().toString(),item4DescBar1.getText().toString(),
                item4PriceBar1.getText().toString(), item4ImageBar1.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem15 (View view){

        UserMainPage.addItemToPurchaseBasket(item1NameBar1.getText().toString(),item1DescBar1.getText().toString(),
                item1PriceBar1.getText().toString(), item1ImageBar1.getBackground());
        if(Locale.getDefault().getLanguage().equals("ar"))
            Toast.makeText(this,"أدخلت منتجا جديدا في سلة التسوق",Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }

}
