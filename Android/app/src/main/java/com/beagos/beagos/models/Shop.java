package com.beagos.beagos.models;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by piyush0 on 11/02/17.
 */

public class Shop {

    String name;
    String UUID;
    String address;
    String imageURL;
    Float rating;


    public Shop() {
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Shop(String name, Float rating, String address, String UUID) {
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.UUID = UUID;
    }

    public Shop(String name, String UUID, String address, String imageURL) {
        this.name = name;
        this.UUID = UUID;
        this.address = address;
        this.imageURL = imageURL;
    }

    public static ArrayList<Shop> getDummyShops() {
        ArrayList<Shop> shops = new ArrayList<>();

        shops.add(new Shop("Deltech", 55.8f, "Rithala", "1"));
        shops.add(new Shop("Coding Blocks", 85.3f, "Pitampura", "2"));
        shops.add(new Shop("Diro Labs", 66.3f, "UK", "3"));
        shops.add(new Shop("Laundromat", 22.3f, "Rohini", "4"));
        shops.add(new Shop("Reebok", 22.3f, "Rohini", "5"));
        shops.add(new Shop("Adidas", 22.3f, "Rohini", "6"));
        shops.add(new Shop("Kfc", 22.3f, "Rohini", "7"));
        shops.add(new Shop("McDonalds", 22.3f, "Rohini", "8"));

        return shops;
    }

    public static String getJson(Shop shop) {
        Gson gson = new Gson();

        String json = gson.toJson(shop);
        return json;
    }

    public static Shop getShop(String json) {
        Gson gson = new Gson();
        Shop shop = gson.fromJson(json, Shop.class);
        return shop;
    }

}
