package com.zzc.elegantcommunity.module.media.channel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zzc.elegantcommunity.EventBus.LoginEvent;
import com.zzc.elegantcommunity.EventBus.LogoutEvent;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.activity.LoginActivity;
import com.zzc.elegantcommunity.activity.UserDetailsActivity;
import com.zzc.elegantcommunity.retrofit.RetrofitImageAPI;
import com.zzc.elegantcommunity.retrofit.RxRetrofit;
import com.zzc.elegantcommunity.util.UserInfoUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by Meiji on 2016/12/24.
 */

public class PersonalCenterFragment extends RxFragment {

    private static final String TAG = "PersonalCenterFragment";
    private static PersonalCenterFragment instance = null;
//    private RecyclerView recyclerView;
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private MultiTypeAdapter adapter;
//    private MediaChannelDao dao = new MediaChannelDao();
//    private TextView tv_desc;
//    private String isFirstTime = "isFirstTime";
//    private List<MediaChannelBean> list;

    public static PersonalCenterFragment getInstance() {
        if (instance == null) {
            instance = new PersonalCenterFragment();
        }
        return instance;
    }

    @BindView(R.id.nickname_txtv)
    public TextView tv_nickname;

    @OnClick(R.id.account_rlayout)
    public void accountManger() {
        Intent intent = new Intent(getContext(), UserDetailsActivity.class);
        startActivity(intent);
    }

    @BindView(R.id.head_mimgv)
    ImageView head_mimgv;

    @OnClick(R.id.head_mimgv)
    public void clicktouxiang() {


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当登录完成时 页面你需要修改
        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_informantion, container, false);
        ButterKnife.bind(this, view);
        UserInfoUtil instance = UserInfoUtil.getInstance();
        String token = instance.getToken();
        if (token == null || token.contentEquals("")) {
            //未登录
            handleLogout();
        } else {
            //已登录
            hanldeLogIn();
        }

        return view;

    }

    private void handleLogout() {
        head_mimgv.setImageResource(R.mipmap.person_default_head);
        tv_nickname.setText("点击此处登录");
        tv_nickname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hanldeLogIn() {
        tv_nickname.setOnClickListener(null);
        tv_nickname.setText("您好:" + UserInfoUtil.getInstance().getNickName());
        getheadImage();
    }


    private void getheadImage() {
        Retrofit rxRetrofit = RxRetrofit.getRxRetrofitInstance();
        RetrofitImageAPI imageAPI = rxRetrofit.create(RetrofitImageAPI.class);
        imageAPI.getImageDetails(UserInfoUtil.getInstance().getPortraitFilename())
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
                        head_mimgv.setImageBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "网络连接异常");
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();
        //swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
        //setAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (instance != null) {
            instance = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        hanldeLogIn();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LogoutEvent logoutEvent){
        handleLogout();
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();


    }
}
