package com.zzc.elegantcommunity.retrofit.issueActivity;

import com.zzc.elegantcommunity.model.issueactivity.BriefAcitivtyResponseModel;
import com.zzc.elegantcommunity.model.issueactivity.BriefActivityRequestModel;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by zhangzhengchao on 2018/3/29.
 */

public interface ActivityService {

    @POST("elegant/activity/getNewestActivities")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
//需要添加头
    Observable<BriefAcitivtyResponseModel> getNewestActivities(@Body BriefActivityRequestModel briefActivityRequestModel);
}
