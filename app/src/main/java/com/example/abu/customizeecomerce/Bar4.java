package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bar4 extends AppCompatActivity {
    TextView item1NameBar4;
    TextView item2NameBar4;
    TextView item3NameBar4;
    TextView item4NameBar4;
    TextView item5NameBar4;

    TextView item1DescBar4;
    TextView item2DescBar4;
    TextView item3DescBar4;
    TextView item4DescBar4;
    TextView item5DescBar4;

    TextView item1PriceBar4;
    TextView item2PriceBar4;
    TextView item3PriceBar4;
    TextView item4PriceBar4;
    TextView item5PriceBar4;

    EditText item1ImageBar4;
    EditText item2ImageBar4;
    EditText item3ImageBar4;
    EditText item4ImageBar4;
    EditText item5ImageBar4;

    CardView cardView41;
    CardView cardView42;
    CardView cardView43;
    CardView cardView44;
    CardView cardView45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar4);

        item1NameBar4 = (TextView) findViewById(R.id.itemNameTextBarItem41);
        item2NameBar4 = (TextView) findViewById(R.id.itemNameTextBarItem42);
        item3NameBar4 = (TextView) findViewById(R.id.itemNameTextBarItem43);
        item4NameBar4 = (TextView) findViewById(R.id.itemNameTextBarItem44);
        item5NameBar4 = (TextView) findViewById(R.id.itemNameTextBarItem45);

        item1DescBar4 = (TextView) findViewById(R.id.itemDescBarItem41);
        item2DescBar4 = (TextView) findViewById(R.id.itemDescBarItem42);
        item3DescBar4 = (TextView) findViewById(R.id.itemDescBarItem43);
        item4DescBar4 = (TextView) findViewById(R.id.itemDescBarItem44);
        item5DescBar4 = (TextView) findViewById(R.id.itemDescBarItem45);

        item1PriceBar4 = (TextView) findViewById(R.id.itemPriceBarItem41);
        item2PriceBar4 = (TextView) findViewById(R.id.itemPriceBarItem42);
        item3PriceBar4 = (TextView) findViewById(R.id.itemPriceBarItem43);
        item4PriceBar4 = (TextView) findViewById(R.id.itemPriceBarItem44);
        item5PriceBar4 = (TextView) findViewById(R.id.itemPriceBarItem45);

        item1ImageBar4 = (EditText) findViewById(R.id.editTextBarItem41);
        item2ImageBar4 = (EditText) findViewById(R.id.editTextBarItem42);
        item3ImageBar4 = (EditText) findViewById(R.id.editTextBarItem43);
        item4ImageBar4 = (EditText) findViewById(R.id.editTextBarItem44);
        item5ImageBar4 = (EditText) findViewById(R.id.editTextBarItem45);

        cardView41 = (CardView) findViewById(R.id.cardView41);
        cardView42 = (CardView) findViewById(R.id.cardView42);
        cardView43 = (CardView) findViewById(R.id.cardView43);
        cardView44 = (CardView) findViewById(R.id.cardView44);
        cardView45 = (CardView) findViewById(R.id.cardView45);


        createThisPage();// MOST TO BE IN THE FIRST !!! test
    }


    public void createThisPage (){

        try {
            switch (UserMainPage.B2IteamC) {
                case 3 : //item1     0 -> item name       1 -> item description        3 -> item price
                    item1NameBar4.setText(UserMainPage.itemsBar4[0]);
                    item1DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item1PriceBar4.setText(UserMainPage.itemsBar4[2]);
                    item1ImageBar4.setBackground(UserMainPage.imgItemsBar4[0]);

                    cardView41.setVisibility(View.VISIBLE);
                    cardView42.setVisibility(View.INVISIBLE);
                    cardView43.setVisibility(View.INVISIBLE);
                    cardView44.setVisibility(View.INVISIBLE);
                    cardView45.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar4.setText(UserMainPage.itemsBar4[0]);
                    item1DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item1PriceBar4.setText(UserMainPage.itemsBar4[2]);
                    item1ImageBar4.setBackground(UserMainPage.imgItemsBar4[0]);

                    //second item
                    item2NameBar4.setText(UserMainPage.itemsBar4[3]);
                    item2DescBar4.setText(UserMainPage.itemsBar4[4]);
                    item2PriceBar4.setText(UserMainPage.itemsBar4[5]);
                    item2ImageBar4.setBackground(UserMainPage.imgItemsBar4[1]);

                    cardView41.setVisibility(View.VISIBLE);
                    cardView42.setVisibility(View.VISIBLE);
                    cardView43.setVisibility(View.INVISIBLE);
                    cardView44.setVisibility(View.INVISIBLE);
                    cardView45.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar4.setText(UserMainPage.itemsBar4[0]);
                    item1DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item1PriceBar4.setText(UserMainPage.itemsBar4[2]);
                    item1ImageBar4.setBackground(UserMainPage.imgItemsBar4[0]);

                    //second item
                    item2NameBar4.setText(UserMainPage.itemsBar4[3]);
                    item2DescBar4.setText(UserMainPage.itemsBar4[4]);
                    item2PriceBar4.setText(UserMainPage.itemsBar4[5]);
                    item2ImageBar4.setBackground(UserMainPage.imgItemsBar4[1]);

                    //third item
                    item3NameBar4.setText(UserMainPage.itemsBar4[6]);
                    item3DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item3PriceBar4.setText(UserMainPage.itemsBar4[8]);
                    item3ImageBar4.setBackground(UserMainPage.imgItemsBar4[2]);

                    cardView41.setVisibility(View.VISIBLE);
                    cardView42.setVisibility(View.VISIBLE);
                    cardView43.setVisibility(View.VISIBLE);
                    cardView44.setVisibility(View.INVISIBLE);
                    cardView45.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar4.setText(UserMainPage.itemsBar4[0]);
                    item1DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item1PriceBar4.setText(UserMainPage.itemsBar4[2]);
                    item1ImageBar4.setBackground(UserMainPage.imgItemsBar4[0]);

                    //second item
                    item2NameBar4.setText(UserMainPage.itemsBar4[3]);
                    item2DescBar4.setText(UserMainPage.itemsBar4[4]);
                    item2PriceBar4.setText(UserMainPage.itemsBar4[5]);
                    item2ImageBar4.setBackground(UserMainPage.imgItemsBar4[1]);

                    //third item
                    item3NameBar4.setText(UserMainPage.itemsBar4[6]);
                    item3DescBar4.setText(UserMainPage.itemsBar4[7]);
                    item3PriceBar4.setText(UserMainPage.itemsBar4[8]);
                    item3ImageBar4.setBackground(UserMainPage.imgItemsBar4[2]);

                    //forth item
                    item4NameBar4.setText(UserMainPage.itemsBar4[9]);
                    item4DescBar4.setText(UserMainPage.itemsBar4[10]);
                    item4PriceBar4.setText(UserMainPage.itemsBar4[11]);
                    item4ImageBar4.setBackground(UserMainPage.imgItemsBar4[3]);

                    cardView41.setVisibility(View.VISIBLE);
                    cardView42.setVisibility(View.VISIBLE);
                    cardView43.setVisibility(View.VISIBLE);
                    cardView44.setVisibility(View.VISIBLE);
                    cardView45.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar4.setText(UserMainPage.itemsBar2[0]);
                    item1DescBar4.setText(UserMainPage.itemsBar4[1]);
                    item1PriceBar4.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar4.setBackground(UserMainPage.imgItemsBar2[0]);

                    //second item
                    item2NameBar4.setText(UserMainPage.itemsBar4[3]);
                    item2DescBar4.setText(UserMainPage.itemsBar4[4]);
                    item2PriceBar4.setText(UserMainPage.itemsBar4[5]);
                    item2ImageBar4.setBackground(UserMainPage.imgItemsBar4[1]);

                    //third item
                    item3NameBar4.setText(UserMainPage.itemsBar4[6]);
                    item3DescBar4.setText(UserMainPage.itemsBar4[7]);
                    item3PriceBar4.setText(UserMainPage.itemsBar4[8]);
                    item3ImageBar4.setBackground(UserMainPage.imgItemsBar4[2]);

                    //forth item
                    item4NameBar4.setText(UserMainPage.itemsBar4[9]);
                    item4DescBar4.setText(UserMainPage.itemsBar4[10]);
                    item4PriceBar4.setText(UserMainPage.itemsBar4[11]);
                    item4ImageBar4.setBackground(UserMainPage.imgItemsBar4[3]);

                    //fifth item
                    item5NameBar4.setText(UserMainPage.itemsBar4[12]);
                    item5DescBar4.setText(UserMainPage.itemsBar4[13]);
                    item5PriceBar4.setText(UserMainPage.itemsBar4[14]);
                    item5ImageBar4.setBackground(UserMainPage.imgItemsBar4[4]);

                    cardView41.setVisibility(View.VISIBLE);
                    cardView42.setVisibility(View.VISIBLE);
                    cardView43.setVisibility(View.VISIBLE);
                    cardView44.setVisibility(View.VISIBLE);
                    cardView45.setVisibility(View.VISIBLE);

                    break;
                default:
                    Toast.makeText(this, "this is not possible Bar4 page", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void addToPurchaseBasketItem41 (View view){

        UserMainPage.addItemToPurchaseBasket(item1NameBar4.getText().toString(),item1DescBar4.getText().toString(),
                item1PriceBar4.getText().toString(), item1ImageBar4.getBackground());

        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem42 (View view){

        UserMainPage.addItemToPurchaseBasket(item2NameBar4.getText().toString(),item2DescBar4.getText().toString(),
                item2PriceBar4.getText().toString(), item2ImageBar4.getBackground());

        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem43 (View view){

        UserMainPage.addItemToPurchaseBasket(item3NameBar4.getText().toString(),item3DescBar4.getText().toString(),
                item3PriceBar4.getText().toString(), item3ImageBar4.getBackground());

        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem44 (View view){

        UserMainPage.addItemToPurchaseBasket(item4NameBar4.getText().toString(),item4DescBar4.getText().toString(),
                item4PriceBar4.getText().toString(), item4ImageBar4.getBackground());

        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
    public void addToPurchaseBasketItem45 (View view){

        UserMainPage.addItemToPurchaseBasket(item5NameBar4.getText().toString(),item5DescBar4.getText().toString(),
                item5PriceBar4.getText().toString(), item5ImageBar4.getBackground());

        Toast.makeText(this, "you enter a new item to Shopping Cart", Toast.LENGTH_SHORT).show();
    }
}
