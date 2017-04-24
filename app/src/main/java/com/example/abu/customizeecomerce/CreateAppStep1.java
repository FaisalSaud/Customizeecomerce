package com.example.abu.customizeecomerce;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateAppStep1 extends AppCompatActivity {

    //to add image
    private static final int SELECTED_PICTURE=1;
   public static EditText img;
    EditText storeName;
    String storeNameS;
    Button chooseIcon;

    public static Activity step1;//test


        @Override
        protected void onCreate (Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_app_step1);

            step1=this;//test

            Button next = (Button) findViewById(R.id.nextButton);
            chooseIcon = (Button) findViewById(R.id.chooseIconButton);
            img = (EditText) findViewById(R.id.storeIcon);


            //////////////////////////////////////////////////////////////////////////////////////


            //////////////////////////////////////////////////////////////////////////////////////

            //img.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.icon),null);

//        chooseIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CreateAppStep1.this, ChooseIconImage.class);
//                startActivity(i);
//            }
//        });

//        if (storeNameS.isEmpty()) {
//
//            //Toast.makeText(CreateAppStep1.this, "The store name is empty", Toast.LENGTH_SHORT).show();
//        }else{
//            try{
//                next.setOnClickListener(new View.OnClickListener() {
//            @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);
//                        Toast.makeText(CreateAppStep1.this, storeNameS , Toast.LENGTH_SHORT).show();
//                        startActivity(i);
//                    }
//                });
//            }
//        catch (Exception e){
//            Toast.makeText(this,"error in step 1.1", Toast.LENGTH_LONG).show();
//        }
//        }


        }
    public void nextButton (View view){

        storeName = (EditText) findViewById(R.id.storeName);
        storeNameS = storeName.getText().toString();

        if (storeNameS.isEmpty()){
            Toast.makeText(CreateAppStep1.this, "the Store Name is empty please" , Toast.LENGTH_SHORT).show();
            return;
        }else if (storeNameS.length()>29) {
            Toast.makeText(CreateAppStep1.this, "the Store Name is too long", Toast.LENGTH_SHORT).show();
            return;
        }else{

            Toast.makeText(CreateAppStep1.this, storeNameS , Toast.LENGTH_SHORT).show();

            Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);

//            //send the store name
//            intent.putExtra("storeName", storeNameS);
//
//            //send the store icon
//            Drawable drawable = (Drawable) img.getBackground();
//            BitmapDrawable bd = (BitmapDrawable) drawable;
//            Bitmap bitmap = (Bitmap) bd.getBitmap();
//            intent.putExtra("storeIcon", bitmap);


            startActivity(i);
            if(UserMainPage.isCom)
                finish();




        }
    }

//    public void gitImgFromGallery (View view){
//        int PICK_IMAGE = 101;
//        Intent intent = new Intent();
//        try {
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Icon"), PICK_IMAGE);
//
//        }
//        catch (Exception e){
//            Toast.makeText(this,"error in step 1.2", Toast.LENGTH_LONG).show();
//        }
//    }

    public void uploadImg (View view){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i , SELECTED_PICTURE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Toast.makeText(CreateAppStep1.this,"before switch",Toast.LENGTH_LONG).show();
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
                            img.setBackground(d);
                        } catch (Exception e) {
                        }
                    }
                }catch (Exception e){
                    //empty
                }
                break;
            case 2:
                if (requestCode== 2){

                    Bitmap bitmap = (Bitmap) data.getParcelableExtra("bitmap"); //you can delete the casting
                    Drawable d = new BitmapDrawable(getResources(),bitmap);


                    img.setBackground(d);

                }
            default:
                Toast.makeText(this, "in default", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void goToChooseIcon (View view){

        try {
            Intent i = new Intent(CreateAppStep1.this, ChooseIconImage.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        UserMainPage.UserMainPage.finish();
    }
}
