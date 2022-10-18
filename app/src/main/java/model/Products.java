package model;

import java.net.URL;
import java.util.ArrayList;

public class Products {
    public String productName,description,brand,category,thumbnails;
    public int id,price,stock;
    public double discountPercentage,rating;
    public ArrayList  images;

    public Products(int id,String productName,String description,int price,double discountPercentage,double rating,int stock,String brand,String category,String thumbnails,ArrayList images){
        this.id=id;
        this.productName=productName;
        this.description=description;
        this.price=price;
        this.discountPercentage=discountPercentage;
        this.rating=rating;
        this.stock=stock;
        this.brand=brand;
        this.category=category;
        this.thumbnails=thumbnails;
        this.images=images;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public ArrayList getImages() {
        return images;
    }
}
