package com.example.abu.customizeecomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button Submit = (Button) findViewById(R.id.Submit_Create);
//        Submit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext() , "Work in progress ...",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void showMessage (View view){
        EditText username = (EditText) findViewById(R.id.usernameText);     //get the text from .xml to .java
        String usernameS = username.getText().toString();    //get the text from data type "EditText" to "String"
        EditText password = (EditText) findViewById(R.id.passwordText);
        String passwordS = password.getText().toString();
        EditText rePassword = (EditText) findViewById(R.id.rePasswordText);
        String rePasswordS = rePassword.getText().toString();
        EditText phoneNo = (EditText) findViewById(R.id.phoneText);
        String phoneNoS = phoneNo.getText().toString();
        EditText eMail = (EditText) findViewById(R.id.emailText);
        String eMailS = eMail.getText().toString();


        if(!(passwordS.equals(rePasswordS)) && ( passwordS.length() > 16) && (passwordS.length() < 5)){
            if(!(passwordS.equals(rePasswordS))) {
                Toast.makeText(this, " the passwords NOT match ", Toast.LENGTH_LONG).show();    //to show error message
            }else if ( passwordS.length() > 16){
                Toast.makeText(this, " the password is too long ", Toast.LENGTH_LONG).show();   //to show error message
            }else{
                Toast.makeText(this, " the password is too short ", Toast.LENGTH_LONG).show();  //to show error message
            }
            return;     //to git out of the method
        }else if(true){
            //check if the username is taken
            //Toast.makeText(this , " the usename is already taken " , Toast.LENGTH_LONG).show();
            //return;
        }else if(true){
            //check if the phoneNo is taken
            //Toast.makeText(this , " the phone number is already have account " , Toast.LENGTH_LONG).show();
            //return;
        }else if(true){
            //check if the E-Mail is taken
            //Toast.makeText(this , " this E-Mail : "+ eMailS +" is alrady have account " , Toast.LENGTH_LONG).show();
            //return;
        }else{
            //add the account to the database OR crate the account
        }

    }
}
