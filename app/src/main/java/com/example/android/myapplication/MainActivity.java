package com.example.android.myapplication;

import android.content.Intent;
import android.icu.text.IDNA;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


    EditText  user,email, id, pass;
    Button sign;
    TextView login,note,chat;
DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          user=(EditText)findViewById(R.id.userName);
         email=(EditText)findViewById(R.id.email);
         id=(EditText)findViewById(R.id.id);
         pass=(EditText)findViewById(R.id.password);
        sign=(Button)findViewById(R.id.signUp);
        login=(TextView)findViewById(R.id.to);
        note=(TextView)findViewById(R.id.note);
        chat=findViewById(R.id.chat);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,chat.class);
                startActivity(intent);
            }
        });

        //identify firebase and get it reference
        ref=FirebaseDatabase .getInstance().getReference("Users");

        // move t0 login page
         login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSecond=new Intent(MainActivity.this,Login.class);
                startActivity(goToSecond);
            }
        });
    }

    //when press on register button
    public void btn_regi(View view)
    {
        final String userGet=user.getText().toString();
        final String emailGet=email.getText().toString();
        final String idGet= id.getText().toString();
        final String passGet=pass.getText().toString();

        // pattern which should the inputs match it
        final String userPattern="[A-Z]+[a-z]+ +[A-Z]+[a-z]+";
        final String email_pattern="[a-z0-9._-]+@[a-z]+.+[a-z]+";
        final String passPattern="[a-zA-Z0-9*_\\-/.!@#$%^&)(+=]+(8<n)+";

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//if any editText not fill
                if(userGet.isEmpty() ||emailGet.isEmpty()||idGet.isEmpty()||passGet.isEmpty())
                {note.setText("Please full every information ");}
                else  //if any of inputs don't matches the patterns
                    if (!emailGet.matches(email_pattern) )//|| !userGet.matches(userPattern)|| !passGet.matches(passPattern))
                          note.setText("Invalid email ");
                     else if (!userGet.matches(userPattern))
                         note.setText("user name should start with UpperCase");
                     else if (!passGet.matches(passPattern))
                         note.setText("Password wrong");
                    else {
//if the input username exists in firebase
                         if(dataSnapshot.child(userGet).exists())
                         { Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_LONG).show();}
//if it not exists,  new user
                        else{
                            Info info=new Info(userGet, passGet,emailGet,idGet);
                            ref.child(userGet).setValue(info);
                            Toast.makeText(getApplicationContext(),"Register Successfuly",Toast.LENGTH_LONG).show();
                         }
                     }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
