package me.haxzie.imagezoomer;

import android.app.Application;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

public class ImageZoomer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Glide.init(getApplicationContext(), new GlideBuilder().setLogLevel(Log.VERBOSE));
    }
}
