package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    private FirebaseAuth con;
    EditText email;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button LogIn = (Button) findViewById(R.id.Login);
        con = FirebaseAuth.getInstance();

    }
    public void login(View view){
        try {
            email = (EditText) findViewById(R.id.email);
            pass = (EditText) findViewById(R.id.password);
            con.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(Login.this, MainActivity.class);
                        email.setText("");
                        pass.setText("");
                        startActivity(i);
                    } else
                        Toast.makeText(Login.this, "Wrong email/password", Toast.LENGTH_LONG).show();
                }
            });

        }


       catch (Exception e){
        Toast.makeText(this," missing field(s) ",Toast.LENGTH_LONG).show();
        }

    }
}
