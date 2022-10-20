package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Profile;

public class UserProfile extends AppCompatActivity {
    TextView name,mobile,email,password,loginStatus;
    FloatingActionButton filter;
    ArrayList<Profile> userData;
    DbClassMyKart dbClassMyKart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name=findViewById(R.id.fullName);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginStatus=findViewById(R.id.loginStatus);
        filter=findViewById(R.id.filterPage);
        userData=new ArrayList<Profile>();
        dbClassMyKart=new DbClassMyKart(UserProfile.this);
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String myMobile=sp.getString("mobile","nomail");
        System.out.println("MyMobile : "+myMobile);
        dbClassMyKart.getData(myMobile);
        name.setText(dbClassMyKart.name);
        mobile.setText(dbClassMyKart.mobileNumber);
        email.setText(dbClassMyKart.email);
        password.setText(dbClassMyKart.password);
        loginStatus.setText(dbClassMyKart.login);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilterPage();
            }
        });
    }
    void getFilterPage(){
        Intent i= new Intent(this,filters.class);
        startActivity(i);
    }


}