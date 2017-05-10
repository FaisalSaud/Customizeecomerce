package com.example.abu.customizeecomerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class CreateAppStep3 extends AppCompatActivity {
    public static int cont ;
    public static EditText bar1;
    public static EditText bar2;
    public static EditText bar3;
    public static EditText bar4;

    public static Activity step3;//test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app_step3);
        step3=this; //test

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
                barsOnMainPage();
                startActivity(i);
                if(UserMainPage.isCom)
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
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(this, "لقد وصلت للحد الأعلى", Toast.LENGTH_LONG).show();
            else
            Toast.makeText(this, "you have reached the bar limit", Toast.LENGTH_LONG).show();
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.INVISIBLE);

        }
        cont++;

    }
    public void remorveBar(View view){
        if (cont==2) {
            bar2.setVisibility(View.INVISIBLE);
            Button removeBarButton = (Button) findViewById(R.id.removeBarButton);
            removeBarButton.setVisibility(View.INVISIBLE);
           // Toast.makeText(this, "you have reached the minim", Toast.LENGTH_LONG).show();
        }
        else if (cont==3) {
            bar3.setVisibility(View.INVISIBLE);
        }
        else if (cont==4) {
            bar4.setVisibility(View.INVISIBLE);
            Button addBarButton = (Button) findViewById(R.id.addBarButton);
            addBarButton.setVisibility(View.VISIBLE);
        }
        cont--;

    }
    public void barsOnMainPage() {
        if(cont == 4){
            UserMainPage.Bar1.setText(bar1.getText());
            UserMainPage.Bar2.setText(bar2.getText());
            UserMainPage.Bar3.setText(bar3.getText());
            UserMainPage.Bar4.setText(bar4.getText());}
        else if (cont == 3) {
            UserMainPage.Bar1.setText(bar1.getText());
            UserMainPage.Bar2.setText(bar2.getText());
            UserMainPage.Bar3.setText(bar3.getText());
            UserMainPage.Bar4.setVisibility(View.INVISIBLE);
      } else if (cont == 2){
            UserMainPage.Bar1.setText(bar1.getText());
            UserMainPage.Bar2.setText(bar2.getText());
            UserMainPage.Bar3.setVisibility(View.INVISIBLE);
            UserMainPage.Bar4.setVisibility(View.INVISIBLE);
        } else{
            UserMainPage.Bar1.setText(bar1.getText());
            UserMainPage.Bar2.setVisibility(View.INVISIBLE);
            UserMainPage.Bar3.setVisibility(View.INVISIBLE);
            UserMainPage.Bar4.setVisibility(View.INVISIBLE);
        }


    }
}
