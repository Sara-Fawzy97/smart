package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class chat extends AppCompatActivity {

    TabHost host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        host=findViewById(R.id.host);
        host.setup();

        //tab1 for Chat
        TabHost.TabSpec spec= host.newTabSpec("Chats");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Chats");
        host.addTab(spec);

        //tab2 for users
        spec=host.newTabSpec("Friends");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Friends");
        host.addTab(spec);

    }
}
