package com.example.z01.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z01.R;
import com.example.z01.bean.PerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {

    private Context context;
    private List<PerBean.ResultBean> list = new ArrayList<PerBean.ResultBean>();

    public MAdapter(Context context, List<PerBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int i = getItemViewType(position);
        switch (i){
            case 0:
                ViewHolder1 holder1;
                if (convertView == null) {
                    holder1 = new ViewHolder1();
                    convertView = View.inflate(context, R.layout.la,null);
                    holder1.ivs=convertView.findViewById(R.id.ivs);
                    holder1.tes = convertView.findViewById(R.id.tes);
                    convertView.setTag(holder1);
                }else {
                    holder1 = (ViewHolder1) convertView.getTag();
                }
                ImageLoader.getInstance().displayImage(list.get(position).getImageUrl(),holder1.ivs);
                holder1.tes.setText(list.get(position).getReleaseTimeShow());
                break;
            case 1:
                ViewHolder2 holder2;
                if (convertView == null) {
                    holder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.lb,null);

                    holder2.tee = convertView.findViewById(R.id.tee);
                    convertView.setTag(holder2);
                }else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }

                holder2.tee.setText(list.get(position).getName());
                break;
        }
        return convertView;
    }
    class ViewHolder1{
        ImageView ivs;
        TextView tes;
    }
    class ViewHolder2{

        TextView tee;
    }
}
