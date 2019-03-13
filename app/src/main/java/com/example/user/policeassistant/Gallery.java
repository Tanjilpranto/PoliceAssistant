package com.example.user.policeassistant;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Gallery extends AppCompatActivity {

    ImageView userphoto;
    Button log;
    Button btn;
    static int PReqCode=1;
    static int REQUESCODE=1;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Uri pickedImageUri;

    ProgressDialog pd;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://police-assistant-d85ca.appspot.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        userphoto = findViewById(R.id.img);
        btn=findViewById(R.id.btn);
        log=findViewById(R.id.btnl);

        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");



        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                //Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(intent);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(pickedImageUri != null) {
                    pd.show();
                    String uid=user.getUid().toString();
                    StorageReference childRef = storageRef.child(uid);

                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(pickedImageUri);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(Gallery.this, "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(Gallery.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Gallery.this, "Select an image", Toast.LENGTH_SHORT).show();
                }
            }

            });





        userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=22)
                {
                    checkAndRequestForPermission();
                }else
                {
                    openGallery();
                }
            }
        });


    }

    private void openGallery() {

        Intent galleryIntent=new Intent((Intent.ACTION_GET_CONTENT));
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }

    private void checkAndRequestForPermission() {
        if(ContextCompat.checkSelfPermission(Gallery.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Gallery.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(Gallery.this,"Please accet permission",Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(Gallery.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }else{
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data !=null){


            pickedImageUri=data.getData();
            userphoto.setImageURI(pickedImageUri);


        }
    }
}

