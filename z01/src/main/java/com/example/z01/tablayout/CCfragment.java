package com.example.z01.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.z01.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class CCfragment extends Fragment {

    private Banner banner;
    private List<String> list_path = new ArrayList<String>();
    private List<String> list_title = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment33, container, false);

        banner = view.findViewById(R.id.banner);

        iniData();

        return view;
    }

    private void iniData() {

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201410%2F05%2F20141005082835_2RTzn.thumb.700_0.jpeg&thumburl=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3846895839%2C2711067435%26fm%3D26%26gp%3D0.jpg");

        list_title.add("关关雎鸠");
        list_title.add("在河之洲");
        list_title.add("窈窕淑女");
        list_title.add("君子好逑");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new Imager());
        banner.setImages(list_path);
        banner.setBannerAnimation(Transformer.RotateDown);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(1500);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();

    }


    private class Imager implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load((String)path).into((ImageView)imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }


}
