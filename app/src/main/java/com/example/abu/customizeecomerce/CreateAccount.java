package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String FIREBASE_URL="https://***.firebaseio.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button Submit = (Button) findViewById(R.id.Submit_Create);
        mAuth = FirebaseAuth.getInstance();





    }

    public void showMessage (View view){

        try {
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


            //check if user name empty
            if (usernameS.isEmpty()){
                Toast.makeText(this,"user name is empty please fill it",Toast.LENGTH_LONG).show();
                return;
            }

            //check if password empty
            if (passwordS.isEmpty()){
                Toast.makeText(this,"password is empty please fill it",Toast.LENGTH_LONG).show();
                return;
            }

            //skip the check for rePassword because it will check if it is == wit password

            //check if phone NO empty
            if (phoneNoS.isEmpty()){
                Toast.makeText(this,"phone number is empty please fill it",Toast.LENGTH_LONG).show();
                return;
            }

            //check if email empty
            if (eMailS.isEmpty()){
                Toast.makeText(this,"e-mail is empty please fill it",Toast.LENGTH_LONG).show();
                return;
            }

            if(((!passwordS.equals(rePasswordS)) || ( passwordS.length() > 16)||(passwordS.length() < 5))){
                if(!(passwordS.equals(rePasswordS))) {
                    Toast.makeText(this, " the passwords NOT match ", Toast.LENGTH_LONG).show();    //to show error message
                }else if ( passwordS.length() > 16){
                    Toast.makeText(this, " the password is too long ", Toast.LENGTH_LONG).show();   //to show error message
                }else{
                    Toast.makeText(this, " the password is too short ", Toast.LENGTH_LONG).show();  //to show error message
                }
                return;
            }else{


                mAuth.createUserWithEmailAndPassword(eMailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent i = new Intent(CreateAccount.this, StartPage.class);
                        startActivity(i);
                        Toast.makeText(CreateAccount.this, " success",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(CreateAccount.this," no connection",Toast.LENGTH_LONG).show();

                    }
                });
            }
        }catch (Exception e){
            Toast.makeText(this," missing field(s) ",Toast.LENGTH_LONG).show();
        }

    }
    //فخ يثمثفث
    //to git out of the method


    // }else if(true){
    //check if the username is taken
    //Toast.makeText(this , " the usename is already taken " , Toast.LENGTH_LONG).show();
    //return;
    //}else if(true){
    //check if the phoneNo is taken
    //Toast.makeText(this , " the phone number is already have account " , Toast.LENGTH_LONG).show();
    //return;
    //}else if(true){
    //check if the E-Mail is taken
//            Toast.makeText(this , " this E-Mail : "+ eMailS +" is alrady have account " , Toast.LENGTH_LONG).show();
//            return;
}
