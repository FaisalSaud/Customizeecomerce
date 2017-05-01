package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bar2 extends AppCompatActivity {

    TextView item1NameBar2;
    TextView item2NameBar2;
    TextView item3NameBar2;
    TextView item4NameBar2;
    TextView item5NameBar2;

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

        item1NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem21);
        item2NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem22);
        item3NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem23);
        item4NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem24);
        item5NameBar2 = (TextView) findViewById(R.id.itemNameTextBarItem25);

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
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0].getBackground());

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.INVISIBLE);
                    cardView23.setVisibility(View.INVISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0].getBackground());

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1].getBackground());

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.INVISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0].getBackground());

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1].getBackground());

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2].getBackground());

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.INVISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0].getBackground());

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1].getBackground());

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2].getBackground());

                    //forth item
                    item4NameBar2.setText(UserMainPage.itemsBar2[9]);
                    item4PriceBar2.setText(UserMainPage.itemsBar2[11]);
                    item4ImageBar2.setBackground(UserMainPage.imgItemsBar2[3].getBackground());

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.VISIBLE);
                    cardView25.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar2.setText(UserMainPage.itemsBar2[0]);
                    item1PriceBar2.setText(UserMainPage.itemsBar2[2]);
                    item1ImageBar2.setBackground(UserMainPage.imgItemsBar2[0].getBackground());

                    //second item
                    item2NameBar2.setText(UserMainPage.itemsBar2[3]);
                    item2PriceBar2.setText(UserMainPage.itemsBar2[5]);
                    item2ImageBar2.setBackground(UserMainPage.imgItemsBar2[1].getBackground());

                    //third item
                    item3NameBar2.setText(UserMainPage.itemsBar2[6]);
                    item3PriceBar2.setText(UserMainPage.itemsBar2[8]);
                    item3ImageBar2.setBackground(UserMainPage.imgItemsBar2[2].getBackground());

                    //forth item
                    item4NameBar2.setText(UserMainPage.itemsBar2[9]);
                    item4PriceBar2.setText(UserMainPage.itemsBar2[11]);
                    item4ImageBar2.setBackground(UserMainPage.imgItemsBar2[3].getBackground());

                    //fifth item
                    item5NameBar2.setText(UserMainPage.itemsBar2[12]);
                    item5PriceBar2.setText(UserMainPage.itemsBar2[14]);
                    item5ImageBar2.setBackground(UserMainPage.imgItemsBar2[4].getBackground());

                    cardView21.setVisibility(View.VISIBLE);
                    cardView22.setVisibility(View.VISIBLE);
                    cardView23.setVisibility(View.VISIBLE);
                    cardView24.setVisibility(View.VISIBLE);
                    cardView25.setVisibility(View.VISIBLE);

                    break;
                default:
                    Toast.makeText(this, "this is not posable Bar1 page", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
