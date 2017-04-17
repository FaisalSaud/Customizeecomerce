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
    EditText bar1;
    EditText bar2;
    EditText bar3;
    EditText bar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step3);

        cont =1;    //to count the number of "EditText" for Bars
        Button next = (Button) findViewById(R.id.nextButton);
         bar1= (EditText) findViewById(R.id.bar1Text);
         bar2= (EditText) findViewById(R.id.bar2Text);
         bar3= (EditText) findViewById(R.id.bar3Text);
         bar4= (EditText) findViewById(R.id.bar4Text);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAppStep3.this, CreateAppStep4.class);

                UserMainPage.Bar1.setText(bar1.getText());
                startActivity(i);
                finish();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addBar(View view){
        if (cont==1) {
            bar2.setVisibility(View.VISIBLE);

            Button removeBarButton = (Button) findViewById(R.id.removeBarButton);
            removeBarButton.setVisibility(View.VISIBLE);

        }
        else if (cont==2) {
            bar3.setVisibility(View.VISIBLE);

        }
        else if (cont==3) {
            bar4.setVisibility(View.VISIBLE);
            Toast.makeText(this, "you have reached the bar limit", Toast.LENGTH_LONG).show();
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.INVISIBLE);

        }
        cont++;

    }
    public void remorveBar(View view){
        if (cont==2) {
            bar2.setVisibility(View.GONE);
            Button removeBarButton = (Button) findViewById(R.id.removeBarButton);
            removeBarButton.setVisibility(View.GONE);
           // Toast.makeText(this, "you have reached the minim", Toast.LENGTH_LONG).show();
        }
        else if (cont==3) {
            bar3.setVisibility(View.GONE);
        }
        else if (cont==4) {
            bar4.setVisibility(View.GONE);
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.INVISIBLE);
        }
        cont--;



    }
}
