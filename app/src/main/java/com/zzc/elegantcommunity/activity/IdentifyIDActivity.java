package com.zzc.elegantcommunity.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.retrofit.RxRetrofit;
import com.zzc.elegantcommunity.retrofit.UserService;
import com.zzc.elegantcommunity.util.UserInfoUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;


/**
 * Created by Administrator on 2017/8/12.
 */

public class IdentifyIDActivity extends TakePhotoActivity {
    private static final String TAG ="IdentifyIDActivity";


    @BindView(R.id.iv_id_back)ImageView iv_id_back;
    @BindView(R.id.iv_id_front)ImageView iv_id_front;
    @BindView(R.id.et_input_id)
    EditText etInputId;

    private ProgressDialog progressDialog;
    private String imagepath1=null;
    private String imagepath2=null;

    //选择位置
    private int selectPostion;
    //选择方式 0为拍照 1为从相片中获取
    private int selectType;

    @OnClick(R.id.id_jump)
    public void jump(){

        Intent intent=new Intent(this,CertificateActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.btn_back)
    public  void back(){
        finish();
    }
    @OnClick(R.id.bt_confirm_upload)
    public void upload(){
        if(imagepath1==null||imagepath2==null){
            Toast.makeText(IdentifyIDActivity.this,"请先选择照片",Toast.LENGTH_LONG).show();
            return;
        }
        String id = etInputId.getText().toString();
        if(id==null||"".contentEquals(id)){
            Toast.makeText(IdentifyIDActivity.this,"请先输入省份证号",Toast.LENGTH_LONG).show();
            return;
        }

        showDialog();

        Retrofit retrofit = RxRetrofit.getRxRetrofitInstance();
        UserService userService = retrofit.create(UserService.class);

        File file1 = new File(imagepath1);//filePath 图片地址/
        File file2 = new File(imagepath2);//filePath 图片地址/

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody imageBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody imageBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);

        builder.addFormDataPart("file", file1.getName(), imageBody1);
        builder.addFormDataPart("file", file2.getName(), imageBody2);
        List<MultipartBody.Part> parts = builder.build().parts();

        //获取TOKEN 和用户名
        UserInfoUtil instance = UserInfoUtil.getInstance();
        MediaType textType = MediaType.parse("text/plain");

        //拼接TOKEN
        String token1 = instance.getToken();
        String splitToken="{\"token\":\""+token1+"\"}";


        RequestBody token = RequestBody.create(textType, splitToken);
        RequestBody userAcc = RequestBody.create(textType, instance.getUserAcc());
        RequestBody idNo  = RequestBody.create(textType, id);
        userService.upLoadCertificateId(idNo ,token,userAcc,parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Log.i(TAG,"上传成功"+responseBody.toString());
                        Log.i(TAG,"字段："+ responseBody.string());

                        progressDialog.dismiss();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG,"网络异常"+throwable.getMessage());
                        progressDialog.dismiss();
                    }
                });

    }

    private void showDialog() {
        //创建ProgressDialog对象
        progressDialog = new ProgressDialog(
                IdentifyIDActivity.this);
        //设置进度条风格，风格为圆形，旋转的
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_SPINNER);
        //设置ProgressDialog 标题
        progressDialog.setTitle("正在上传照片信息");
        //设置ProgressDialog 提示信息
        progressDialog.setMessage("请稍等...");
        //设置ProgressDialog 标题图标
        progressDialog.setIcon(android.R.drawable.btn_star);
        //设置ProgressDialog 的进度条是否不明确
        progressDialog.setIndeterminate(false);
        //设置ProgressDialog 是否可以按退回按键取消
        progressDialog.setCancelable(true);
        //设置取消按钮
//        progressDialog.setButton("取消",
//        new ProgressDialogButtonListener());
//        让ProgressDialog显示
        progressDialog.show();
//        handler.sendEmptyMessageDelayed(0,2000);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_id);
        ButterKnife.bind(this);


        Mylistener mylistener1=new Mylistener(1);
        Mylistener mylistener2=new Mylistener(2);
        iv_id_front.setOnClickListener(mylistener1);
        iv_id_back.setOnClickListener(mylistener2);



    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

        //当为拍照时
        if (selectType==0){
            if(selectPostion==1){

                Bitmap bitmap1 = BitmapFactory.decodeFile(imagepath1);
                iv_id_front.setImageBitmap(bitmap1);
            }else {
                Bitmap bitmap2 = BitmapFactory.decodeFile(imagepath2);
                iv_id_front.setImageBitmap(bitmap2);
            }
        }
        //当为从相片获取时
        if (selectType==1){
            TImage image = result.getImage();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getOriginalPath());
            if(selectPostion==1){
                imagepath1=image.getOriginalPath();
                iv_id_front.setImageBitmap(bitmap);
            }else {
                imagepath2=image.getOriginalPath();
                iv_id_back.setImageBitmap(bitmap);
            }
        }
    }

   class Mylistener implements View.OnClickListener{
       //1为正面 2为反面
       int type;
        public Mylistener(int type){
            this.type=type;
        }
       @Override
       public void onClick(View v) {
            selectPostion=type;
           AlertDialog.Builder builder=new AlertDialog.Builder(IdentifyIDActivity.this);
           final String[] Items={"拍照","从手机中选取"};
           builder.setItems(Items, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   switch (i){
                       //拍照
                       case 0:
                          selectType=0;
                           TakePhoto takePhoto1 = getTakePhoto();
                           Log.i(TAG,"getExternalStorageDirectory:"+Environment.getExternalStorageDirectory());
                           File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                           if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                           Uri imageUri = Uri.fromFile(file);
                           Log.i(TAG,"imageUri为："+imageUri);
                           Log.i(TAG,"imageUri为："+imageUri.getPath());
                           if(selectPostion==1){
                               imagepath1=imageUri.getPath();
                           }else{
                               imagepath2=imageUri.getPath();
                           }
                           takePhoto1.onPickFromCapture(imageUri);
                           break;

                       //从照片中获取
                       case 1:
                           selectType=1;
                           TakePhoto takePhoto2 = getTakePhoto();
                           takePhoto2.onPickFromGallery();
                           break;

                   }

               }
           });
           builder.setCancelable(true);
           AlertDialog dialog=builder.create();
           dialog.show();
       }
   }
}
