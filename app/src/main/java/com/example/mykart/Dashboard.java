package com.example.mykart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    ArrayList listOfProducts;
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
                            System.out.println("MyProducts :"+storeProducts.get(0).getProductName());
                            listOfProducts=new ArrayList<>();
                            for(int i=0;i<storeProducts.size();i++){
                                ArrayList ab=new ArrayList<>();
                                ab.add(storeProducts.get(i).getId());
                                ab.add(storeProducts.get(i).getProductName());
                                ab.add(storeProducts.get(i).getDescription());
                                ab.add(storeProducts.get(i).getPrice());
                                ab.add(storeProducts.get(i).getDiscountPercentage());
                                ab.add(storeProducts.get(i).getRating());
                                ab.add(storeProducts.get(i).getStock());
                                ab.add(storeProducts.get(i).getBrand());
                                ab.add(storeProducts.get(i).getCategory());
                                ab.add(storeProducts.get(i).getThumbnails());
                                listOfProducts.add(ab);
                            }
                            System.out.println("MyProducts are:"+ listOfProducts);
                            ListAdapter listAdapter=new ArrayAdapter<>(Dashboard.this,android.R.layout.simple_list_item_1,listOfProducts);
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




    }


}
