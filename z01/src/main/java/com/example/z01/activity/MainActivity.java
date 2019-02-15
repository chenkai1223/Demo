package com.example.z01.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.z01.R;

public class MainActivity extends AppCompatActivity {

    private TextView miao;
    private int i=5;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i>0){
                i--;
                miao.setText(i+"s");
                handler.sendEmptyMessageDelayed(1,1000);
            }else {
                Intent intent = new Intent(MainActivity.this,LogActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miao = findViewById(R.id.miao);

        SharedPreferences user = getSharedPreferences("User", MODE_PRIVATE);
        edit = user.edit();
        boolean key = user.getBoolean("key", false);
        if (key){
            Intent intent = new Intent(MainActivity.this,LogActivity.class);
            startActivity(intent);
            finish();
        }else {
            handler.sendEmptyMessageDelayed(1,1000);
        }
        edit.putBoolean("key",true);
        edit.commit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
