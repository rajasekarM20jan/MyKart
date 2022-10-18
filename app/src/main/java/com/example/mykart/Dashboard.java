package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        requestQueue= Volley.newRequestQueue(this);
        storeProducts=new ArrayList<>();
        productList=findViewById(R.id.productList);
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
                            System.out.println("MyProducts :"+storeProducts.get(0).getImages());
                            MyCustomAdapter listAdapter=new MyCustomAdapter(Dashboard.this,R.layout.my_custom_layout,storeProducts);
                            productList.setAdapter(listAdapter);

                            System.out.println(id+productName+description+price+discountPercentage+rating+stock+brand+category);
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
    }
}
