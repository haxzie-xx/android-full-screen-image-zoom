package me.haxzie.imagezoomer;

import android.app.Application;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;

public class ImageZoomer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlideImageLoader.with(getApplicationContext());
    }
}
