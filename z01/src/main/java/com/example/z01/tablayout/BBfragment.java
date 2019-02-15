package com.example.z01.tablayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.xlistviewlibrary.view.XListView;
import com.example.z01.R;
import com.example.z01.adapter.MAdapter;
import com.example.z01.bean.PerBean;
import com.example.z01.dao.UserDao;
import com.example.z01.utils.NewWordUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BBfragment extends Fragment {

    String urlstring = "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?page=1&count=10";
    int page;
    private XListView xlistview;
    private List<PerBean.ResultBean> list = new ArrayList<PerBean.ResultBean>();
    private MAdapter adapter;
    private UserDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment22, container, false);

        xlistview = view.findViewById(R.id.xlistview);
        xlistview.setPullLoadEnable(true);

        adapter = new MAdapter(getActivity(), list);
        xlistview.setAdapter(adapter);

        dao = new UserDao(getActivity());

        iniData(page);

        xlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                list.clear();
                page=0;
                iniData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                iniData(page);
            }
        });

        return view;
    }


    private void iniData(int page) {
        String s = urlstring + page;
        if (NewWordUtils.iscoon(getActivity())){
            new Asyync().execute(s);
        }else {
            Toast.makeText(getActivity(),"网络已断开",Toast.LENGTH_SHORT).show();
            list = dao.query();
            adapter = new MAdapter(getActivity(), list);
            xlistview.setAdapter(adapter);
        }
    }

    class Asyync extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return NewWordUtils.getJson(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            PerBean fromJson = gson.fromJson(s, PerBean.class);
            List<PerBean.ResultBean> result = fromJson.getResult();
            list.addAll(result);

            adapter.notifyDataSetChanged();
            shuxin();
        }
    }

    private void shuxin() {
        xlistview.setRefreshTime("刚刚");
        xlistview.stopLoadMore();
        xlistview.stopRefresh();
    }


}
