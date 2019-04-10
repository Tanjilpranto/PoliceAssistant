package com.example.user.policeassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostExpand extends AppCompatActivity {

    TextView CriminalName,Father,Mother,PresentAddress,Description,PermanentAddress,Rewards;
    String CName,Fname,Mname,Dst,description,permanentAddress,rewards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_expand);

        CriminalName=findViewById(R.id.nameboxcrim);
        Father=findViewById(R.id.nameboxfath);
        Mother=findViewById(R.id.nameboxmoth);
        PresentAddress=findViewById(R.id.nameboxpreadd);
        Description=findViewById(R.id.nameboxdescrip);
        PermanentAddress=findViewById(R.id.nameboxperadd);
        Rewards=findViewById(R.id.nameboxrewards);


        Fname=getIntent().getStringExtra("father");
        Dst=getIntent().getStringExtra("PresentAddress");
        description=getIntent().getStringExtra("Description");
        CName=getIntent().getStringExtra("name");
        Mname=getIntent().getStringExtra("mother");
        permanentAddress=getIntent().getStringExtra("PermanentAddress");
        rewards=getIntent().getStringExtra("rewards");

        CriminalName.setText(CName);
        Father.setText(Fname);
        PresentAddress.setText(Dst);
        Description.setText(description);
        Mother.setText(Mname);
        PermanentAddress.setText(permanentAddress);
        Rewards.setText(rewards);
    }
}
