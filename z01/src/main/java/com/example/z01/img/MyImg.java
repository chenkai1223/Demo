package com.example.z01.img;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.z01.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;

public class MyImg extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .displayer(new RoundedBitmapDisplayer(360))
                .build();
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/images");

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .diskCache(new UnlimitedDiskCache(file))
                .build();

        ImageLoader instance = ImageLoader.getInstance();
        instance.init(imageLoaderConfiguration);

    }

    /***
     * 头像
     * @return
     */
    public static DisplayImageOptions imageOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(360))
                .build();
        return options;
    }
}

