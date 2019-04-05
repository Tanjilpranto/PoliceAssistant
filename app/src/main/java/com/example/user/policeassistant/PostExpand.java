package com.example.user.policeassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostExpand extends AppCompatActivity {

    TextView CriminalName;
    String CName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_expand);

        CriminalName=findViewById(R.id.nameboxcrim);
        CName=getIntent().getStringExtra("name");
        CriminalName.setText(CName);
    }
}
