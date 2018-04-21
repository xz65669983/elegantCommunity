package com.zzc.elegantcommunity.activity.activityDetails;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.adapter.aliAdapter.EachItemViewAdapter;
import com.zzc.elegantcommunity.adapter.aliAdapter.OneImageViewAdapter;
import com.zzc.elegantcommunity.adapter.aliAdapter.TitleViewAdapter;
import com.zzc.elegantcommunity.model.issueactivity.BriefActivityModel;
import com.zzc.elegantcommunity.model.issueactivity.ParticipateActivityRequestModel;
import com.zzc.elegantcommunity.model.issueactivity.ParticipateActivityResponseModel;
import com.zzc.elegantcommunity.retrofit.RetrofitImageAPI;
import com.zzc.elegantcommunity.retrofit.RxRetrofit;
import com.zzc.elegantcommunity.retrofit.RxRetrofitWithGson;
import com.zzc.elegantcommunity.retrofit.issueActivity.ActivityService;
import com.zzc.elegantcommunity.util.UserInfoUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/2/17.
 */

public class ActivityDetailsActivity extends AppCompatActivity {
    private static final String TAG = "ActivityDetailsActivity";
    private RecyclerView rvActivityDetials;

    private Integer activityId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detials);
        ButterKnife.bind(this);

        initView();
        initdate();
    }

    @OnClick(R.id.btn_back)
    public void back(){
        finish();
    }



    @OnClick(R.id.bt_participate)
    public void participate(){
        //判断是否已经登录

        boolean login = UserInfoUtil.isLogin();
        if(!login){
            Toast.makeText(ActivityDetailsActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return ;
        }
        Retrofit rxRetrofitInstance = RxRetrofitWithGson.getRxRetrofitInstance();
        ActivityService activityService = rxRetrofitInstance.create(ActivityService.class);

        ParticipateActivityRequestModel p=new ParticipateActivityRequestModel();
        UserInfoUtil instance = UserInfoUtil.getInstance();
        p.setToken(instance.getToken());
        p.setUserAcc(instance.getUserAcc());
        ParticipateActivityRequestModel.Participator participator= new ParticipateActivityRequestModel.Participator();
        participator.setActivityId(activityId);
        participator.setUserId(instance.getId());
        p.setParticipator(participator);
        activityService.participate(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ParticipateActivityResponseModel>() {
                    @Override
                    public void accept(ParticipateActivityResponseModel participateActivityResponseModel) throws Exception {
                        String resultCode = participateActivityResponseModel.getResultCode();
                        if (resultCode.equals("0000")){
                            Toast.makeText(ActivityDetailsActivity.this,"报名成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ActivityDetailsActivity.this,"报名失败"+participateActivityResponseModel.getResultMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(ActivityDetailsActivity.this,"网络异常",Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void initdate() {

        Intent intent = getIntent();
        BriefActivityModel briefActivityModel = (BriefActivityModel) intent.getSerializableExtra("BriefActivityModel");
        String beginTime = briefActivityModel.getBeginTime();
        String endTime = briefActivityModel.getEndTime();
        Integer capacityLimit = briefActivityModel.getCapacityLimit();
        String city = briefActivityModel.getCity();
        String mainAddress = briefActivityModel.getMainAddress();
        String content = briefActivityModel.getContent();
        activityId = briefActivityModel.getId();
        Integer cost = briefActivityModel.getCost();
        String county = briefActivityModel.getCounty();
        String participatorCondition = briefActivityModel.getParticipatorCondition();
        String poster = briefActivityModel.getPoster();
//        Integer siteId = briefActivityModel.getSiteId();
        Integer sponsor = briefActivityModel.getSponsor();
        Integer status = briefActivityModel.getStatus();
        String title = briefActivityModel.getTitle();

        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rvActivityDetials.setLayoutManager(layoutManager);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        //TitleView

        TitleViewAdapter titleViewAdapter = new TitleViewAdapter(this);
        ArrayList<TitleViewAdapter.TitleViewHolderBean> titleViewHolderBeans = new ArrayList<>();
        TitleViewAdapter.TitleViewHolderBean t = new TitleViewAdapter.TitleViewHolderBean();
        t.setContent(content);
        t.setTvSignupCount("已报名人数"+briefActivityModel.getParticipatedCount()+"人");
        t.setTvTitle("主题：" + title);
        titleViewHolderBeans.add(t);
        titleViewAdapter.setTitleViewHolderBeans(titleViewHolderBeans);
        adapters.add(titleViewAdapter);

        //eachItemViewAdapter
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        EachItemViewAdapter eachAdapter = new EachItemViewAdapter(this, new LinearLayoutHelper(5), 4, layoutParams);

        ArrayList<EachItemViewAdapter.EachItemBean> eachItemBeans = new ArrayList();

        EachItemViewAdapter.EachItemBean eachItemBean1 = new EachItemViewAdapter.EachItemBean();
        eachItemBean1.setContent(beginTime + " 至 " + endTime);
        eachItemBean1.setHeadPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_access_time_grey_700_24dp));
        EachItemViewAdapter.EachItemBean eachItemBean2 = new EachItemViewAdapter.EachItemBean();
        eachItemBean2.setContent(city + "  " + mainAddress);
        eachItemBean2.setHeadPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_location_on_grey_500_24dp));
        eachItemBean2.setHasArrowPicture(true);
        EachItemViewAdapter.EachItemBean eachItemBean3 = new EachItemViewAdapter.EachItemBean();
        eachItemBean3.setContent("活动人数限制:" + capacityLimit + "人");
        eachItemBean3.setHeadPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_person_grey_500_24dp));
        EachItemViewAdapter.EachItemBean eachItemBean4 = new EachItemViewAdapter.EachItemBean();
        eachItemBean4.setContent("￥" + cost);
        eachItemBean4.setHeadPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_attach_money_grey_500_24dp));
        eachItemBean4.setHasArrowPicture(true);
        eachItemBeans.add(eachItemBean1);
        eachItemBeans.add(eachItemBean2);
        eachItemBeans.add(eachItemBean3);
        eachItemBeans.add(eachItemBean4);
        eachAdapter.setBeans(eachItemBeans);
        adapters.add(eachAdapter);


        delegateAdapter.setAdapters(adapters);
        rvActivityDetials.setAdapter(delegateAdapter);

        if(poster!=null&&!poster.equals("")){
            //加载图片
            RetrofitImageAPI retrofitImageAPI = RxRetrofit.getRxRetrofitInstance().create(RetrofitImageAPI.class);
            retrofitImageAPI.getImageDetails(poster)
                .subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, Bitmap>() {

                    @Override
                    public Bitmap apply(ResponseBody responseBody) throws Exception {
                        InputStream inputStream = responseBody.byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        Log.i(TAG,"获取图片成功 你应该给我显示出来啦");
                        OneImageViewAdapter oneImageViewAdapter = new OneImageViewAdapter(ActivityDetailsActivity.this, new LinearLayoutHelper(10), 1);
                        oneImageViewAdapter.setBitmap(bitmap);
                        adapters.add(oneImageViewAdapter);
                        delegateAdapter.setAdapters(adapters);
                        delegateAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(ActivityDetailsActivity.this,"获取图片失败",Toast.LENGTH_LONG).show();
                    }
                });

        }


    }

    private void initView() {
        rvActivityDetials = findViewById(R.id.rv_activity_detials);
        rvActivityDetials.setBackgroundColor(0xfff0f2f5);

    }

}
