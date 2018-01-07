package com.zzc.elegantcommunity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.zzc.elegantcommunity.adapter.FirstComingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzc on 2017/8/13.
 */


public class FirstComingActvity extends AppCompatActivity {
    private static final String TAG = "FirstComingActvity";
    private int currentpage;

    @BindView(R.id.vp_firstcoming)
    ViewPager vp_firstcoming;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_coming);
        ButterKnife.bind(this);
        vp_firstcoming.setAdapter(new FirstComingAdapter(getSupportFragmentManager()));
        vp_firstcoming.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentpage=position;
                Log.i(TAG,"当前页为："+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_firstcoming.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;
            float endX;
            float endY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               // Log.i(TAG,"当前页为："+currentpage);
                if(currentpage==2){
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            startX=event.getX();
                            startY=event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            endX=event.getX();
                            endY=event.getY();
                            WindowManager windowManager= (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                            //获取屏幕的宽度
                            Point size = new Point();
                            windowManager.getDefaultDisplay().getSize(size);
                            int width=size.x;
                            //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                            if(startX-endX>0&&startX-endX>=(width/4)){
                                goToMainActivity();
                                finish();

                            }
                            break;
                    }

                }

                return false;
            }
        });

    }
    public void goToMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }

}
