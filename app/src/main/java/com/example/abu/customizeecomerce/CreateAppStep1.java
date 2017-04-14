package com.example.abu.customizeecomerce;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
    EditText img;
    EditText storeName;
    String storeNameS;
    Button chooseIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step1);

        Button next = (Button) findViewById(R.id.nextButton);
        chooseIcon = (Button) findViewById(R.id.chooseIconButton);
        img = (EditText) findViewById(R.id.storeIcon);
        storeName = (EditText) findViewById(R.id.storeName);
        storeNameS = storeName.getText().toString();

        //img.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.icon),null);

//        chooseIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CreateAppStep1.this, ChooseIconImage.class);
//                startActivity(i);
//            }
//        });

        if (storeNameS.isEmpty()) {

            //Toast.makeText(CreateAppStep1.this, "The store name is empty", Toast.LENGTH_SHORT).show();
        }else{
            try{
                next.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                        Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);
                        Toast.makeText(CreateAppStep1.this, storeNameS , Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });
            }
        catch (Exception e){
            Toast.makeText(this,"error in step 1.1", Toast.LENGTH_LONG).show();
        }
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
        Toast.makeText(CreateAppStep1.this,"before switch",Toast.LENGTH_LONG).show();
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
                     //   img.setBackgroundDrawable(d);
                        img.setBackground(d);
                    }catch (Exception e){
//                        Toast.makeText(this, "555555", Toast.LENGTH_LONG).show();
                    }
                }
//                Toast.makeText(this, "the if is false", Toast.LENGTH_LONG).show();
                break;
            case 2:
                if (requestCode== Activity.RESULT_FIRST_USER){

//                    try {
//                        CharSequence userIcon3 = data.getCharSequenceExtra("userIcon");
//
//                        BitmapDrawable userIcon = (BitmapDrawable) BitmapDrawable.createFromPath(data.getStringExtra("userIcon"));
//
//                        Drawable userIcon1 = userIcon;
//                        img.setBackground(userI8con1);
//
//                    }catch (Exception e){
//                        Toast.makeText(CreateAppStep1.this, e.getMessage() + " Create app s1" , Toast.LENGTH_LONG).show();
//                    }
                    Toast.makeText(CreateAppStep1.this,"2",Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    Bitmap bb = (Bitmap)  i.getParcelableExtra("icon");
                    ImageView iv = (ImageView) findViewById(R.id.imageView) ;
                    iv.setImageBitmap(bb);

                    Drawable d =  iv.getDrawable();

                    img.setBackground(d);

//
//                    Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("userIcon"),0,
//                            getIntent().getByteArrayExtra("userIcon").length);
//                    ImageView pr = new ImageView(this);
//                    pr.setImageBitmap(b);
//
//                    Bitmap bitmap =(Bitmap) i.getParcelableExtra("icon");
//                    //img.setImageBitmap(bitmap);
//                    img.setBackground(bitmap);
//
//                    img.setBackground();


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

//    protected void onActivityResult (int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==2){
//            Parcelable[] userIcon2 = data.getParcelableArrayExtra("userIcon");
//            CharSequence userIcon3 = data.getCharSequenceExtra("userIcon");
//            Drawable userIcon = (Drawable) userIcon3;
//            img.setBackground(userIcon);
//
//
//        }
//    }




}
