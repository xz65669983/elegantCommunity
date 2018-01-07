package com.zzc.elegantcommunity.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzc.elegantcommunity.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/7/30.
 */

public class RevisePasswordActivity extends AppCompatActivity {
@OnClick(R.id.btn_back)
public void back(){
    finish();
}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_password);
        ButterKnife.bind(this);

    }
}

