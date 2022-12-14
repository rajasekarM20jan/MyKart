package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class filters extends AppCompatActivity {
    Button all,smartPhones,laptops,fragrances,skincare,groceries,homeDecor;
    FloatingActionButton user,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        all=findViewById(R.id.all);
        smartPhones=findViewById(R.id.smartPhones);
        laptops=findViewById(R.id.laptops);
        fragrances=findViewById(R.id.fragrances);
        skincare=findViewById(R.id.skinCare);
        groceries=findViewById(R.id.groceries);
        homeDecor=findViewById(R.id.homeDecor);
        user=findViewById(R.id.userDetails);
        cart=findViewById(R.id.kart);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","all");
                startActivity(i);
            }
        });
        smartPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","smartphones");
                startActivity(i);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","laptops");
                startActivity(i);
            }
        });
        fragrances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","fragrances");
                startActivity(i);
            }
        });
        skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","skincare");
                startActivity(i);
            }
        });
        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","groceries");
                startActivity(i);
            }
        });
        homeDecor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(filters.this,Dashboard.class);
                i.putExtra("values","home-decoration");
                startActivity(i);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserPage();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    void getUserPage(){
        Intent i=new Intent(this,UserProfile.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

    }
}