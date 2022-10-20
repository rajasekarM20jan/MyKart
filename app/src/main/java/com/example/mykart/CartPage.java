package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartPage extends AppCompatActivity {

    ListView myKartList;
    FloatingActionButton backInCart;
    Button proceedToBuy;
    ArrayList arr;
    String product,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        myKartList=findViewById(R.id.listOfCart);
        backInCart=findViewById(R.id.backInCart);
        proceedToBuy=findViewById(R.id.buyInCart);
        arr=new ArrayList<>();
        Intent i =getIntent();
        try{
            product=i.getStringExtra("product");
            price=i.getStringExtra("price");
        }catch (Exception e){
            product=null;
            price=null;
        }
        if(!product.equals(null)){
            System.out.println("final product"+product);
            ArrayList a=new ArrayList<>();
            a.add(product);
            a.add(price);
            arr.add(a);
            proceedToBuy.setVisibility(View.VISIBLE);
        }else{
            System.out.println("final product is empty");
            proceedToBuy.setVisibility(View.INVISIBLE);
        }
        ListAdapter myCartList=new ArrayAdapter<>(CartPage.this, android.R.layout.simple_list_item_1,arr);
        myKartList.setAdapter(myCartList);
    }
}