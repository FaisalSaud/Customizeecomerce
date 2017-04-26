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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

//        itemsBar1 = new String[5];
//        itemsBar2 = new String[5];
//        itemsBar3 = new String[5];
//        itemsBar4 = new String[5];



        itemNameStep5 = (EditText) findViewById(R.id.ItemNameStep5);
        itemDescriptionStep5 = (EditText) findViewById(R.id.ItemDescriptionStep5);
        itemPriceStep5 = (EditText) findViewById(R.id.itemPriceStep5);
        barsListStep5 = (Spinner) findViewById(R.id.barsSpinnerStep5);


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
                UserMainPage.isCom=true;
                CreateAppStep1.step1.finish();
                CreateAppStep2.step2.finish();
                CreateAppStep3.step3.finish();
                CreateAppStep4.step4.finish();
                finish();
            }
        });
         img = (EditText) findViewById(R.id.ItemImageStep5);}
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

    public void saveItem (View view){

        if(barsListStep5.getSelectedItemPosition()==0){
            if (UserMainPage.itemsBar1.length >= 15){
                Toast.makeText(CreateAppStep5.this,"you have reach the item limit",Toast.LENGTH_LONG).show();
                return;
            }else {
                UserMainPage.itemsBar1[UserMainPage.itemsBar1.length - 1]=itemNameStep5.getText().toString();           //name
                UserMainPage.itemsBar1[UserMainPage.itemsBar1.length - 1]=itemDescriptionStep5.getText().toString();    //description
                UserMainPage.itemsBar1[UserMainPage.itemsBar1.length - 1]=itemPriceStep5.getText().toString();          //price
            }
        }else if(barsListStep5.getSelectedItemPosition()==1){
            if (UserMainPage.itemsBar2.length >= 15){
                Toast.makeText(CreateAppStep5.this,"you have reach the item limit",Toast.LENGTH_LONG).show();
                return;
            }else {
                UserMainPage.itemsBar2[UserMainPage.itemsBar2.length - 1] = itemNameStep5.getText().toString();           //name
                UserMainPage.itemsBar2[UserMainPage.itemsBar2.length - 1] = itemDescriptionStep5.getText().toString();    //description
                UserMainPage.itemsBar2[UserMainPage.itemsBar2.length - 1] = itemPriceStep5.getText().toString();          //price
            }
        }else if(barsListStep5.getSelectedItemPosition()==2){
            if (UserMainPage.itemsBar3.length >= 15){
                Toast.makeText(CreateAppStep5.this,"you have reach the item limit",Toast.LENGTH_LONG).show();
                return;
            }else {
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemNameStep5.getText().toString();           //name
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemDescriptionStep5.getText().toString();    //description
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemPriceStep5.getText().toString();          //price
            }
        }else if(barsListStep5.getSelectedItemPosition()==3){
            if (UserMainPage.itemsBar4.length >= 15){
                Toast.makeText(CreateAppStep5.this,"you have reach the item limit",Toast.LENGTH_LONG).show();
                return;
            }else {
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemNameStep5.getText().toString();           //name
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemDescriptionStep5.getText().toString();    //description
                UserMainPage.itemsBar3[UserMainPage.itemsBar3.length - 1] = itemPriceStep5.getText().toString();          //price
            }
        }else {
            Toast.makeText(CreateAppStep5.this,"imposable to reach this Toast in CreateAppStep5 ",Toast.LENGTH_LONG).show();
        }

    }


    }



