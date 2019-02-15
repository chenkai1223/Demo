package com.example.z01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z01.R;
import com.example.z01.fragment.Afragment;
import com.example.z01.fragment.Bfragment;
import com.example.z01.fragment.Cfragment;

public class LogActivity extends BaseActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.but1:
                    transaction.show(afragment).hide(bfragment).hide(cfragment);
                    break;
                case R.id.but2:
                    transaction.show(bfragment).hide(afragment).hide(cfragment);
                    break;
                case R.id.but3:
                    transaction.show(cfragment).hide(bfragment).hide(afragment);
                    break;
            }
            transaction.commit();
            return true;
        }
    };
    private Afragment afragment;
    private Bfragment bfragment;
    private Cfragment cfragment;
    private DrawerLayout drawer;


    @Override
    public void inid() {
        drawer = findViewById(R.id.drawer);
    }

    @Override
    public void iniv() {

        afragment = new Afragment();
        bfragment = new Bfragment();
        cfragment = new Cfragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction transaction1 = transaction.add(R.id.frameLayout, afragment);
        FragmentTransaction transaction2 = transaction.add(R.id.frameLayout, bfragment);
        FragmentTransaction transaction3 = transaction.add(R.id.frameLayout, cfragment);

        transaction.show(afragment).hide(bfragment).hide(cfragment);
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        /***
         * 侧滑监听
         */
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(LogActivity.this,"侧滑打开",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(LogActivity.this,"侧滑关闭",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

    }

    @Override
    public int getview() {
        return R.layout.activity_log;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        afragment.onActivityResult(requestCode, resultCode, data);
    }
}
