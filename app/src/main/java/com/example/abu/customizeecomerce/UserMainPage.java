package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        Button Bar1 = (Button) findViewById(R.id.Bar1);
        Button Bar2 = (Button) findViewById(R.id.Bar2);
        Button Bar3 = (Button) findViewById(R.id.Bar3);
        Button Bar4 = (Button) findViewById(R.id.Bar4);
        TextView PromoSlides = (TextView) findViewById(R.id.textView42);
        Bar1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent i = new Intent(UserMainPage.this, Bar1.class);
            startActivity(i);
            }
        });
        Bar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar2.class);
                startActivity(i);
            }
        });
        Bar3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar3.class);
                startActivity(i);
            }
        });
        Bar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserMainPage.this, Bar4.class);
                startActivity(i);
            }
        });
    }
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_page,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
