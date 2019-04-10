package com.example.user.policeassistant;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;

public class PostExpand extends AppCompatActivity {

    TextView CriminalName,Father,Mother,PresentAddress,Description,PermanentAddress,Rewards;
    private ImageView Criminalpic;
    String CName,Fname,Mname,Dst,description,permanentAddress,rewards,title;
    public String url;

    FirebaseStorage storage1 = FirebaseStorage.getInstance();
    StorageReference DownRef1 = storage1.getReferenceFromUrl("gs://police-assistant-d85ca.appspot.com/PostImage");
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
        Criminalpic=findViewById(R.id.criminalpic);


        Fname=getIntent().getStringExtra("father");
        Dst=getIntent().getStringExtra("PresentAddress");
        description=getIntent().getStringExtra("Description");
        CName=getIntent().getStringExtra("name");
        Mname=getIntent().getStringExtra("mother");
        permanentAddress=getIntent().getStringExtra("PermanentAddress");
        rewards=getIntent().getStringExtra("rewards");
        title=getIntent().getStringExtra("Title");

        CriminalName.setText(CName);
        Father.setText(Fname);
        PresentAddress.setText(Dst);
        Description.setText(description);
        Mother.setText(Mname);
        PermanentAddress.setText(permanentAddress);
        Rewards.setText(rewards);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String[] parts = user.getEmail().toString().split("@");
        String img = parts[0];
        String imageDown=img+title;


        DownRef1.child(imageDown).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                url= uri.toString();
                Glide.with(getApplicationContext()).load(url).into(Criminalpic);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
