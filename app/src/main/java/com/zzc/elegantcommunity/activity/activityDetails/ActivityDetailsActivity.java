package com.zzc.elegantcommunity.activity.activityDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.zzc.elegantcommunity.R;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2018/2/17.
 */

public class ActivityDetailsActivity extends AppCompatActivity {
    private RecyclerView rvActivityDetials;
    private MultiTypeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_detials);
          initView();
          initdate();
    }

    private void initdate() {
        adapter=new MultiTypeAdapter();
        adapter.register(OneImageViewBean.class,new OneImageViewBinder());
        Items items = new Items();
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        items.add(new OneImageViewBean());
        adapter.setItems(items);
        rvActivityDetials.setAdapter(adapter);
    }

    private void initView() {
      rvActivityDetials = findViewById(R.id.rv_activity_detials);





    }
}
