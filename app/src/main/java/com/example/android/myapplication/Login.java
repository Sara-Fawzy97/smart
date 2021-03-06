package com.example.android.myapplication;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText user, pass;
    TextView invalid, sign, forget;
    Button log;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.pass);
        invalid = (TextView) findViewById(R.id.invalid);
        log = (Button) findViewById(R.id.login);
        sign = (TextView) findViewById(R.id.sign);

        ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users");

        //if user forget his password ,move toother page to reset it
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        // move to sign up page
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //when press on login button
    public void btn_login(View view) {
        final String username = user.getText().toString();
        final String password = pass.getText().toString();

        //hyro7 l firebase
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if the input exists as child for the ref
                if (dataSnapshot.child(username).exists()) {
                    Toast.makeText(getApplicationContext(), " exists", Toast.LENGTH_SHORT).show();

                    if (!username.isEmpty()) {
                        Info info = dataSnapshot.child(username).getValue(Info.class);
                        //if the password in data equal the input password
                        if (info.getPass().equals(password)) {
                            Toast.makeText(getApplicationContext(), "Log in successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //if the input not exists in the firebase
                else
                    Toast.makeText(getApplicationContext(), "Not exists", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
