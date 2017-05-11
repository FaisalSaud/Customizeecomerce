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

import java.util.Locale;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String FIREBASE_URL = "https://***.firebaseio.com/";
    EditText username;
    EditText password;
    EditText rePassword;
    EditText phoneNo;
    EditText eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button Submit = (Button) findViewById(R.id.Submit_Create);
        mAuth = FirebaseAuth.getInstance();


    }

    public void showMessage(View view) {

        try {
            username = (EditText) findViewById(R.id.usernameText);     //get the text from .xml to .java
            String usernameS = username.getText().toString();    //get the text from data type "EditText" to "String"
            password = (EditText) findViewById(R.id.passwordText);
            final String passwordS = password.getText().toString();
            rePassword = (EditText) findViewById(R.id.rePasswordText);
            String rePasswordS = rePassword.getText().toString();
            phoneNo = (EditText) findViewById(R.id.phoneText);
            String phoneNoS = phoneNo.getText().toString();
            eMail = (EditText) findViewById(R.id.emailText);
            final String eMailS = eMail.getText().toString();


            //check if user name empty
            if (usernameS.isEmpty()) {
                if(Locale.getDefault().getLanguage().equals("ar"))
                    Toast.makeText(this, "إسم المستخدم فارغ", Toast.LENGTH_LONG).show();
                else
                Toast.makeText(this, "user name is empty please fill it", Toast.LENGTH_LONG).show();
                return;
            }

            //check if password empty
            if (passwordS.isEmpty()) {
                if(Locale.getDefault().getLanguage().equals("ar"))
                    Toast.makeText(this, "كلمة المرور فارغة", Toast.LENGTH_LONG).show();
                else
                Toast.makeText(this, "password is empty please fill it", Toast.LENGTH_LONG).show();
                return;
            }

            //skip the check for rePassword because it will check if it is == wit password

            //check if phone NO empty
            if (phoneNoS.isEmpty()) {
                if(Locale.getDefault().getLanguage().equals("ar"))
                    Toast.makeText(this, "رقم الهاتف فارغ", Toast.LENGTH_LONG).show();
                else
                Toast.makeText(this, "phone number is empty please fill it", Toast.LENGTH_LONG).show();
                return;
            }

            //check if email empty
            if (eMailS.isEmpty()) {
                if(Locale.getDefault().getLanguage().equals("ar"))
                    Toast.makeText(this, "البريد الإلكتروني فارغ", Toast.LENGTH_LONG).show();
                else
                Toast.makeText(this, "e-mail is empty please fill it", Toast.LENGTH_LONG).show();
                return;
            }

            if (((!passwordS.equals(rePasswordS)) || (passwordS.length() > 16) || (passwordS.length() < 6))) {
                if (!(passwordS.equals(rePasswordS))) {
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this, "كلمتا المرور غير متطابقتان", Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, " the passwords NOT match ", Toast.LENGTH_LONG).show();    //to show error message
                } else if (passwordS.length() > 16) {
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this, "كلمة المرور طويلة", Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, " the password is too long ", Toast.LENGTH_LONG).show();   //to show error message
                } else {
                    if(Locale.getDefault().getLanguage().equals("ar"))
                        Toast.makeText(this, "كلمة المرور قصيرة", Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(this, " the password is too short ", Toast.LENGTH_LONG).show();  //to show error message
                }
                return;
            } else {


                mAuth.createUserWithEmailAndPassword(eMailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(CreateAccount.this, StartPage.class);
                            this.clearFileds();
                            startActivity(i);
                            if(Locale.getDefault().getLanguage().equals("ar"))
                                Toast.makeText(CreateAccount.this, "مقبول", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(CreateAccount.this, " success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(CreateAccount.this, eMailS + " " + passwordS, Toast.LENGTH_LONG).show();
                            if(Locale.getDefault().getLanguage().equals("ar"))
                                Toast.makeText(CreateAccount.this, "لا يوجد إتصال", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(CreateAccount.this, " no connection", Toast.LENGTH_LONG).show();
                        }
                    }

                    public void clearFileds (){
                        try {
                            username.setText("");
                            password.setText("");
                            rePassword.setText("");
                            phoneNo.setText("");
                            eMail.setText("");
                        }catch (Exception e){
                            if(Locale.getDefault().getLanguage().equals("ar"))
                                Toast.makeText(CreateAccount.this, "حدثت مشكلة أثناء إفراغ الخانات", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(CreateAccount.this, "error in clearing the filed",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }

        } catch (Exception e) {
            if(Locale.getDefault().getLanguage().equals("ar"))
                Toast.makeText(CreateAccount.this, "يوجد خانات فارغة", Toast.LENGTH_LONG).show();
            else
            Toast.makeText(this, " missing field(s) ", Toast.LENGTH_LONG).show();
        }

    }

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
