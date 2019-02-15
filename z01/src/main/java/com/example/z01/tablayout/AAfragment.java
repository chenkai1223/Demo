package com.example.z01.tablayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.z01.R;
import com.example.z01.adapter.MAdapter;
import com.example.z01.bean.PerBean;
import com.example.z01.dao.UserDao;
import com.example.z01.utils.NewWordUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class AAfragment extends Fragment {

    String urlstring = "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?page=1&count=10";
    int page;
    private PullToRefreshListView plistview;
    private List<PerBean.ResultBean> list = new ArrayList<PerBean.ResultBean>();
    private MAdapter adapter;
    private UserDao dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment11, container, false);
        plistview = view.findViewById(R.id.plistview);
        plistview.setMode(PullToRefreshListView.Mode.BOTH);
        dao = new UserDao(getActivity());

        adapter = new MAdapter(getActivity(), list);
        plistview.setAdapter(adapter);

        iniData(page);

        plistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                page=0;
                iniData(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
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
            plistview.setAdapter(adapter);
        }
    }

    class Asyync extends AsyncTask<String,Void,String>{

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

            for (int i=0;i<list.size();i++){
                String ivs = list.get(i).getImageUrl();
                String tes = list.get(i).getReleaseTimeShow();
                String tee = list.get(i).getName();
                dao.add(ivs,tes,tee);
            }

            adapter.notifyDataSetChanged();
            plistview.onRefreshComplete();
        }
    }

}
