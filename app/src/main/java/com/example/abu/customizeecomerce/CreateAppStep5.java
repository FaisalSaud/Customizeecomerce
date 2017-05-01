package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class CreateAppStep5 extends AppCompatActivity {
    private static final int SELECTED_PICTURE = 1;
    Button bulid ;
    EditText img;
    EditText itemNameStep5;
    EditText itemDescriptionStep5;
    EditText itemPriceStep5;
    Spinner barsListStep5;

//    String [] bars;
//
////    String []
//    String [] itemsBar1;
//    String [] itemsBar2;
//    String [] itemsBar3;
//    String [] itemsBar4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step5);
        final CheckBox Cm = (CheckBox) findViewById(R.id.checkMode);
//        itemsBar1 = new String[5];
//        itemsBar2 = new String[5];
//        itemsBar3 = new String[5];
//        itemsBar4 = new String[5];



        itemNameStep5 = (EditText) findViewById(R.id.ItemNameStep5);
        itemDescriptionStep5 = (EditText) findViewById(R.id.ItemDescriptionStep5);
        itemPriceStep5 = (EditText) findViewById(R.id.itemPriceStep5);
        barsListStep5 = (Spinner) findViewById(R.id.barsSpinnerStep5);
        img = (EditText) findViewById(R.id.ItemImageStep5);


        try {
            switch (CreateAppStep3.cont) {
                case 1:
                    UserMainPage.bars = new String[1];
                    UserMainPage.bars[0] = CreateAppStep3.bar1.getText().toString();
                    break;
                case 2:
                    UserMainPage.bars = new String[2];
                    UserMainPage.bars[0] = CreateAppStep3.bar1.getText().toString();
                    UserMainPage.bars[1] = CreateAppStep3.bar2.getText().toString();
                    break;
                case 3:
                    UserMainPage.bars = new String[3];
                    UserMainPage.bars[0] = CreateAppStep3.bar1.getText().toString();
                    UserMainPage.bars[1] = CreateAppStep3.bar2.getText().toString();
                    UserMainPage.bars[2] = CreateAppStep3.bar3.getText().toString();
                    break;
                case 4:
                    UserMainPage.bars = new String[4];
                    UserMainPage.bars[0] = CreateAppStep3.bar1.getText().toString();
                    UserMainPage.bars[1] = CreateAppStep3.bar2.getText().toString();
                    UserMainPage.bars[2] = CreateAppStep3.bar3.getText().toString();
                    UserMainPage.bars[3] = CreateAppStep3.bar4.getText().toString();
                    break;
                default:
                    break;
            }


            ArrayAdapter<String> Dadapter = new ArrayAdapter<String>( this,
                    android.R.layout.simple_spinner_item, UserMainPage.bars);
            barsListStep5.setAdapter(Dadapter);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        bulid = (Button) findViewById(R.id.buildButtonStep5);
        bulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(Cm.isChecked()){
                   Intent i = new Intent(CreateAppStep5.this, CreateAppStepFinal.class);
                   startActivity(i);
               }
                else{
                   UserMainPage.isCom=true;
                   CreateAppStep1.step1.finish();
                   CreateAppStep2.step2.finish();
                   CreateAppStep3.step3.finish();
                   CreateAppStep4.step4.finish();
                   finish();
                }

            }
        });
    }
    public void uploadImg(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(CreateAppStep5.this, "before switch", Toast.LENGTH_LONG).show();
        try {
            switch (requestCode) {


                case SELECTED_PICTURE:
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
                            img.setBackground(d);
                        } catch (Exception e) {
                        }
                    }
                    break;
                case 2:
                    if (requestCode == 2) {

                        Bitmap bitmap = (Bitmap) data.getParcelableExtra("bitmap"); //you can delete the casting
                        Drawable d = new BitmapDrawable(getResources(), bitmap);

                        img.setBackground(d);

                    }
                default:
                    Toast.makeText(this, "in default", Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public boolean iEmpty (String [] bar , int count){      //to find if there is an empty space for item in array "String only"

        Toast.makeText(CreateAppStep5.this, "enter to 'iEmpty' method", Toast.LENGTH_LONG).show();
        boolean empty = true;
        if (count > 15)
            return false;
        for (int i = count ; i < bar.length ; i++){
            if (bar[i]!= null)
                empty = false;
        }
        return empty;
    }



    public void saveItem (View view) {

        try {
            if (barsListStep5.getSelectedItemPosition() == 0) { //bar1

                try {
                    if (!iEmpty(UserMainPage.itemsBar1 , UserMainPage.B1IteamC)){
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {


                        Toast.makeText(CreateAppStep5.this, "in 1st else", Toast.LENGTH_LONG).show();
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar1[(UserMainPage.B1IteamC-1) / 3] = img;     //image

                    }
                }catch (Exception e){
                    Toast.makeText(CreateAppStep5.this, "error in 1 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                }

            } else if (barsListStep5.getSelectedItemPosition() == 1) {  //bar2

                try {
                    if (!iEmpty(UserMainPage.itemsBar2,UserMainPage.B2IteamC)) {
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar2[(UserMainPage.B2IteamC-1) / 3].setBackground(img.getBackground());     //image
                    }

                }catch (Exception e){
                Toast.makeText(CreateAppStep5.this, "error in 2 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
            }
            } else if (barsListStep5.getSelectedItemPosition() == 2) { //bar3

                try {
                    if (!iEmpty(UserMainPage.itemsBar3,UserMainPage.B3IteamC)) {
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar3[(UserMainPage.B3IteamC-1) / 3]=img ;
                    }
                }catch (Exception e){
                    Toast.makeText(CreateAppStep5.this, "error in 3 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                }
            } else if (barsListStep5.getSelectedItemPosition() == 3) { //bar4

                try {
                    if (!iEmpty(UserMainPage.itemsBar4,UserMainPage.B4IteamC)) {
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar4[(UserMainPage.B4IteamC-1) / 3]=img ;
                    }
                }catch (Exception e){
                    Toast.makeText(CreateAppStep5.this, "error in 4 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(CreateAppStep5.this, "imposable to reach this Toast in CreateAppStep5 ", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(CreateAppStep5.this, "one item done", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(CreateAppStep5.this, e.getMessage() ,Toast.LENGTH_LONG).show();
        }
    }


    }



