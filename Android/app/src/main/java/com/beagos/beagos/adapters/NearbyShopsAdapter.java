package com.beagos.beagos.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beagos.beagos.R;
import com.beagos.beagos.activities.ShopDetailsActivity;
import com.beagos.beagos.models.Shop;

import java.util.ArrayList;

/**
 * Created by piyush0 on 11/02/17.
 */

public class NearbyShopsAdapter extends RecyclerView.Adapter<NearbyShopsAdapter.ShopViewHolder> {

    Context context;
    ArrayList<Shop> shops;

    public NearbyShopsAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.item_nearby_shops, parent, false);

        ShopViewHolder shopViewHolder = new ShopViewHolder(view);

        shopViewHolder.tv_shopName = (TextView) view.findViewById(R.id.item_nearby_shop_tv_name);
        shopViewHolder.iv_shop = (ImageView) view.findViewById(R.id.item_nearby_shop_iv_pic);

        return shopViewHolder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        final Shop shop = shops.get(position);

        holder.tv_shopName.setText(shop.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int num = Integer.valueOf(shop.getUUID());
            switch (num) {
                case 1:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.dtu));
                    break;
                case 2:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.coding_blocks));
                    break;
                case 3:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.diro));
                    break;
                case 4:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.laundromat));
                    break;
                case 5:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.reebok));
                    break;
                case 6:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.adidas));
                    break;
                case 7:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.kfc));
                    break;
                case 8:
                    holder.iv_shop.setImageDrawable(context.getDrawable(R.drawable.mcd));
                    break;

            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ShopDetailsActivity.class);
                i.putExtra("shop", Shop.getJson(shop));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_shop;
        TextView tv_shopName;

        public ShopViewHolder(View itemView) {
            super(itemView);
        }
    }
}
