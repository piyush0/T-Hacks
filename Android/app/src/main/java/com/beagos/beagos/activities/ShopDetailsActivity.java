package com.beagos.beagos.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beagos.beagos.R;
import com.beagos.beagos.models.Shop;
import com.beagos.beagos.utils.FontsOverride;
import com.beagos.beagos.utils.RequestHandler;
import com.google.gson.Gson;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import me.itangqi.waveloadingview.WaveLoadingView;

public class ShopDetailsActivity extends AppCompatActivity {

    Shop shop;
    private ImageView iv_shopPic;
    private TextView tv_name, tv_address;
    private SimpleRatingBar ratingBar;
    private String source;
    private FloatingActionButton btn_camera;
    WaveLoadingView loadingView;

    public static final String UPLOAD_KEY = "logo";
    public static final String UPLOAD_URL = "http://10.60.34.245:8000/get_score";

    private int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    private Bitmap bitmap;

    private Float score;

    public static final String TAG = "Shop";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Log.d(TAG, "onActivityResult: ");
//            filePath = data.getData();

//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            bitmap = (Bitmap) data.getExtras().get("data");

            iv_shopPic.setVisibility(View.GONE);
            tv_address.setVisibility(View.GONE);
            tv_name.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            btn_camera.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            uploadImage();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA, getWindow());
        btn_camera = (FloatingActionButton) findViewById(R.id.activity_shop_details_btn_camera);
        getShop();
        initViews();
    }

    private void getShop() {
        Intent i = getIntent();
        String json = i.getStringExtra("shop");
        this.shop = Shop.getShop(json);
        Log.d(TAG, "getShop: " + shop.getName());
    }

    private void initViews() {
        iv_shopPic = (ImageView) findViewById(R.id.activity_shop_details_iv_photo);
        loadingView = (WaveLoadingView) findViewById(R.id.activity_loading_waveLoading);
        tv_address = (TextView) findViewById(R.id.activity_shop_details_tv_address);
        tv_name = (TextView) findViewById(R.id.activity_shop_details_tv_name);
        ratingBar = (SimpleRatingBar) findViewById(R.id.activity_shop_details_rating_bar);
        ratingBar.setIndicator(true);
        initRatingBar();
        setDetails();

        int num = Integer.valueOf(shop.getUUID());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (num) {
                case 1:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.dtu));
                    break;
                case 2:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.coding_blocks));
                    break;
                case 3:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.diro));
                    break;
                case 4:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.laundromat));
                    break;
                case 5:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.reebok));
                    break;
                case 6:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.adidas));
                    break;
                case 7:
                    Log.d(TAG, "initViews: " + "kfc");
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.kfc));
                    break;
                case 8:
                    iv_shopPic.setImageDrawable(getDrawable(R.drawable.mcd));
                    break;

            }
        }
    }

    private void initRatingBar() {
        ratingBar.setStarSize(80);
        ratingBar.setNumberOfStars(5);
        ratingBar.setStepSize(0.1f);
        ratingBar.setBorderColor(getResources().getColor(R.color.colorPrimaryDark));
        ratingBar.setFillColor(getResources().getColor(R.color.colorAccent));
        ratingBar.setStarCornerRadius(10);
    }

    private void setDetails() {
        tv_name.setText(shop.getName());
        tv_address.setText(shop.getAddress());
        //TODO: Set pic
        ratingBar.setRating(calculateStars(shop.getRating()));

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d(TAG, "onClick: ");
                startActivityForResult(i, 1);
            }
        });
    }

    private float calculateStars(float rating) {
        float perc = rating / 100;
        return perc * 5;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loadingView.setProgressValue(50);
                Test test = Test.get(s);
                Log.d(TAG, "onPostExecute: " + test.getScore());
                score = Float.valueOf(test.getScore());
//
                sendIntent(score, test.getCompany());
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL, data);
                Log.d(TAG, "doInBackground: " + result);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);


    }


    public void sendIntent(Float score, String company) {
        Intent i = new Intent(ShopDetailsActivity.this, RatingLogoActivity.class);
        i.putExtra("Score", score);
        Log.d(TAG, "sendIntent: " + score);
        i.putExtra("Shop", Shop.getJson(this.shop));
        i.putExtra("Company", company);
        loadingView.setProgressValue(100);
        startActivity(i);
        finish();
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private static class Test {
        String score;
        String company;

        public static Test get(String json) {
            Gson gson = new Gson();
            return gson.fromJson(json, Test.class);
        }

        public Test(String score) {
            this.score = score;
        }

        public String getScore() {
            return score;
        }

        public String getCompany() {
            return company;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
