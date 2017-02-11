package com.beagos.beagos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beagos.beagos.R;
import com.beagos.beagos.adapters.NearbyShopsAdapter;
import com.beagos.beagos.models.Shop;
import com.beagos.beagos.utils.FontsOverride;

import java.util.ArrayList;

public class NearbyShopsActivity extends AppCompatActivity {

    ArrayList<Shop> shops;
    RecyclerView rv_shops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_shops);
        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA, getWindow());

        getShops();
        init();
    }

    public void getShops(){
        this.shops = Shop.getDummyShops();
    }

    public void init(){
        rv_shops = (RecyclerView) findViewById(R.id.activity_nearby_shops_rv);
        rv_shops.setAdapter(new NearbyShopsAdapter(this, shops));
        rv_shops.setLayoutManager(new LinearLayoutManager(this));
    }
}
