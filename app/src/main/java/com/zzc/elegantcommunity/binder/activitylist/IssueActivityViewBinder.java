package com.zzc.elegantcommunity.binder.activitylist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.zzc.elegantcommunity.InitApp;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.model.MyResponseBody;
import com.zzc.elegantcommunity.model.issueactivity.ActivityDetialsModel;
import com.zzc.elegantcommunity.model.issueactivity.IssueActivityModel;
import com.zzc.elegantcommunity.retrofit.IssueActivityService;
import com.zzc.elegantcommunity.retrofit.RxRetrofitWithGson;
import com.zzc.elegantcommunity.util.UserInfoUtil;

import org.feezu.liuli.timeselector.TimeSelector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by zhangzhengchao on 2018/4/4.
 */

public class IssueActivityViewBinder extends ItemViewBinder<ActivityDetialsModel, IssueActivityViewBinder.ViewHolder> {


    private static final String TAG = "IssueActivityViewBinder";
    private String place;

    private View view;

    @BindView(R.id.et_activity_topic)
    EditText etActivityTopic;
    @BindView(R.id.et_mobile_phone)
    EditText etMobilePhone;
    @BindView(R.id.start_time)
    TextView tvStartTime;
    @BindView(R.id.et_activity_fee)
    EditText etActivityFee;
    @BindView(R.id.et_activity_content)
    EditText etAtivityContent;

    @BindView(R.id.et_mian_address)
    EditText etMianAddress;

    @BindView(R.id.end_time)
    TextView tvEndTime;

    @BindView(R.id.tv_select_location)
    TextView tvSelectLoacation;

    @BindView(R.id.tv_end_activity_time)
    TextView tvEndActivityTime;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_issue_activity, parent, false);
        this.view=view;
        ButterKnife.bind(this, view);

        return new IssueActivityViewBinder.ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ActivityDetialsModel item) {


    }

    class ViewHolder extends RecyclerView.ViewHolder {



        ViewHolder(View itemView) {
            super(itemView);

        }
    }

    @OnClick(R.id.end_time)
    public void endTime() {
        pickTime(tvEndTime);
    }

    @OnClick(R.id.start_time)
    public void startTime() {
        pickTime(tvStartTime);
    }
    @OnClick(R.id.issue_activity_place)
    public void selectLocation() {
        CityPickerView.getInstance().setConfig(new CityConfig.Builder(view.getContext()).build());
        CityPickerView.getInstance().setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                tvSelectLoacation.setText(province.toString() + city.toString() + district.toString());
                place = province.toString() + city.toString() + district.toString();
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(view.getContext(), "已取消");
            }
        });

        //显示
        CityPickerView.getInstance().showCityPicker(view.getContext());

    }

    @OnClick(R.id.btn_issue)
    public void issueActivtiy() {
        boolean login = isLogin();
        if(!login){
            Toast.makeText(InitApp.AppContext,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        boolean paramLegal = isParamLegal();
        if(!paramLegal) {
            return;
        }
        IssueActivityModel issueActivityModel = new IssueActivityModel();
        ActivityDetialsModel acm = new ActivityDetialsModel();
        acm.setBeginTime(tvStartTime.getText().toString());
        acm.setEndTime(tvEndTime.getText().toString());
        acm.setCity(this.place);
        acm.setContactPhoneNo(etMobilePhone.getText().toString());
        acm.setTitle(etActivityTopic.getText().toString());
        acm.setMainAddress(etMianAddress.getText().toString());

        String fee = etActivityFee.getText().toString();
        if(fee==null||fee.contentEquals("")){
            acm.setCost(0);
        }else {
            acm.setCost(Integer.getInteger(etActivityFee.getText().toString()));
        }
        acm.setContent(etAtivityContent.getText().toString());

        issueActivityModel.setActivityDetialsModel(acm);
        String token= UserInfoUtil.getInstance().getToken();
        issueActivityModel.setToken(token);

        IssueActivityService issueActivityService = RxRetrofitWithGson.getRxRetrofitInstance().create(IssueActivityService.class);
        issueActivityService.issueActivity(issueActivityModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyResponseBody>() {
                    @Override
                    public void accept(MyResponseBody responseBody) throws Exception {
//                        JsonElement je = new JsonParser().parse(responseBody.toString());
//                        String resultCode = je.getAsJsonObject().get("resultCode").toString();
                        String resultCode = responseBody.getResultCode();
                        if(resultCode.contentEquals("0000")){
                            //发布活动成功
                            Toast.makeText(InitApp.AppContext, "发布活动成功", Toast.LENGTH_LONG).show();

                        }else {
                            //登陆失败
                            /*String resultMessage = je.getAsJsonObject().get("resultMessage").toString();*/
                            String resultMessage = responseBody.getResultMessage();
                            Toast.makeText(InitApp.AppContext, "发布失败,"+resultMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(InitApp.AppContext,"发布活动失败:"+ throwable.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
    }

    private boolean isLogin() {
        String token = UserInfoUtil.getInstance().getToken();
        if(token.contentEquals("")||token==null){
            return false;
        }else{
            return true;
        }


    }

    private boolean isParamLegal() {
        String s1 = tvStartTime.getText().toString();
        if(s1==null||s1.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"活动起始时间不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        String s2 = tvEndTime.getText().toString();
        if(s2==null||s2.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"活动结束时间不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if(this.place==null||this.place.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"地点不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
   /*     String s3 = etMobilePhone.getText().toString();
        if(s3==null||s3.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"联系电话不能为空",Toast.LENGTH_LONG).show();
            return false;
        }*/
        String s4 = etActivityTopic.getText().toString();
        if(s1==null||s4.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"活动主题不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        String s5 = etMianAddress.getText().toString();
        if(s1==null||s5.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"活动详细地址不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        String s6 = etAtivityContent.getText().toString();
        if(s1==null||s1.contentEquals("")){
            Toast.makeText(InitApp.AppContext,"活动内容不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;

    }


    public void pickTime(final TextView view) {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                TimeSelector timeSelector = new TimeSelector(view.getContext(), new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        e.onNext(time);
                    }
                }, "2017-12-01 00:00", "2025-12-31 00:00");
                timeSelector.show();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                view.setText(s);
            }
        });
    }


}
