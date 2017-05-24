package com.example.abu.customizeecomerce;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class CreateAppStep5 extends AppCompatActivity {
    private static final int SELECTED_PICTURE = 1;
    Button bulid ;
    EditText img;
public static int acssess;
    EditText itemNameStep5;
    EditText itemDescriptionStep5;
    EditText itemPriceStep5;
    Spinner barsListStep5;
public static String f;
//    String [] bars;
//
////    String []
//    String [] itemsBar1;
//    String [] itemsBar2;
//    String [] itemsBar3;
//    String [] itemsBar4;

    public static int count;
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
        img = (EditText) findViewById(R.id.ItemImageStep5);
       // f=saveToInternalStorage();

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
        final CheckBox CM = (CheckBox)findViewById(R.id.checkMode);
        bulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savebars();
                saveitembar1();
                saveitembar2();
                saveitembar3();
                saveitembar4();
            //saveToInternalStorage(drawableToBitmap(CreateAppStep2.backgroundImg.getBackground()));
                UserMainPage.isCom = true;
                if(CM.isChecked()){
                    Intent i = new Intent(CreateAppStep5.this , CreateAppStepFinal.class);
              startActivity(i);
                }

                else{

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
                    }catch (Exception e){}
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
        if (count >= 15)
            return false;
//        for (int i = count+1 ; i < bar.length ; i++){
//            if (bar[i]!= null)
//                empty = fa
// lse;
//        }
        return empty;
    }



    public void saveItem (View view) {

        try {
            if (barsListStep5.getSelectedItemPosition() == 0) { //bar1


                try {
                    if (!iEmpty(UserMainPage.itemsBar1 , UserMainPage.B1IteamC)){
                        if(Locale.getDefault().getLanguage().equals("ar"))
                            Toast.makeText(this, "لقد وصلت للحد الأعلى", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {


                        Toast.makeText(CreateAppStep5.this, "in 1st else", Toast.LENGTH_LONG).show();
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar1[UserMainPage.B1IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar1[(UserMainPage.B1IteamC-1) / 3] = img.getBackground();     //image
                        count=UserMainPage.B1IteamC;
                    }
                }catch (Exception e){
                    Toast.makeText(CreateAppStep5.this, "error in 1 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                }

            } else if (barsListStep5.getSelectedItemPosition() == 1) {  //bar2

                try {
                    if (!iEmpty(UserMainPage.itemsBar2,UserMainPage.B2IteamC)) {
                        if(Locale.getDefault().getLanguage().equals("ar"))
                            Toast.makeText(this, "لقد وصلت للحد الأعلى", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar2[UserMainPage.B2IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar2[(UserMainPage.B2IteamC-1) / 3]=img.getBackground();     //image
                    }

                }catch (Exception e){
                Toast.makeText(CreateAppStep5.this, "error in 2 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
            }
            } else if (barsListStep5.getSelectedItemPosition() == 2) { //bar3

                try {
                    if (!iEmpty(UserMainPage.itemsBar3,UserMainPage.B3IteamC)) {
                        if(Locale.getDefault().getLanguage().equals("ar"))
                            Toast.makeText(this, "لقد وصلت للحد الأعلى", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar3[UserMainPage.B3IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar3[(UserMainPage.B3IteamC-1) / 3]=img.getBackground() ;
                    }
                }catch (Exception e){
                    Toast.makeText(CreateAppStep5.this, "error in 3 ---   "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                }
            } else if (barsListStep5.getSelectedItemPosition() == 3) { //bar4

                try {
                    if (!iEmpty(UserMainPage.itemsBar4,UserMainPage.B4IteamC)) {
                        if(Locale.getDefault().getLanguage().equals("ar"))
                            Toast.makeText(this, "لقد وصلت للحد الأعلى", Toast.LENGTH_LONG).show();
                        else
                        Toast.makeText(CreateAppStep5.this, "you have reach the item limit", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemNameStep5.getText().toString();           //name
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemDescriptionStep5.getText().toString();    //description
                        UserMainPage.itemsBar4[UserMainPage.B4IteamC ++] = itemPriceStep5.getText().toString();          //price
                        UserMainPage.imgItemsBar4[(UserMainPage.B4IteamC-1) / 3]=img.getBackground() ;
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
    public void savebars(){
        String file ="save bar1";
        String bar1 = CreateAppStep3.bar1.getText().toString();
        String bar2 = CreateAppStep3.bar2.getText().toString();
        String bar3 = CreateAppStep3.bar3.getText().toString();
        String bar4 = CreateAppStep3.bar4.getText().toString();

        try {
            FileOutputStream fileOutputStream =openFileOutput(file,MODE_PRIVATE);
            fileOutputStream.write((bar1+","+bar2+","+bar3+","+bar4).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveitembar1(){

        try {
            FileOutputStream fileOutputStream =openFileOutput("save itembar1",MODE_PRIVATE);
                fileOutputStream.write((UserMainPage.itemsBar1[0]+","+UserMainPage.itemsBar1[1]+","+UserMainPage.itemsBar1[2]+","+UserMainPage.itemsBar1[3]
                        + "," + UserMainPage.itemsBar1[4] + "," +  UserMainPage.itemsBar1[5] + "," + UserMainPage.itemsBar1[6]
                        + "," + UserMainPage.itemsBar1[7] + "," +  UserMainPage.itemsBar1[8] + "," + UserMainPage.itemsBar1[9]
                        + "," + UserMainPage.itemsBar1[10] + "," + UserMainPage.itemsBar1[11] + "," +UserMainPage.itemsBar1[12]
                        + "," + UserMainPage.itemsBar1[13] + "," + UserMainPage.itemsBar1[14]).getBytes());

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveitembar2(){

        try {
            FileOutputStream fileOutputStream =openFileOutput("save itembar2",MODE_PRIVATE);
            fileOutputStream.write((UserMainPage.itemsBar2[0]+","+UserMainPage.itemsBar2[1]+","+UserMainPage.itemsBar2[2]+","+UserMainPage.itemsBar2[3]
                    + "," + UserMainPage.itemsBar2[4] + "," +  UserMainPage.itemsBar2[5] + "," + UserMainPage.itemsBar2[6]
                    + "," + UserMainPage.itemsBar2[7] + "," +  UserMainPage.itemsBar2[8] + "," + UserMainPage.itemsBar2[9]
                    + "," + UserMainPage.itemsBar2[10] + "," + UserMainPage.itemsBar2[11] + "," +UserMainPage.itemsBar2[12]
                    + "," + UserMainPage.itemsBar2[13] + "," + UserMainPage.itemsBar2[14]).getBytes());

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveitembar3(){

        try {
            FileOutputStream fileOutputStream =openFileOutput("save itembar3",MODE_PRIVATE);
            fileOutputStream.write((UserMainPage.itemsBar3[0]+","+UserMainPage.itemsBar3[1]+","+UserMainPage.itemsBar3[2]+","+UserMainPage.itemsBar3[3]
                    + "," + UserMainPage.itemsBar3[4] + "," +  UserMainPage.itemsBar3[5] + "," + UserMainPage.itemsBar3[6]
                    + "," + UserMainPage.itemsBar3[7] + "," +  UserMainPage.itemsBar3[8] + "," + UserMainPage.itemsBar3[9]
                    + "," + UserMainPage.itemsBar3[10] + "," + UserMainPage.itemsBar3[11] + "," +UserMainPage.itemsBar3[12]
                    + "," + UserMainPage.itemsBar3[13] + "," + UserMainPage.itemsBar3[14]).getBytes());

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveitembar4(){

        try {
            FileOutputStream fileOutputStream =openFileOutput("save itembar4",MODE_PRIVATE);
            fileOutputStream.write((UserMainPage.itemsBar4[0]+","+UserMainPage.itemsBar4[1]+","+UserMainPage.itemsBar4[2]+","+UserMainPage.itemsBar4[3]
                    + "," + UserMainPage.itemsBar4[4] + "," +  UserMainPage.itemsBar4[5] + "," + UserMainPage.itemsBar4[6]
                    + "," + UserMainPage.itemsBar4[7] + "," +  UserMainPage.itemsBar4[8] + "," + UserMainPage.itemsBar4[9]
                    + "," + UserMainPage.itemsBar4[10] + "," + UserMainPage.itemsBar4[11] + "," +UserMainPage.itemsBar4[12]
                    + "," + UserMainPage.itemsBar4[13] + "," + UserMainPage.itemsBar4[14]).getBytes());

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void saveimg(){
//        String file ="save img";
//
//        try {
//            FileOutputStream fileOutputStream =openFileOutput(file,MODE_PRIVATE);
//            fileOutputStream.write((CreateAppStep2.backgroundImg.getBackground().toString()).getBytes());
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static Bitmap drawableToBitmap (Drawable drawable) {
//        if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable)drawable).getBitmap();
//        }
//
//        int width = drawable.getIntrinsicWidth();
//        width = width > 0 ? width : 1;
//        int height = drawable.getIntrinsicHeight();
//        height = height > 0 ? height : 1;
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//
//        return bitmap;
//    }
//    private String saveToInternalStorage(Bitmap bitmapImage){
//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//        File mypath=new File(directory,"profile.jpg");
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(mypath);
//            // Use the compress method on the BitMap object to write image to the OutputStream
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return directory.getAbsolutePath();
//    }

    }



