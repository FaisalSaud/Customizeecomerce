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
    public static Boolean isCom=false;
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


    static String [] bars;
    static String [] itemsBar1;
    static String [] itemsBar2;
    static String [] itemsBar3;
    static String [] itemsBar4;
    static Drawable [] imgItemsBar1;
    static Drawable [] imgItemsBar2;
    static Drawable [] imgItemsBar3;
    static Drawable [] imgItemsBar4;
    public static ActionBar actionBar;
    static int B1IteamC ;//= 0; //item count for bar1
    static int B2IteamC ;//= 0;               //bar2
    static int B3IteamC ;//= 0;               //bar3
    static int B4IteamC ;//= 0;


    //test
    static String purchaseBasketItems [];
    static Drawable purchaseBasketItemsImages [];
    static int purchaseBasketCounter;
    static double itemPriceWithQuantity[];
    //end test

    public static Activity UserMainPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        UserMainPage=this;
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
        }
        else{
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(UserMainPage.this,"سلة التسوق غير مفعلة",Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(UserMainPage.this,"Shopping cart not activated",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public  void getbars() {
        try {
            String Message;
            FileInputStream fileInputStream = openFileInput("save bar1");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();
            while ((Message=bufferedReader.readLine())!=null){
                stringBuffer.append(Message +"");

            }
            String[] bars=stringBuffer.toString().split(",");
            if(bars.length==4){
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setText(bars[2]);
                Bar4.setText(bars[3]);}
            else if (bars.length==3) {
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setText(bars[2]);
                Bar4.setVisibility(View.INVISIBLE);
            } else if (bars.length==2){
                Bar1.setText(bars[0]);
                Bar2.setText(bars[1]);
                Bar3.setVisibility(View.INVISIBLE);
                Bar4.setVisibility(View.INVISIBLE);
            } else{
                Bar1.setText(bars[0]);
                Bar2.setVisibility(View.INVISIBLE);
                Bar3.setVisibility(View.INVISIBLE);
                Bar4.setVisibility(View.INVISIBLE);
            }

        }
        catch (Exception e){
            Toast.makeText(UserMainPage.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
//    public void getitembar1() {
//        try {
//            String Message;
//            FileInputStream fileInputStream = openFileInput("save itembar1");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//            BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
//            StringBuffer stringBuffer =new StringBuffer();
//            while ((Message=bufferedReader.readLine())!=null){
//                stringBuffer.append(Message +"");
//
//            }
//            String[] bars=stringBuffer.toString().split(",");
//
//            switch (UserMainPage.B1IteamC) {
//                case 3 : //item1     0 -> item name       1 -> item description        3 -> item price
//                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
//                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
//                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
//                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);
//
//
//
//                    cardView1.setVisibility(View.VISIBLE);
//                    cardView2.setVisibility(View.INVISIBLE);
//                    cardView3.setVisibility(View.INVISIBLE);
//                    cardView4.setVisibility(View.INVISIBLE);
//                    cardView5.setVisibility(View.INVISIBLE);
//
//                    break;
//                case 6: //item2
//
//                    //first item
//                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
//                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
//                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
//                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);
//
//
//                    //second item
//                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
//                    item2DescBar1.setText(UserMainPage.itemsBar1[4]);
//                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
//                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);
//
//
//
//                    cardView1.setVisibility(View.VISIBLE);
//                    cardView2.setVisibility(View.VISIBLE);
//                    cardView3.setVisibility(View.INVISIBLE);
//                    cardView4.setVisibility(View.INVISIBLE);
//                    cardView5.setVisibility(View.INVISIBLE);
//
//                    break;
//                case 9: //item3
//
//                    //first item
//                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
//                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
//                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
//                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);
//
//                    //second item
//                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
//                    item2DescBar1.setText(UserMainPage.itemsBar1[2]);
//                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
//                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);
//
//                    //third item
//                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
//                    item3DescBar1.setText(UserMainPage.itemsBar1[7]);
//                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
//                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);
//
//                    cardView1.setVisibility(View.VISIBLE);
//                    cardView2.setVisibility(View.VISIBLE);
//                    cardView3.setVisibility(View.VISIBLE);
//                    cardView4.setVisibility(View.INVISIBLE);
//                    cardView5.setVisibility(View.INVISIBLE);
//
//                    break;
//                case 12: //item4
//
//                    //first item
//                    item1NameBar1.setText(UserMainPage.itemsBar1[0]);
//                    item1DescBar1.setText(UserMainPage.itemsBar1[1]);
//                    item1PriceBar1.setText(UserMainPage.itemsBar1[2]);
//                    item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);
//
//                    //second item
//                    item2NameBar1.setText(UserMainPage.itemsBar1[3]);
//                    item2DescBar1.setText(UserMainPage.itemsBar1[4]);
//                    item2PriceBar1.setText(UserMainPage.itemsBar1[5]);
//                    item2ImageBar1.setBackground(UserMainPage.imgItemsBar1[1]);
//
//                    //third item
//                    item3NameBar1.setText(UserMainPage.itemsBar1[6]);
//                    item3DescBar1.setText(UserMainPage.itemsBar1[7]);
//                    item3PriceBar1.setText(UserMainPage.itemsBar1[8]);
//                    item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);
//
//                    //forth item
//                    item4NameBar1.setText(UserMainPage.itemsBar1[9]);
//                    item4DescBar1.setText(UserMainPage.itemsBar1[10]);
//                    item4PriceBar1.setText(UserMainPage.itemsBar1[11]);
//                    item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3]);
//
//                    cardView1.setVisibility(View.VISIBLE);
//                    cardView2.setVisibility(View.VISIBLE);
//                    cardView3.setVisibility(View.VISIBLE);
//                    cardView4.setVisibility(View.VISIBLE);
//                    cardView5.setVisibility(View.INVISIBLE);
//
//                    break;
//                case 15: //item5
//
//
//                    //first item
//                    Bar1.item1NameBar1.setText(bars[0]);
//                    Bar1.item1DescBar1.setText(bars[1]);
//                    Bar1.item1PriceBar1.setText(bars[2]);
//                    //Bar1.item1ImageBar1.setBackground(UserMainPage.imgItemsBar1[0]);
//
//                    //second item
//                    Bar1.item2NameBar1.setText(bars[3]);
//                    Bar1.item2DescBar1.setText(bars[4]);
//                    Bar1.item2PriceBar1.setText(bars[5]);
//                    //Bar1.item2ImageBar1.setBackground(imgItemsBar1[1]);
//
//                    //third item
//                    Bar1.item3NameBar1.setText(bars[6]);
//                    Bar1.item3DescBar1.setText(bars[7]);
//                    Bar1.item3PriceBar1.setText(bars[8]);
//                   // Bar1.item3ImageBar1.setBackground(UserMainPage.imgItemsBar1[2]);
//
//                    //forth item
//                    Bar1.item4NameBar1.setText(bars[9]);
//                    Bar1.item4DescBar1.setText(bars[10]);
//                    Bar1.item4PriceBar1.setText(bars[11]);
//                   // Bar1.item4ImageBar1.setBackground(UserMainPage.imgItemsBar1[3]);
//
//                    //fifth item
//                    Bar1.item5NameBar1.setText(bars[12]);
//                    Bar1.item5DescBar1.setText(bars[13]);
//                    Bar1.item5PriceBar1.setText(bars[14]);
//                    //Bar1.item5ImageBar1.setBackground(UserMainPage.imgItemsBar1[4]);
//
//                    Bar1.cardView1.setVisibility(View.VISIBLE);
//                    Bar1.cardView2.setVisibility(View.VISIBLE);
//                    Bar1.cardView3.setVisibility(View.VISIBLE);
//                    Bar1.cardView4.setVisibility(View.VISIBLE);
//                    Bar1.cardView5.setVisibility(View.VISIBLE);
//
//                    break;
//                default:
//                    if(Locale.getDefault().getLanguage().equals("ar"))
//                        Toast.makeText(this,"غير ممكن في صفحة العنوان الفرعي 1 ",Toast.LENGTH_LONG).show();
//                    else
//                        Toast.makeText(this, "this is not possible Bar1 page", Toast.LENGTH_LONG).show();
//                    break;
//            }
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//
//    }
//        catch (Exception e){
//            Toast.makeText(UserMainPage.this,e.getMessage(),Toast.LENGTH_LONG).show();
//        }
////return bars;
//    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            setUMPBackground(new BitmapDrawable(getResources(),b));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void intoFile (){

        String a = null;
        try {
            FileOutputStream fileOutputStream =openFileOutput("save purchaseBasketItem",MODE_PRIVATE);

            for (int i=0 ; i< purchaseBasketItems.length ; i++){
                if(purchaseBasketItems[i] != null)
                a += purchaseBasketItems[i]+",";
            }
            fileOutputStream.write(a.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit the application")
                .setMessage("are you sure you want to exit the app? \nit will delet your sopping cart!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
