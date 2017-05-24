package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static EditText slideShowPicture;   //to show the slide show :D

    //test
    static boolean fromPurchaseB;  //if the activity start from 'bar' or 'Purchase Basket'

    static String itemNameFromBars;
    static String itemDescriptionFromBars;
    static String itemPriceFromBars;
    static Drawable itemImageFromBars;
    static double itemPriceWithQuantityFromBars;
    //end test


    static String[] bars;
    static String[] itemsBar1;
    static String[] itemsBar2;
    static String[] itemsBar3;
    static String[] itemsBar4;
    static Drawable[] imgItemsBar1;
    static Drawable[] imgItemsBar2;
    static Drawable[] imgItemsBar3;
    static Drawable[] imgItemsBar4;
    public static ActionBar actionBar;
    static int B1IteamC;//= 0; //item count for bar1
    static int B2IteamC;//= 0;               //bar2
    static int B3IteamC;//= 0;               //bar3
    static int B4IteamC;//= 0;


    //test
    static String purchaseBasketItems[];
    static Drawable purchaseBasketItemsImages[];
    static int purchaseBasketCounter;
    static double itemPriceWithQuantity[];
    //end test

    public static Activity UserMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        UserMainPage = this;
        //background = (EditText) findViewById(R.id.userBackground);
        slideShowPicture = (EditText) findViewById(R.id.slideShowEditText);


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
        itemPriceWithQuantity = new double[3];
        //end test


        Toast.makeText(UserMainPage.this, "Start ump", Toast.LENGTH_SHORT).show();
        if (!isCom) {
            Intent i = new Intent(this, CreateAppStep1.class);
            moveTaskToBack(true);
            startActivity(i);

        }
        Bar1 = (Button) findViewById(R.id.bar1Button);
        Bar2 = (Button) findViewById(R.id.Bar2Button);
        Bar3 = (Button) findViewById(R.id.Bar3Button);
        Bar4 = (Button) findViewById(R.id.Bar4Button);
        getbars();
        //getitembar1();
        actionBar = getSupportActionBar();
        ShoppingCart = (ImageButton) findViewById(R.id.imageButton);
        promoSlide = (TextView) findViewById(R.id.textView42);
        Bar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitembar1();
                Intent i = new Intent(UserMainPage.this, Bar1.class);
                startActivity(i);
            }
        });
        Bar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitembar2();
                Intent i = new Intent(UserMainPage.this, Bar2.class);
                startActivity(i);
            }
        });
        Bar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitembar3();
                Intent i = new Intent(UserMainPage.this, Bar3.class);
                startActivity(i);
            }
        });
        Bar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitembar4();
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

        if (requestCode == 500) {
            finish();
        }
    }

    //add item from bar1, bar2, bar3 or bar4  to Purchase Basket
    public static void addItemToPurchaseBasket(String itemName, String itemDescription, String itemPrice, Drawable itemImage) {

        if (purchaseBasketCounter >= 9) {
            if (Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(UserMainPage, "لقد بلغت الحد الأقصى في إضافة المنتجات", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(UserMainPage, "YOU HAVE REACH THE MAXIMUM NUMBER OF ITEMS", Toast.LENGTH_SHORT).show();
            return;
        }
//        for (int i = purchaseBasketCounter ; i < purchaseBasketItems.length ; i++){
//            if (purchaseBasketItems[i] != null )
//        }

        try {
            purchaseBasketItems[purchaseBasketCounter++] = itemName;
            purchaseBasketItems[purchaseBasketCounter++] = itemDescription;
            purchaseBasketItems[purchaseBasketCounter++] = itemPrice;
            purchaseBasketItemsImages[(purchaseBasketCounter - 1) / 3] = itemImage;
        } catch (Exception e) {
            Toast.makeText(UserMainPage, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return;
    }

    public static void setUMPBackground(Drawable d) {
        background = d;
        actionBar.setBackgroundDrawable(d);
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
        } else {
            if (Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(UserMainPage.this, "سلة التسوق غير مفعلة", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(UserMainPage.this, "Shopping cart not activated", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getbars() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save bar1");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null) {
                stringBuffer.append(Message + "");

            }
            String[] bars = stringBuffer.toString().split(",");
            if (bars.length == 4) {
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setText(bars[2]);
                Bar4.setText(bars[3]);
            } else if (bars.length == 3) {
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setText(bars[2]);
                Bar4.setVisibility(View.INVISIBLE);
            } else if (bars.length == 2) {
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setVisibility(View.INVISIBLE);
                Bar4.setVisibility(View.INVISIBLE);
            } else {
                Bar1.setText(bars[0]);
                Bar2.setVisibility(View.INVISIBLE);
                Bar3.setVisibility(View.INVISIBLE);
                Bar4.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            Toast.makeText(UserMainPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void getitembar1() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save itembar1");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null) {
                stringBuffer.append(Message + "");

            }
            String[] bars = stringBuffer.toString().split(",");
               if(bars.length>=3 && !bars[0].equals("null")){
                   itemsBar1[0]=bars[0];
                   itemsBar1[1]=bars[1];
                   itemsBar1[2]=bars[2];
                   B1IteamC=3;
               }
            if(bars.length>=6 && !bars[3].equals("null")){
                itemsBar1[3]=bars[3];
                itemsBar1[4]=bars[4];
                itemsBar1[5]=bars[5];
                B1IteamC=6;
            }
            if(bars.length>=9&& !bars[6].equals("null")){
                itemsBar1[6]=bars[6];
                itemsBar1[7]=bars[7];
                itemsBar1[8]=bars[8];
                B1IteamC=9;
            }
            if(bars.length>=12 && !bars[9].equals("null")){
                itemsBar1[9]=bars[9];
                itemsBar1[10]=bars[10];
                itemsBar1[11]=bars[11];
                B1IteamC=12;
            }
            if(bars.length==15&& !bars[12].equals("null")){
                itemsBar1[12]=bars[12];
                itemsBar1[13]=bars[13];
                itemsBar1[14]=bars[14];
                B1IteamC=15;
            }
        } catch (Exception e) {
            Toast.makeText(UserMainPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void getitembar2() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save itembar2");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null) {
                stringBuffer.append(Message + "");

            }
            String[] bars = stringBuffer.toString().split(",");
            if(bars.length>=3 && !bars[0].equals("null")){
                itemsBar2[0]=bars[0];
                itemsBar2[1]=bars[1];
                itemsBar2[2]=bars[2];
                B2IteamC=3;
            }
            if(bars.length>=6 && !bars[3].equals("null")){
                itemsBar2[3]=bars[3];
                itemsBar2[4]=bars[4];
                itemsBar2[5]=bars[5];
                B2IteamC=6;
            }
            if(bars.length>=9&& !bars[6].equals("null")){
                itemsBar2[6]=bars[6];
                itemsBar2[7]=bars[7];
                itemsBar2[8]=bars[8];
                B2IteamC=9;
            }
            if(bars.length>=12 && !bars[9].equals("null")){
                itemsBar2[9]=bars[9];
                itemsBar2[10]=bars[10];
                itemsBar2[11]=bars[11];
                B2IteamC=12;
            }
            if(bars.length==15&& !bars[12].equals("null")){
                itemsBar2[12]=bars[12];
                itemsBar2[13]=bars[13];
                itemsBar2[14]=bars[14];
                B2IteamC=15;
            }
        } catch (Exception e) {
            Toast.makeText(UserMainPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void getitembar3() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save itembar3");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null) {
                stringBuffer.append(Message + "");

            }
            String[] bars = stringBuffer.toString().split(",");
            if(bars.length>=3 && !bars[0].equals("null")){
                itemsBar3[0]=bars[0];
                itemsBar3[1]=bars[1];
                itemsBar3[2]=bars[2];
                B3IteamC=3;
            }
            if(bars.length>=6 && !bars[3].equals("null")){
                itemsBar3[3]=bars[3];
                itemsBar3[4]=bars[4];
                itemsBar3[5]=bars[5];
                B3IteamC=6;
            }
            if(bars.length>=9&& !bars[6].equals("null")){
                itemsBar3[6]=bars[6];
                itemsBar3[7]=bars[7];
                itemsBar3[8]=bars[8];
                B3IteamC=9;
            }
            if(bars.length>=12 && !bars[9].equals("null")){
                itemsBar3[9]=bars[9];
                itemsBar3[10]=bars[10];
                itemsBar3[11]=bars[11];
                B3IteamC=12;
            }
            if(bars.length==15&& !bars[12].equals("null")){
                itemsBar3[12]=bars[12];
                itemsBar3[13]=bars[13];
                itemsBar3[14]=bars[14];
                B3IteamC=15;
            }
        } catch (Exception e) {
            Toast.makeText(UserMainPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void getitembar4() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save itembar4");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null) {
                stringBuffer.append(Message + "");

            }
            String[] bars = stringBuffer.toString().split(",");
            if(bars.length>=3 && !bars[0].equals("null")){
                itemsBar4[0]=bars[0];
                itemsBar4[1]=bars[1];
                itemsBar4[2]=bars[2];
                B4IteamC=3;
            }
            if(bars.length>=6 && !bars[3].equals("null")){
                itemsBar4[3]=bars[3];
                itemsBar4[4]=bars[4];
                itemsBar4[5]=bars[5];
                B4IteamC=6;
            }
            if(bars.length>=9&& !bars[6].equals("null")){
                itemsBar4[6]=bars[6];
                itemsBar4[7]=bars[7];
                itemsBar4[8]=bars[8];
                B4IteamC=9;
            }
            if(bars.length>=12 && !bars[9].equals("null")){
                itemsBar4[9]=bars[9];
                itemsBar4[10]=bars[10];
                itemsBar4[11]=bars[11];
                B4IteamC=12;
            }
            if(bars.length==15&& !bars[12].equals("null")){
                itemsBar4[12]=bars[12];
                itemsBar4[13]=bars[13];
                itemsBar4[14]=bars[14];
                B4IteamC=15;
            }
        } catch (Exception e) {
            Toast.makeText(UserMainPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}

//    private void loadImageFromStorage(String path)
//    {
//
//        try {
//            File f=new File(path, "profile.jpg");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            setUMPBackground(new BitmapDrawable(getResources(),b));
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void intoFile (){
//
//        String a = null;
//        try {
//            FileOutputStream fileOutputStream =openFileOutput("save purchaseBasketItem",MODE_PRIVATE);
//
//            for (int i=0 ; i< purchaseBasketItems.length ; i++){
//                if(purchaseBasketItems[i] != null)
//                a += purchaseBasketItems[i]+",";
//            }
//            fileOutputStream.write(a.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("Exit the application")
//                .setMessage("are you sure you want to exit the app? \nit will delet your sopping cart!!")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        android.os.Process.killProcess(android.os.Process.myPid());
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }
//}
