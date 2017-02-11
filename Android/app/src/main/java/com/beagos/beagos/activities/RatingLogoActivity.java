package com.beagos.beagos.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.beagos.beagos.R;
import com.beagos.beagos.models.Shop;
import com.beagos.beagos.utils.FontsOverride;
import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;
import com.github.anastr.speedviewlib.ImageSpeedometer;
import com.github.anastr.speedviewlib.TubeSpeedometer;

public class RatingLogoActivity extends AppCompatActivity {

    public static final String TAG = "Rating";

    private TextView tv_rating, tv_brand, tv_brandName;
    private ImageSpeedometer tubeSpeedometer;
    private Float rating;
    Shop shop;
    private Handler handler;



    String company;

    private CircleProgressView mCircleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_logo);
        FontsOverride.applyFontForToolbarTitle(this, FontsOverride.FONT_PROXIMA_NOVA, getWindow());
        getShop();
        handler = new Handler();
        getRating();

        Log.d(TAG, "onCreate: " + this.shop.getName());
    }

    public void getShop(){
        this.company = getIntent().getStringExtra("Company");
        Log.d(TAG, "getShop: " + this.company);
        this.shop = Shop.getShop(getIntent().getStringExtra("Shop"));
    }

    private void getRating() {

        this.rating = getIntent().getFloatExtra("Score", 0);
        initViews();
    }

    private void initViews() {
        tv_rating = (TextView) findViewById(R.id.activity_rating_logo_tv_rating);
//        tv_brand = (TextView) findViewById(R.id.activity_rating_logo_brand);
//        tv_brandName = (TextView) findViewById(R.id.activity_rating_logo_brand_name);
//        tubeSpeedometer = (ImageSpeedometer) findViewById(R.id.activity_rating_logo_speedometer);
//        tubeSpeedometer.setIndicatorWidth(59f);
//        tubeSpeedometer.setMaxSpeed(100);
//        tubeSpeedometer.speedTo(this.rating.intValue());
        handler.postDelayed(runnable, 1500);

        mCircleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_view);
        mCircleProgressView.setTextEnabled(false);
        mCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleProgressView.setProgressWithAnimation(this.rating,1500);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            tubeSpeedometer.setWithTremble(false);
            tv_rating.setVisibility(View.VISIBLE);
//            tv_brand.setVisibility(View.VISIBLE);
//            tv_brandName.setVisibility(View.VISIBLE);
            tv_rating.setText(String.valueOf(rating).substring(0,5) + " %");
//            StringBuilder sb = new StringBuilder(company);
//            if(sb.charAt(0)>= 'a' && sb.charAt(0)<='z'){
//                char x = ((char)(sb.charAt(0) - 32));
//                sb.setCharAt(0,x);
//            }
//            tv_brandName.setText(sb.toString());

            //TODO: Increase rating of the shop

        }
    };


}
