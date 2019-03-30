package com.example.user.policeassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Recycle extends AppCompatActivity{

    private RecyclerView mBloglist;
    private DatabaseReference mDatabase;
    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_general);

        mDatabase= FirebaseDatabase.getInstance().getReference("Posts");
        mDatabase.keepSynced(true);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mBloglist=findViewById(R.id.recyclerId);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(mLinearLayoutManager);

        context = this;


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog,BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog,BlogViewHolder>
                (Blog.class,R.layout.activity_list_item_row,BlogViewHolder.class,mDatabase){

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
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
                        //Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
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

}
