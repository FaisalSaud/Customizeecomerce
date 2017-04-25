package com.example.abu.customizeecomerce;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

public class CreateAppStep2 extends AppCompatActivity {
    private static final int SELECTED_PICTURE = 1;
//    TextView img;
//    EditText img;

    public static Activity step2;
    public static EditText backgroundImg;
    public static String backColor;//test


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step2);


        step2 = this;
        backColor = ""; //test

        backgroundImg = (EditText) findViewById(R.id.backgroundImage);
        Button next = (Button) findViewById(R.id.nextButton);
        //img = (EditText) findViewById(R.id.editText4);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep2.this, CreateAppStep3.class);
                //UserMainPage.Background.setImage(SelectedImage);
                putOnMainPage();
                startActivity(i);
                if(UserMainPage.isCom)
                finish();
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

        //Toast.makeText(CreateAppStep2.this, "before switch", Toast.LENGTH_LONG).show();
        switch (requestCode) {

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
                            backgroundImg.setBackground(d);
                        } catch (Exception e) {
                        }
                    }
                }catch (Exception e){
                    //empty
                }
                break;
            case 2:
                if (requestCode == 2) {

                    Bitmap bitmap = (Bitmap) data.getParcelableExtra("bitmap"); //you can delete the casting
                    Drawable d = new BitmapDrawable(getResources(), bitmap);

                    backgroundImg.setBackground(d);

                }
            default:
                Toast.makeText(this, "in default", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void chooseBackgroundImage (View view){
        Intent i = new Intent(CreateAppStep2.this,ChooseBackground.class);
        startActivity(i);
    }

    public void putOnMainPage (){
        try{

//            switch (backColor){//test
//                case "orange":
//                    //UserMainPage.Background.setBackgroundResource(R.color.orange);
//                    UserMainPage.promoSlide.setBackgroundResource(R.color.orange);
//            }
            UserMainPage.background.setBackground(backgroundImg.getBackground());

            //UserMainPage.Background.setBackgroundDrawable(backgroundImg.getBackground());

        }catch (Exception e){
            Toast.makeText(CreateAppStep2.this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
