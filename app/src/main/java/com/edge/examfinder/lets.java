package com.edge.examfinder;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class lets extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4;
    DatabaseReference myref;
    String uid;
    String good;
    ImageView imgIcon;
    Button btn1,btn2;
    String nm[]={"Maths","Commerce","Arts","Biology"};
    String stream1[]={"UNDER-GRADUATE-COURSES","POST-GRADUATE-COURSES","PHD-COURSES"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt33);
        txt4 = findViewById(R.id.txt44);
        imgIcon = findViewById(R.id.imageIcon);
        myref= FirebaseDatabase.getInstance().getReference();
        btn1=findViewById(R.id.check);
        btn2=findViewById(R.id.check1);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                uid=profile.getUid();
                txt1.setText(profile.getDisplayName());
                txt2.setText(profile.getEmail());
                Picasso.get().load(profile.getPhotoUrl()).fit().placeholder(R.drawable.logo_image).centerInside().into(imgIcon);
            }
        }
        myref.child(uid).child("Stream").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot ds: iterable) {
                    txt3.setText(ds.getValue().toString());
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myref.child(uid).child("Course").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot ds: iterable) {
                    txt4.setText(ds.getValue().toString());
                }
                progressDialog.cancel();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Toast.makeText(this, ""+fun.length, Toast.LENGTH_SHORT).show();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt3.getText().toString().equals(nm[0]))
                {
                    startActivity(new Intent(lets.this,math1.class));
                }
                else if(txt3.getText().toString().equals(nm[1]))
                {
                    startActivity(new Intent(lets.this,commerce1.class));
                }
                else if(txt3.getText().toString().equals(nm[2]))
                {
                    startActivity(new Intent(lets.this,arts1.class));
                }
                else if(txt3.getText().toString().equals(nm[3]))
                {
                    startActivity(new Intent(lets.this,bio1.class));
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lets.this,Check_Eligibility.class));
            }
        });

    }}