package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAppStep5 extends AppCompatActivity {

    Button bulid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step5);

        bulid = (Button) findViewById(R.id.button3);
        bulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMainPage.isCom=true;
                finish();
            }
        });



    }


}
