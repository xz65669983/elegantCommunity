package com.zzc.elegantcommunity.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zzc.elegantcommunity.EventBus.LogoutEvent;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.model.MyResponseBody;
import com.zzc.elegantcommunity.model.User;
import com.zzc.elegantcommunity.model.UserModel;
import com.zzc.elegantcommunity.retrofit.MyRetrofit;
import com.zzc.elegantcommunity.retrofit.RetrofitImageAPI;
import com.zzc.elegantcommunity.retrofit.RxRetrofit;
import com.zzc.elegantcommunity.retrofit.UserService;
import com.zzc.elegantcommunity.util.UserInfoUtil;
import com.zzc.elegantcommunity.view.SPMoreImageView;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/7/30.
 */

public class UserDetailsActivity extends AppCompatActivity {
    private static final String TAG = "UserDetailsActivity";

    @BindView(R.id.head_mimgv)
    SPMoreImageView headMimgv;
    @BindView(R.id.phone_num_txt)
    TextView tvPhoneNum;
    @BindView(R.id.mail_num_txt)
    TextView tvMailNum;
    @BindView(R.id.nickname_txtv)
    TextView tvNickName;
    @BindView(R.id.sex_txtv)
    TextView tvSex;
    @BindView(R.id.age_txtv)
    TextView tvAge;
    @BindView(R.id.city_txtv)
    TextView tvCity;

    //占时修改为认证学历接口
    @OnClick(R.id.revise_password_txt)
    public void revisePassword() {
        Intent intent=new Intent(UserDetailsActivity.this,IdentifyIDActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_save)
    public void save() {
        finish();
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
        String token = UserInfoUtil.getInstance().getToken();
        if(token==null||token.contentEquals("")){
            return;
        }
        Log.i(TAG,"进入登出接口");
        Retrofit retrofit = MyRetrofit.getGsonRetrofitInstance();
        UserService userService = retrofit.create(UserService.class);
        User user = new User();
        String userAcc = UserInfoUtil.getInstance().getUserAcc();
        user.setUserAcc(userAcc);
        UserModel userModel = new UserModel();
        userModel.setUser(user);
        Call<MyResponseBody> call = userService.logOut(userModel);
        call.enqueue(new Callback<MyResponseBody>() {
            @Override
            public void onResponse(Call<MyResponseBody> call, Response<MyResponseBody> response) {
                MyResponseBody body = response.body();
                Log.i(TAG,"接受成功"+body.getResultCode()+"message"+body.getResultMessage());
                if ("0000".contentEquals(body.getResultCode())) {
                    //清除Token
                    UserInfoUtil.getInstance().saveToken("");
                    //清空UI界面
                    clearui();
                    //发送通知给PersonalCenterFragment
                    EventBus.getDefault().post(new LogoutEvent());
                    Toast.makeText(UserDetailsActivity.this, "登出成功，欢迎下次回来", Toast.LENGTH_LONG).show();
                     finish();
                }else{
                    Toast.makeText(UserDetailsActivity.this, "系统异常，登出失败", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MyResponseBody> call, Throwable t) {
                Toast.makeText(UserDetailsActivity.this, "网络异常请稍后重试", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void clearui() {
        headMimgv.setImageResource(R.mipmap.person_default_head);
        tvPhoneNum.setText("");
        tvMailNum.setText("");
        tvNickName.setText("");
        tvSex.setText("");
        tvAge.setText("");
        tvCity.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spuser_details);
        ButterKnife.bind(this);
        initview();

    }


    private void initview() {
        UserInfoUtil instance = UserInfoUtil.getInstance();
        String token = instance.getToken();
        if (token == null || token.contentEquals("")) {
            clearui();
        } else {
            Log.i(TAG, "获取图片时的名称：" + instance.getPortraitFilename());
            setheadImage(instance.getPortraitFilename());
            tvPhoneNum.setText(instance.getPhoneNumber());
            tvMailNum.setText(instance.getEmail());
            tvNickName.setText(instance.getNickName());
            Log.i(TAG, String.valueOf(instance.getMale()));
            tvSex.setText(String.valueOf(instance.getMale()));
            tvAge.setText("");
            tvCity.setText("");
        }
    }

    private void setheadImage(String filename) {
        Log.e(TAG,"图片默认地址为："+filename);
        Retrofit rxRetrofit = RxRetrofit.getRxRetrofitInstance();
        RetrofitImageAPI imageAPI = rxRetrofit.create(RetrofitImageAPI.class);
        imageAPI.getImageDetails(filename)
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
                        headMimgv.setImageBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "网络连接异常");
                        AlertDialog.Builder customizeDialog =
                                new AlertDialog.Builder(UserDetailsActivity.this);
                        customizeDialog.setTitle("网络连接异常,无法获取头像")
                                .setMessage(throwable.getMessage())
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                })
                                .show();
                    }
                });
    }
}
