package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Bar1 extends AppCompatActivity {

    TextView item1NameBar1;
    TextView item2NameBar1;
    TextView item3NameBar1;
    TextView item4NameBar1;
    TextView item5NameBar1;

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

        item1NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem11);
        item2NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem12);
        item3NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem13);
        item4NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem14);
        item5NameBar1 = (TextView) findViewById(R.id.itemNameTextBarItem15);

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
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.INVISIBLE);
                    cardView3.setVisibility(View.INVISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 6: //item2

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1].getBackground());

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.INVISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 9: //item3

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1].getBackground());

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2].getBackground());

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.INVISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 12: //item4

                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1].getBackground());

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2].getBackground());

                    //forth item
                    item4NameBar1.setText(UserMainPage.itemsBar1[9]);
                    item4PriceBar1.setText(UserMainPage.itemsBar1[11]);
                    item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3].getBackground());

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.VISIBLE);
                    cardView5.setVisibility(View.INVISIBLE);

                    break;
                case 15: //item5


                    //first item
                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0].getBackground());

                    //second item
                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1].getBackground());

                    //third item
                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2].getBackground());

                    //forth item
                    item4NameBar1.setText(UserMainPage.itemsBar1[9]);
                    item4PriceBar1.setText(UserMainPage.itemsBar1[11]);
                    item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3].getBackground());

                    //fifth item
                    item5NameBar1.setText(UserMainPage.itemsBar1[12]);
                    item5PriceBar1.setText(UserMainPage.itemsBar1[14]);
                    item5ImageBar1.setBackground(UserMainPage.imgItemsBar1[4].getBackground());

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    cardView3.setVisibility(View.VISIBLE);
                    cardView4.setVisibility(View.VISIBLE);
                    cardView5.setVisibility(View.VISIBLE);

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
