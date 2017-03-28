package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAppStep3 extends AppCompatActivity {
    int cont ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step3);

        cont =1;    //to count the number of "EditText" for Bars
        Button next = (Button) findViewById(R.id.nextButton);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep3.this, CreateAppStep4.class);
                startActivity(i);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addBar(View view){
        if (cont==1) {
            EditText a = (EditText) findViewById(R.id.bar2);
            a.setVisibility(View.VISIBLE);

            Button removeBarButton = (Button) findViewById(R.id.removeBarButton);
            removeBarButton.setVisibility(View.VISIBLE);

        }
        else if (cont==2) {
            EditText a = (EditText) findViewById(R.id.bar3);
            a.setVisibility(View.VISIBLE);

        }
        else if (cont==3) {
            EditText a = (EditText) findViewById(R.id.bar44);
            a.setVisibility(View.VISIBLE);
            Toast.makeText(this, "you have reached the bar limit", Toast.LENGTH_LONG).show();
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.GONE);

        }
        cont++;

    }
    public void remorveBar(View view){
        if (cont==2) {
            EditText a = (EditText) findViewById(R.id.bar2);
            a.setVisibility(View.GONE);
            Button removeBarButton = (Button) findViewById(R.id.removeBarButton);
            removeBarButton.setVisibility(View.GONE);
           // Toast.makeText(this, "you have reached the minim", Toast.LENGTH_LONG).show();
        }
        else if (cont==3) {
            EditText a = (EditText) findViewById(R.id.bar3);
            a.setVisibility(View.GONE);
        }
        else if (cont==4) {
            EditText a = (EditText) findViewById(R.id.bar44);
            a.setVisibility(View.GONE);
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.VISIBLE);
        }
        cont--;



    }
}
