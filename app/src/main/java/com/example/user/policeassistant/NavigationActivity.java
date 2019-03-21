package com.example.user.policeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    StorageReference storageReference;
    private FloatingActionButton fab2;
    private DatabaseReference mdatabase;
    private String SplitUsername;

    NavigationView navigationView;
    String Usernameprofile;
    View header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

        String[] part =email.split("@");
        SplitUsername=part[0];

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdatabase=FirebaseDatabase.getInstance().getReference("Users");
        storageReference= FirebaseStorage.getInstance().getReference();

        mdatabase.child(SplitUsername).child("Username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usernameprofile=dataSnapshot.getValue().toString();
                setuser(Usernameprofile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        drawerLayout= findViewById(R.id.drawerlayout);
        navigationView= findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
        fab2=findViewById(R.id.fab_submit_post2);
        header=navigationView.getHeaderView(0);

        TextView profilemail=header.findViewById(R.id.profileEmail);


        profilemail.setText(email);



        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Post.class);
                startActivity(intent);
                finish();




            }
        });


    }

    public void setuser(String user)
    {
        TextView textviewUser=header.findViewById(R.id.profileUsername);
        textviewUser.setText(user);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.contact:
                //some work..............................
                break;
            case R.id.rateus:
                //some work.............................
                break;
            case R.id.search:
                //some work.............................
                break;
            case android.R.id.home:
                //some work...........................
                finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();
        switch (id){
            case R.id.profile:
                //some work..............................
                break;
            case R.id.savedposts:
                //some work.............................
                break;
            case R.id.rewards:
                //some work.............................
                Intent intent=new Intent(getApplicationContext(),Recycle.class);
                startActivity(intent);
                finish();

                break;
            case R.id.settings:
                //some work...........................
                break;
            case R.id.logout:
                //some work..............................
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
                finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}
