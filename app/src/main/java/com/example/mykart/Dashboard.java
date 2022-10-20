package com.example.mykart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Products;

public class Dashboard extends AppCompatActivity {
    String productName,description,brand,category,thumbnails;
    int id,price,stock;
    double discountPercentage,rating;
    ArrayList<Products> storeProducts;
    ListView productList;
    RequestQueue requestQueue;
    SearchView search;
    Button filter,userProfile;
    String values="all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        requestQueue= Volley.newRequestQueue(this);
        storeProducts=new ArrayList<>();
        search=findViewById(R.id.mySearch);
        productList=findViewById(R.id.productList);
        filter=findViewById(R.id.filters);
        userProfile=findViewById(R.id.userProfile);
        Intent i=getIntent();
        String a;
        try{
            a=i.getStringExtra("values");
        }
        catch (Exception e){
            a="all";
        }
        values=a;
        String url="https://dummyjson.com/products";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("products");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject products=jsonArray.getJSONObject(i);
                                id= (int) products.get("id");
                                productName = (String) products.get("title");
                                description=(String) products.get("description");
                                price=(int) products.get("price");
                                discountPercentage=(double) products.get("discountPercentage");
                                rating= Double.parseDouble(products.get("rating").toString());
                                stock=(int) products.get("stock");
                                brand=(String) products.get("brand");
                                category=(String) products.get("category");
                                thumbnails=(String) products.get("thumbnail");
                                JSONArray img=(JSONArray)products.get("images");
                                ArrayList images=new ArrayList<>();
                                for(int j=0;j<img.length();j++){
                                    String jobj= (String) img.get(j);
                                    images.add(jobj);
                                }
                                System.out.println("My Products are1:"+id+productName+description+price+discountPercentage+rating+stock+brand+category+thumbnails+ images);
                                storeProducts.add(new Products(id,productName,description,price,discountPercentage,rating,stock,brand,category,thumbnails,images));
                            }
                            ArrayList<Products> myFilter=new ArrayList<Products>();
                            for (Products myFilters: storeProducts) {
                                if (values.equals("all")){
                                    myFilter.add(myFilters);
                                }else if(myFilters.getCategory().toLowerCase().equals(values.toLowerCase())){
                                    myFilter.add(myFilters);

                                }
                            }
                            MyCustomAdapter listAdapt=new MyCustomAdapter(Dashboard.this,R.layout.my_custom_layout,myFilter);
                            productList.setAdapter(listAdapt);
                            productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    Intent i=new Intent(Dashboard.this,ProductViewer.class);
                                    i.putExtra("productName",myFilter.get(position).getProductName());
                                    i.putExtra("description",myFilter.get(position).getDescription());
                                    String prc= String.valueOf(myFilter.get(position).getPrice());
                                    i.putExtra("price",prc);
                                    String discount=String.valueOf(myFilter.get(position).getDiscountPercentage());
                                    i.putExtra("discountPercentage",discount);
                                    String ratings=String.valueOf(myFilter.get(position).getRating());
                                    i.putExtra("rating",ratings);
                                    String stocks=String.valueOf(myFilter.get(position).getStock());
                                    i.putExtra("stock",stocks);
                                    i.putExtra("brand",myFilter.get(position).getBrand());
                                    i.putExtra("category",myFilter.get(position).getCategory());
                                    ArrayList img=myFilter.get(position).getImages();
                                    i.putExtra("images",img);
                                    System.out.println("MyProductsSearch :"+myFilter.get(position).getImages());
                                    startActivity(i);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);



        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Products> mySearch=new ArrayList<Products>();
                for (Products item: storeProducts) {
                    if(item.getProductName().toLowerCase().contains(s.toLowerCase())){
                        mySearch.add(item);
                    }
                }
                MyCustomAdapter listAdapter=new MyCustomAdapter(Dashboard.this,R.layout.my_custom_layout,mySearch);
                productList.setAdapter(listAdapter);
                productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent i=new Intent(Dashboard.this,ProductViewer.class);
                        i.putExtra("productName",mySearch.get(position).getProductName());
                        i.putExtra("description",mySearch.get(position).getDescription());
                        String prc= String.valueOf(mySearch.get(position).getPrice());
                        i.putExtra("price",prc);
                        String discount=String.valueOf(mySearch.get(position).getDiscountPercentage());
                        i.putExtra("discountPercentage",discount);
                        String ratings=String.valueOf(mySearch.get(position).getRating());
                        i.putExtra("rating",ratings);
                        String stocks=String.valueOf(mySearch.get(position).getStock());
                        i.putExtra("stock",stocks);
                        i.putExtra("brand",mySearch.get(position).getBrand());
                        i.putExtra("category",mySearch.get(position).getCategory());
                        ArrayList img=mySearch.get(position).getImages();
                        i.putExtra("images",img);
                        System.out.println("MyProductsSearch :"+mySearch.get(position).getImages());
                        startActivity(i);
                    }
                });
                return false;
            }
        });


        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i=new Intent(Dashboard.this,ProductViewer.class);
                i.putExtra("productName",storeProducts.get(position).getProductName());
                i.putExtra("description",storeProducts.get(position).getDescription());
                String prc= String.valueOf(storeProducts.get(position).getPrice());
                i.putExtra("price",prc);
                String discount=String.valueOf(storeProducts.get(position).getDiscountPercentage());
                i.putExtra("discountPercentage",discount);
                String ratings=String.valueOf(storeProducts.get(position).getRating());
                i.putExtra("rating",ratings);
                String stocks=String.valueOf(storeProducts.get(position).getStock());
                i.putExtra("stock",stocks);
                i.putExtra("brand",storeProducts.get(position).getBrand());
                i.putExtra("category",storeProducts.get(position).getCategory());
                ArrayList img=storeProducts.get(position).getImages();
                i.putExtra("images",img);
                System.out.println("MyProducts :"+storeProducts.get(position).getImages());
                startActivity(i);

            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getfilterPage();
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserPage();
            }
        });
    }
    void getfilterPage(){
        Intent i=new Intent(this,filters.class);
        startActivity(i);
    }
    void getUserPage(){
        Intent i=new Intent(this,UserProfile.class);
        startActivity(i);
    }
}
