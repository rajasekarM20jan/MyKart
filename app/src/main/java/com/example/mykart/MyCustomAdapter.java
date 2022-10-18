package com.example.mykart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.Products;

public class MyCustomAdapter extends ArrayAdapter<Products> {
    Context context;
    int resource;
    List<Products> storeProducts;
    public MyCustomAdapter(Context context, int resource, List<Products> storeProducts){
        super(context,resource,storeProducts);
        this.context=context;
        this.resource=resource;
        this.storeProducts=storeProducts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=LayoutInflater.from(context);
        View  view=inflator.inflate(resource,null);

        ImageView thumbnail=view.findViewById(R.id.thumbnail);
        TextView productName=view.findViewById(R.id.productName);
        TextView price=view.findViewById(R.id.price);

        Products product=storeProducts.get(position);
        System.out.println(product.getProductName());
        System.out.println(String.valueOf(product.getPrice()));
        System.out.println(product.getThumbnails());
        productName.setText(product.getProductName());
        price.setText("₹ "+String.valueOf(product.getPrice()));
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    String url=product.getThumbnails();
                    System.out.println("MyThumbnail: "+url);
                    InputStream inputStream=new URL(url).openStream();
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    System.out.println("MyThumbnail: "+bitmap);
                    thumbnail.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        myThread.start();
        return view;
    }
}
