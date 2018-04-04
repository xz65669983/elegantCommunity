package com.zzc.elegantcommunity.retrofit;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by zhangzhengchao on 2017/12/27.
 */

public interface RetrofitImageAPI {

    @GET("elegant/files/{filename}")
    Observable<ResponseBody> getImageDetails(@Path("filename") String filename);

    @POST("elegant/fileUpload")
    @Multipart
    Observable<ResponseBody> fileUpload( @Part MultipartBody.Part file);
}
