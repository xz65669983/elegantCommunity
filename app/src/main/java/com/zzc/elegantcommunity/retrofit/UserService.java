package com.zzc.elegantcommunity.retrofit;


import com.zzc.elegantcommunity.model.MyResponseBody;
import com.zzc.elegantcommunity.model.RegisterModel;
import com.zzc.elegantcommunity.model.UserModel;
import com.zzc.elegantcommunity.model.login.LoginResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by zzc on 2017/10/15.
 */

public interface UserService {
    @POST("elegant/user/login")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
//需要添加头
    Call<LoginResponse> login(@Body UserModel userModel);

    @POST("elegant/user/register")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
//需要添加头
    Observable<ResponseBody> signUp(@Body RegisterModel registerModel);

    @POST("elegant/user/logout")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
//需要添加头
    Call<MyResponseBody> logOut(@Body UserModel userModel);

    @POST("elegant/user/identificationCert")
    @Multipart
    Observable<ResponseBody> upLoadCertificateId(@Part("idNo") RequestBody idNo,
                                                 @Part("token") RequestBody token,
                                                 @Part("userAcc") RequestBody userAcc,
                                                 @Part List<MultipartBody.Part> partList);

}
