package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createNewApp = (Button) findViewById(R.id.createApp);
         myApp = (Button) findViewById(R.id.myApp);
        Button howToStart = (Button) findViewById(R.id.howToStart);
        // ST is a test button
        Button ST = (Button) findViewById(R.id.button8);

        createNewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent[] intents = new Intent[2];
                Intent intent = new Intent(MainActivity.this, UserMainPage.class);
//                intents[1]=intent;
//                Intent i = new Intent(MainActivity.this, CreateAppStep1.class);
//                intents[0]=i;

                try {


                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                UserMainPage.isCom = false;
                startActivity(intent);
//                startActivities(intents);
            }
        });
        myApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // MainActivity.this.openFolder();
                //Intent i = new Intent(MainActivity.this, Main2Activity.class);
            //  startActivity(i);
            }

        });
        ST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserMainPage.class);
                UserMainPage.isCom = true;
                startActivity(i);
            }
        });



    }





}
