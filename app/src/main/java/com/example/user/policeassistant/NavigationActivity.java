package com.example.user.policeassistant;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    StorageReference storageRef;
    private FloatingActionButton fab2;
    private DatabaseReference mdatabase;
    private String SplitUsername;
    private Button save;
    private DatabaseReference saveRefrence;

<<<<<<< HEAD
=======
    private RecyclerView mBloglist;
    private DatabaseReference mDatabase;
    private static Context context;
>>>>>>> Branch_pranto

    public String ur;

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
        storageRef= FirebaseStorage.getInstance().getReference();
        save=findViewById(R.id.savePost);

<<<<<<< HEAD
=======

>>>>>>> Branch_pranto
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

<<<<<<< HEAD
=======
       // View hg = getLayoutInflater().inflate(R.layout.activity_homepage_general, null);
        mDatabase= FirebaseDatabase.getInstance().getReference("Posts");
        mDatabase.keepSynced(true);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mBloglist=findViewById(R.id.recyclerId);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(mLinearLayoutManager);

        context = this;


        saveRefrence=FirebaseDatabase.getInstance().getReference("SavedPost");

>>>>>>> Branch_pranto
        drawerLayout= findViewById(R.id.drawerlayout);
        navigationView= findViewById(R.id.navigationview);

        navigationView.setNavigationItemSelectedListener(this);
        //fab2=findViewById(R.id.fab_submit_post2);
        header=navigationView.getHeaderView(0);

        TextView profilemail=header.findViewById(R.id.profileEmail);

        final CircleImageView proImage=header.findViewById(R.id.profileImage);

        profilemail.setText(email);

        storageRef.child(SplitUsername).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                ur= uri.toString();
                Glide.with(getApplicationContext()).load(ur).into(proImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

<<<<<<< HEAD
=======


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //saveRefrence.child(SplitUsername).setValue();
                Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
            }
        });







>>>>>>> Branch_pranto
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

<<<<<<< HEAD
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //some work if wish
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //some work if wish
                return true;
=======
>>>>>>> Branch_pranto
            }
        };

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setOnActionExpandListener(onActionExpandListener);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog,NavigationActivity.BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, NavigationActivity.BlogViewHolder>
                (Blog.class,R.layout.activity_list_item_row, NavigationActivity.BlogViewHolder.class,mDatabase){

            @Override
            protected void populateViewHolder(NavigationActivity.BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                //viewHolder.setDesc(model.getDescription());
                //viewHolder.setRewardable(model.getReward());
                viewHolder.setDist(model.getDist());
                viewHolder.setname(model.getName());
                viewHolder.setFather(model.getFather());
                viewHolder.setRewards(model.getReward());

            }

        };

        mBloglist.setAdapter(firebaseRecyclerAdapter);

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {

        View mview;
        public BlogViewHolder( View itemView)
        {
            super(itemView);
            mview=itemView;
            mview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //item onclick here

                    /*FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);*/
                    try{
                        Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
                    }catch(Exception e)
                    {
                        Toast.makeText(context,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        public void setTitle(String title)
        {
            TextView post_Title=mview.findViewById(R.id.post_title);
            post_Title.setText(title);
        }

        public void setDist(String dist)
        {
            TextView post_dist=mview.findViewById(R.id.districtname);
            post_dist.setText(dist);
        }

        public void setname(String name)
        {
            TextView post_name=mview.findViewById(R.id.fullname);
            post_name.setText(name);
        }

        public void setFather(String father)
        {
            TextView post_father=mview.findViewById(R.id.fatherfullname);
            post_father.setText(father);
        }

        public void setRewards(String rewards)
        {
            TextView post_reward=mview.findViewById(R.id.points);
            post_reward.setText(rewards);
        }


    }





    public void setuser(String user)
    {
        TextView textviewUser=header.findViewById(R.id.profileUsername);
        textviewUser.setText(user);
    }

    public void SetURL(String url)
    {

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
