package com.example.user.policeassistant;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;


public class Post extends AppCompatActivity {

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;


    private ListView listView;
    private FloatingActionButton fab;
    private List<Information> list;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    public String mEmailAddress;
    public String user;
    String s="Posts";




    private EditText title;
    private EditText body;
    private EditText dist;
    private EditText Name;
    private EditText Father;
    String reward="100 XP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sign_in);

        list=new ArrayList<>();




        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        title = findViewById(R.id.field_title);
        body = findViewById(R.id.field_body);
        fab=findViewById(R.id.fab_submit_post);
        dist=findViewById(R.id.fieldDist);
        Name=findViewById(R.id.fieldName);
        Father=findViewById(R.id.fieldFather);

        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference2= FirebaseDatabase.getInstance().getReference();



        try {

            if (mFirebaseUser == null){
                //Not signed in, launch the Sign In Activity

                Toast.makeText(Post.this, "Null user", Toast.LENGTH_LONG).show();
                finish();
                return;
            }else {


                mEmailAddress = mFirebaseUser.getEmail();

                String[] parts = mEmailAddress.split("@");
                user=parts[0];

               // Toast.makeText(Post.this, user, Toast.LENGTH_LONG).show();


            }


           fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    String Title = title.getText().toString();
                    String Body = body.getText().toString();
                    String District=dist.getText().toString();
                    String name=Name.getText().toString();
                    String father=Father.getText().toString();


                    String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                    Information info = new Information(Title, Body,District,name,mydate,father,reward);



                    databaseReference.child(s).push().setValue(info);
                    databaseReference2.child("User Posts").child(user).push().setValue(info);

                    Toast.makeText(Post.this, "Post Saved", Toast.LENGTH_LONG).show();

                    title.setText("");
                    body.setText("");
                    dist.setText("");
                    Name.setText("");
                    Father.setText("");

                    Intent intent=new Intent(getApplicationContext(),Recycle.class);
                    startActivity(intent);

                    /*EmailVerification();
                    if(mFirebaseUser.isEmailVerified())
                    {
                        Toast.makeText(getApplicationContext(),"Email is Verified",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Email is not Verified",Toast.LENGTH_SHORT).show();
                    }*/






                }
            });
        }catch (Exception e)
        {
            Toast.makeText(Post.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        }

        public void EmailVerification()
        {
            mFirebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Email send",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Email send failed",Toast.LENGTH_SHORT).show();
                }
            });
        }


    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(),NavigationActivity.class);
        startActivity(intent);
        finish();
    }
    }

