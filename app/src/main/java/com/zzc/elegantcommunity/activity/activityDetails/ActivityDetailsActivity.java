package com.zzc.elegantcommunity.activity.activityDetails;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.adapter.aliAdapter.EachItemViewAdapter;
import com.zzc.elegantcommunity.adapter.aliAdapter.OneImageViewAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/17.
 */

public class ActivityDetailsActivity extends AppCompatActivity {
    private RecyclerView rvActivityDetials;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_detials);

          initView();
          initdate();
    }

    private void initdate() {

    }

    private void initView() {
      rvActivityDetials = findViewById(R.id.rv_activity_detials);
        rvActivityDetials.setBackgroundColor(0xfff0f2f5);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rvActivityDetials.setLayoutManager(layoutManager);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        //eachItemViewAdapter
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        EachItemViewAdapter eachAdapter = new EachItemViewAdapter(this, new LinearLayoutHelper(5), 4, layoutParams);

        ArrayList<EachItemViewAdapter.EachItemBean> eachItemBeans=new ArrayList();

        EachItemViewAdapter.EachItemBean eachItemBean1 = new EachItemViewAdapter.EachItemBean();
        eachItemBean1.setContent("04-26 8:30至04-26 17:30");
        eachItemBean1.setHeadPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_access_time_grey_700_24dp));
        EachItemViewAdapter.EachItemBean eachItemBean2 = new EachItemViewAdapter.EachItemBean();
        eachItemBean2.setContent("北京朝阳区三里屯通盈中心洲际酒店");
        eachItemBean2.setHeadPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_location_on_grey_500_24dp));
        eachItemBean2.setHasArrowPicture(true);
        EachItemViewAdapter.EachItemBean eachItemBean3 = new EachItemViewAdapter.EachItemBean();
        eachItemBean3.setContent("已报名1663/限1700人报名");
        eachItemBean3.setHeadPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_person_grey_500_24dp));
        EachItemViewAdapter.EachItemBean eachItemBean4 = new EachItemViewAdapter.EachItemBean();
        eachItemBean4.setContent("￥0~599");
        eachItemBean4.setHeadPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_attach_money_grey_500_24dp));
        eachItemBean4.setHasArrowPicture(true);
        eachItemBeans.add(eachItemBean1);
        eachItemBeans.add(eachItemBean2);
        eachItemBeans.add(eachItemBean3);
        eachItemBeans.add(eachItemBean4);
        eachAdapter.setBeans(eachItemBeans);
        adapters.add(eachAdapter);

        // 单个图片adapter
        adapters.add(new OneImageViewAdapter(this,new LinearLayoutHelper(10), 1));

        delegateAdapter.setAdapters(adapters);

        rvActivityDetials.setAdapter(delegateAdapter);

//        delegateAdapter.notifyDataSetChanged();


    }

}
