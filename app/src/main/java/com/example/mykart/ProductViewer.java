package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ProductViewer extends AppCompatActivity {
    TextView productName,description,price,rating,brand,category;
    ImageView image;
    ImageButton next,previous;
    String stock;
    ArrayList images;
    int count;
    int a=1;
    int b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        productName=findViewById(R.id.productNameinViewer);
        description=findViewById(R.id.descriptionInViewer);
        price=findViewById(R.id.priceInViewer);
        rating=findViewById(R.id.ratingInViewer);
        brand=findViewById(R.id.brandInViewer);
        category=findViewById(R.id.categoryInViewer);
        image=findViewById(R.id.imageViewInViewer);
        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
        Intent i=getIntent();
        String pname=i.getStringExtra("productName");
        String pdesc=i.getStringExtra("description");
        String prc=i.getStringExtra("price");
        String discount=i.getStringExtra("discountPercentage");
        String rate=i.getStringExtra("rating");
        stock=i.getStringExtra("stock");
        String pbrand=i.getStringExtra("brand");
        String pcategory=i.getStringExtra("category");
        images= i.getStringArrayListExtra("images");
        productName.setText(pname);
        description.setText(pdesc);
        price.setText(prc);
        rating.setText(rate);
        brand.setText(pbrand);
        category.setText(pcategory);
        String url=images.get(0).toString();
        myThread gt;
        new myThread(url);
        gt=new myThread(url);
        gt.et();
        image.setImageBitmap(gt.map);
        System.out.println("MyImages : "+images.get(0));
        count=images.size();
        System.out.println("MyImagesCount"+count);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNext();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToPrevious();
            }
        });
    }
    void moveToNext() {
        try {
        if(a>=count-1){
            a =0;
        }
        String url=images.get(a).toString();
        myThread gt;
        new myThread(url);
        gt=new myThread(url);
        gt.et();
        image.setImageBitmap(gt.map);
        a=a+1;
        }catch(Exception e){
            Toast.makeText(this, "No More Images...", Toast.LENGTH_SHORT).show();
        }
    }

    void moveToPrevious() {
        try {
            if (a <= 0) {
                a = count - 1;
            }
            String url = images.get(a).toString();
            myThread gt;
            new myThread(url);
            gt = new myThread(url);
            gt.et();
            image.setImageBitmap(gt.map);
            a = a - 1;
        }catch(Exception e){
            Toast.makeText(this, "No More Images...", Toast.LENGTH_SHORT).show();
        }
    }
}
class myThread extends Thread{
    String url;
    Bitmap map;
    myThread(String url){
        this.url=url;

    }
    public Bitmap et(){
        try{
            InputStream inputStream=new URL(url).openStream();
            map=BitmapFactory.decodeStream(inputStream);

        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
}








