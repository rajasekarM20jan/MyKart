package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class filters extends AppCompatActivity {
    Button filter,all,smartPhones,laptops,fragrances,skincare,groceries,homeDecor;


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
    }
}