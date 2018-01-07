package com.zzc.elegantcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * Created by zzc on 2017/11/11.
 */

public class SplashActivity extends AppCompatActivity {

    private LinearLayout ll_splash;
    Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //第一次打开APP
                case 1:
                    Intent intent1=new Intent(SplashActivity.this,FirstComingActvity.class);
                    startActivity(intent1);
                    finish();

                    break;
                case 2:
                    Intent intent2=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent2);
                    finish();
                    break;

            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        ll_splash = (LinearLayout) findViewById(R.id.ll_splash);
        //startAnim();
        myHandler.sendEmptyMessageDelayed(1,1000);
    }
}
