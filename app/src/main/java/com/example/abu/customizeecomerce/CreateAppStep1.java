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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateAppStep1 extends AppCompatActivity {

    //to add image
    private static final int SELECTED_PICTURE=1;
    EditText img;
    EditText storeName;
    String storeNameS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step1);

        Button next = (Button) findViewById(R.id.nextButton);

        img = (EditText) findViewById(R.id.storeIcon);
        storeName = (EditText) findViewById(R.id.storeName);
        storeNameS = storeName.getText().toString();

        //img.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.icon),null);



        try{
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);
                Toast.makeText(CreateAppStep1.this, storeNameS , Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });}
        catch (Exception e){
            Toast.makeText(this,"error in step 1.1", Toast.LENGTH_LONG).show();
        }
    }

    public void nextButton (View view){

        if (storeNameS.isEmpty()){
            Toast.makeText(CreateAppStep1.this, "the Store Name is empty please" , Toast.LENGTH_SHORT).show();
            return;
        }else if (storeNameS.length()>29) {
            Toast.makeText(CreateAppStep1.this, "the Store Name is too long", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(CreateAppStep1.this, storeNameS , Toast.LENGTH_SHORT).show();

            Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);
            startActivity(i);
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

        //Toast.makeText(this, "before switch", Toast.LENGTH_LONG).show();

        switch (requestCode){
            case SELECTED_PICTURE:
                if (!(requestCode==RESULT_OK)){

                   // Toast.makeText(this, "after switch", Toast.LENGTH_LONG).show();

                    Uri uri = data.getData();
                    String[]projection={MediaStore.Images.Media.DATA};

                    //Toast.makeText(this, "111111", Toast.LENGTH_LONG).show();

                    Cursor cursor= getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

//                    Toast.makeText(this, "222222", Toast.LENGTH_LONG).show();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

//                    Toast.makeText(this, "3333333", Toast.LENGTH_LONG).show();

                    Bitmap yourSelect = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(yourSelect);

//                    Toast.makeText(this, "4444444", Toast.LENGTH_LONG).show();
                    try {
                        img.setBackground(d);
                    }catch (Exception e){
//                        Toast.makeText(this, "555555", Toast.LENGTH_LONG).show();
                    }
                }
//                Toast.makeText(this, "the if is false", Toast.LENGTH_LONG).show();
                break;
            default:
//                Toast.makeText(this, "in default", Toast.LENGTH_LONG).show();
                break;
        }
    }




}
