package com.example.z01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.z01.R;
import com.example.z01.adapter.Upadapter;
import com.example.z01.tablayout.AAfragment;
import com.example.z01.tablayout.BBfragment;
import com.example.z01.tablayout.CCfragment;
import com.example.z01.tablayout.DDfragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Afragment extends Fragment {

    private TabLayout tab;
    private ViewPager viewpager;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<ChannelBean> stringList = new ArrayList<ChannelBean>();
    private Upadapter adapter;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        tab = view.findViewById(R.id.tab);
        viewpager = view.findViewById(R.id.viewpager);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),stringList);
            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iniData();
        adapter = new Upadapter(getChildFragmentManager(), fragmentList, stringList);
        viewpager.setAdapter(adapter);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(viewpager);

    }

    private void iniData() {

        stringList.add(new ChannelBean("首页",true));
        stringList.add(new ChannelBean("电影",true));
        stringList.add(new ChannelBean("音乐",true));
        stringList.add(new ChannelBean("购物",false));
        stringList.add(new ChannelBean("娱乐",false));
        stringList.add(new ChannelBean("社区",false));

        for (int i = 0; i<stringList.size();i++){


            ChannelBean bean = stringList.get(i);
            if (bean.isSelect()){
                tab.addTab(tab.newTab().setText(bean.getName()));
                if (i==0){
                    fragmentList.add(new AAfragment());
                }else if (i==1){
                    fragmentList.add(new BBfragment());
                }else if (i==2){
                    fragmentList.add(new CCfragment());
                }else if (i==3){
                    fragmentList.add(new DDfragment());
                }
            }

        }



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tab.removeAllTabs();
        fragmentList.clear();


        String stringExtra = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
        Gson gson = new Gson();
        Type type = new TypeToken<List<ChannelBean>>() {
        }.getType();
        stringList = gson.fromJson(stringExtra, type);

        for (int i = 0 ; i < stringList.size(); i ++){
            ChannelBean channelBean = stringList.get(i);
            if (channelBean.isSelect()){
                tab.addTab(tab.newTab().setText(channelBean.getName()));
                if ("首页".equals(channelBean.getName())){
                    fragmentList.add(new AAfragment());
                }else if ("电影".equals(channelBean.getName())){
                    fragmentList.add(new BBfragment());
                }else if ("音乐".equals(channelBean.getName())){
                    fragmentList.add(new CCfragment());
                }else {
                    fragmentList.add(new DDfragment());
                }


            }

        }
        adapter.notifyDataSetChanged();




    }



}
