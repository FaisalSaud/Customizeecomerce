package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    ImageView item1ImageBar1;
    ImageView item2ImageBar1;
    ImageView item3ImageBar1;
    ImageView item4ImageBar1;
    ImageView item5ImageBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar1);

        createThisPage();// MOST TO BE IN THE FIRST !!!


    }

    public void createThisPage (){
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

        item1ImageBar1 = (ImageView) findViewById(R.id.imageView3BarItem11);
        item2ImageBar1 = (ImageView) findViewById(R.id.imageView3BarItem12);
        item3ImageBar1 = (ImageView) findViewById(R.id.imageView3BarItem13);
        item4ImageBar1 = (ImageView) findViewById(R.id.imageView3BarItem14);
        item5ImageBar1 = (ImageView) findViewById(R.id.imageView3BarItem15);

        switch (UserMainPage.itemsBar1.length){
            case 3: //item1

                break;
            case 6: //item2

                break;
            case 9: //item3

                break;
            case 12: //item4

                break;
            case 15: //item5

                break;
            default:
                Toast.makeText(this,"this is not posable Bar1 page",Toast.LENGTH_LONG).show();
                break;
        }
    }


}
