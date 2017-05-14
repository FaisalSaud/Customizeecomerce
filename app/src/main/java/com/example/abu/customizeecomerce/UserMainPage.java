package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class UserMainPage extends AppCompatActivity {
    public static Button Bar1;
    public static Button Bar2;
    public static Button Bar3;
    public static Button Bar4;
    public static Drawable background;
    public static TextView promoSlide;
    public static ImageButton ShoppingCart;
    public static Boolean isCom = true;
    public static int Payment;
    public static int Delivery;
    Drawable f;
    static String [] bars;
    static String [] itemsBar1;
    static String [] itemsBar2;
    static String [] itemsBar3;
    static String [] itemsBar4;
    static Drawable [] imgItemsBar1;
    static Drawable [] imgItemsBar2;
    static Drawable [] imgItemsBar3;
    static Drawable [] imgItemsBar4;
    static ActionBar actionBar;
    static int B1IteamC ;//= 0; //item count for bar1
    static int B2IteamC ;//= 0;               //bar2
    static int B3IteamC ;//= 0;               //bar3
    static int B4IteamC ;//= 0;
     int turnoff=1;


    //test
    static String purchaseBasketItems [];
    static Drawable purchaseBasketItemsImages [];
    static int purchaseBasketCounter;
    //end test

    public static Activity UserMainPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        UserMainPage=this;
        //background = (EditText) findViewById(R.id.userBackground);



        itemsBar1 = new String[15];
        itemsBar2 = new String[15];
        itemsBar3 = new String[15];
        itemsBar4 = new String[15];
        imgItemsBar1 = new Drawable[5];
        imgItemsBar2 = new Drawable[5];
        imgItemsBar3 = new Drawable[5];
        imgItemsBar4 = new Drawable[5];

        B1IteamC = 0;
        B2IteamC = 0;
        B3IteamC = 0;
        B4IteamC = 0;



        //test
        purchaseBasketItems = new String[9];
        purchaseBasketItemsImages = new Drawable[3];
        purchaseBasketCounter = 0;
        //end test


        Toast.makeText(UserMainPage.this, "Start ump" , Toast.LENGTH_SHORT).show();
        if(!isCom) {
            Intent i = new Intent(this,CreateAppStep1.class);
            moveTaskToBack(true);
            startActivity(i);

        }
        Bar1 = (Button) findViewById(R.id.bar1Button);
        Bar2 = (Button) findViewById(R.id.Bar2Button);
        Bar3 = (Button) findViewById(R.id.Bar3Button);
        Bar4 = (Button) findViewById(R.id.Bar4Button);

        actionBar = getSupportActionBar();
        ShoppingCart = (ImageButton) findViewById(R.id.imageButton);
        promoSlide = (TextView) findViewById(R.id.textView42);
        Bar1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent i = new Intent(UserMainPage.this, Bar1.class);
            startActivity(i);
            }
        });
        Bar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar2.class);
                startActivity(i);
            }
        });
        Bar3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar3.class);
                startActivity(i);
            }
        });
        Bar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar4.class);
                startActivity(i);
            }
        });
//        ShoppingCart.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(UserMainPage.this, PurchaseBasket.class);
//
////                Bundle b = new Bundle();
////                String storeName = b.getString("storeName");
////                b.getParcelable("storeIcon");
//
//                startActivity(i);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==500){
            finish();
        }
    }

    //add item from bar1, bar2, bar3 or bar4  to Purchase Basket
    public static void addItemToPurchaseBasket (String itemName , String itemDescription , String itemPrice , Drawable itemImage){

        if(purchaseBasketCounter >= 9) {
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(UserMainPage, "لقد بلغت الحد الأقصى في إضافة المنتجات", Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(UserMainPage, "YOU HAVE REACH THE MAXIMUM NUMBER OF ITEMS", Toast.LENGTH_SHORT).show();
            return ;
        }
//        for (int i = purchaseBasketCounter ; i < purchaseBasketItems.length ; i++){
//            if (purchaseBasketItems[i] != null )
//        }

        try {
            purchaseBasketItems[purchaseBasketCounter++] = itemName;
            purchaseBasketItems[purchaseBasketCounter++] = itemDescription;
            purchaseBasketItems[purchaseBasketCounter++] = itemPrice;
            purchaseBasketItemsImages[(purchaseBasketCounter - 1) / 3] = itemImage;
        }catch (Exception e){
            Toast.makeText(UserMainPage, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return ;
    }

    public static void setUMPBackground(Drawable d){
        background = d;
        actionBar.setBackgroundDrawable(background);
        //background.setVisibility(View.INVISIBLE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }
    //and this to handle actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.imageButton && CreateAppStep4.ShopingCart.isChecked()) {
            Intent i = new Intent(UserMainPage.this, PurchaseBasket.class);
            startActivity(i);
            return true;
        }
        else{
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(UserMainPage.this,"سلة التسوق غير مفعلة",Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(UserMainPage.this,"Shopping cart not activated",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
