package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Locale;

public class CreateAppStep4 extends AppCompatActivity {

    private static final int SELECTED_PICTURE=1;

    CheckBox PromoSlides;
    static CheckBox ShopingCart;
    CheckBox Mada;
    CheckBox Cash;
    CheckBox InStore;
    CheckBox byHand;

    Button chooseSlideShowButton;

    public static Activity step4;//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step4);
        step4=this;//test
        Button next = (Button) findViewById(R.id.NextStep4);
        PromoSlides = (CheckBox) findViewById(R.id.checkBox4);
        Mada = (CheckBox) findViewById(R.id.checkBox7);
        ShopingCart = (CheckBox) findViewById(R.id.checkBox);
        Cash = (CheckBox) findViewById(R.id.checkBox5);
        InStore = (CheckBox) findViewById(R.id.checkBox6);
        byHand = (CheckBox) findViewById(R.id.checkBox2);

        chooseSlideShowButton = (Button) findViewById(R.id.slideShowButton);

        //to show and disappear the button
        PromoSlides.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chooseSlideShowButton.setVisibility(View.VISIBLE);
                }
                if (!isChecked){
                    chooseSlideShowButton.setVisibility(View.GONE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep4.this, CreateAppStep5.class);
                putOnMainPage();
                if(UserMainPage.Payment >= 0 && UserMainPage.Delivery >= 0)
                startActivity(i);
                if(UserMainPage.isCom)
                finish();

            }
        });

        }
    public void putOnMainPage(){
        if (PromoSlides.isChecked())
            UserMainPage.promoSlide.setVisibility(View.VISIBLE);
        else
            UserMainPage.promoSlide.setVisibility(View.INVISIBLE);

//        if(ShopingCart.isChecked())
//            UserMainPage.ShoppingCart.setVisibility(View.VISIBLE);
//        else
//            UserMainPage.ShoppingCart.setVisibility(View.INVISIBLE);
        if(Cash.isChecked() && Mada.isChecked()){
            UserMainPage.Payment = 2;
        }
        else if(Cash.isChecked() && !Mada.isChecked()){
            UserMainPage.Payment = 1;
        }
        else if(!Cash.isChecked() && Mada.isChecked()){
            UserMainPage.Payment = 0;
        }
        else{
            UserMainPage.Payment = -1;
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(this, "فضلا إختر طريقة دفع", Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(CreateAppStep4.this ,"Please Select a payment method" , Toast.LENGTH_SHORT).show();
        }
        if(InStore.isChecked() && byHand.isChecked()){
            UserMainPage.Delivery = 2;
        }
        else if(InStore.isChecked() && !byHand.isChecked()){
            UserMainPage.Delivery = 1;
        }
        else if(!InStore.isChecked() && byHand.isChecked()){
            UserMainPage.Delivery = 0;
        }
        else{
            UserMainPage.Delivery = -1;
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(this, "فضلا إختر طريقة توصيل", Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(CreateAppStep4.this ,"Please Select a delivery method" , Toast.LENGTH_SHORT).show();}
    }


    public void uploadImg (View view){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i , SELECTED_PICTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case SELECTED_PICTURE:
                try {
                    if (!(requestCode == RESULT_OK)) {

                        Uri uri = data.getData();
                        String[] projection = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(projection[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();

                        Bitmap yourSelect = BitmapFactory.decodeFile(filePath);
                        Drawable d = new BitmapDrawable(yourSelect);

                        try {
                            UserMainPage.slideShowPicture.setBackground(d);
                        } catch (Exception e) {
                        }
                    }
                }catch (Exception e){
                    //empty
                }
                break;

            default:
                Toast.makeText(this, "in default", Toast.LENGTH_LONG).show();
                break;
        }
    }

    }
