package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateAppStep1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step1);

        Button next = (Button) findViewById(R.id.nextButton);


        try{
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep1.this, CreateAppStep2.class);
                startActivity(i);
            }
        });}
        catch (Exception e){
            Toast.makeText(this,"error in step 1.1", Toast.LENGTH_LONG).show();
        }
    }

    public void gitImgFromGallery (View view){
        int PICK_IMAGE = 101;
        Intent intent = new Intent();
        try {
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Icon"), PICK_IMAGE);

        }
        catch (Exception e){
            Toast.makeText(this,"error in step 1.2", Toast.LENGTH_LONG).show();
        }
    }
}
