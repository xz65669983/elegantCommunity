package com.zzc.elegantcommunity.retrofit;


import com.zzc.elegantcommunity.model.MyResponseBody;
import com.zzc.elegantcommunity.model.issueactivity.IssueActivityModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/12/31.
 */

public interface IssueActivityService {

    @POST("elegant/activity/publishActivity")
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    //Observable<ResponseBody> issueActivity(@Body IssueActivityModel issueActivityModel);
    Observable<MyResponseBody> issueActivity(@Body IssueActivityModel issueActivityModel);
}
