package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createNewApp = (Button) findViewById(R.id.createApp);
        Button myApp = (Button) findViewById(R.id.myApp);
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
                startActivity(intent);
//                startActivities(intents);
            }
        });
        ST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserMainPage.class);
                startActivity(i);
            }
        });

//        myApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, MyApp.class);
//                startActivity(i);
//            }
//        });
//        howToStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, HowToStart.class);
//                startActivity(i);
//            }
//        });

    }
}
