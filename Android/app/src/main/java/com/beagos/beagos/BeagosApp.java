package com.beagos.beagos;

import android.app.Application;

import com.beagos.beagos.utils.FontsOverride;

/**
 * Created by piyush0 on 11/02/17.
 */

public class BeagosApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/" + FontsOverride.FONT_PROXIMA_NOVA);

    }
}
