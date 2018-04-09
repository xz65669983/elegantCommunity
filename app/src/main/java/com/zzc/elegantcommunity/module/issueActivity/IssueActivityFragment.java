package com.zzc.elegantcommunity.module.issueActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import com.zzc.elegantcommunity.InitApp;
import com.zzc.elegantcommunity.binder.activitylist.IssueActivityViewBinder;
import com.zzc.elegantcommunity.model.issueactivity.ActivityDetialsModel;
import com.zzc.elegantcommunity.module.activity.IActivityList;
import com.zzc.elegantcommunity.module.base.BaseListFragment;
import com.zzc.elegantcommunity.util.StringUtil;

import java.io.File;
import java.util.List;
import me.drakeet.multitype.MultiTypeAdapter;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by zhangzhengchao on 2018/4/4.
 */

public class IssueActivityFragment extends BaseListFragment<IActivityList.Presenter> {
    public static final String TAG = "IssueActivityFragment";
    private static final int IMAGE = 1;
    private File file;

    @Override
    public void setPresenter(IActivityList.Presenter presenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    protected void initData() throws NullPointerException {
        oldItems.add(new ActivityDetialsModel());
        adapter.setItems(oldItems);
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        swipeRefreshLayout.setEnabled(false);
        adapter = new MultiTypeAdapter(oldItems);
        adapter.register(ActivityDetialsModel.class, new IssueActivityViewBinder(IssueActivityFragment.this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            Log.i(TAG,uri.getPath());
            Log.i(TAG,"获取的外部缓存路径:"+InitApp.AppContext.getExternalCacheDir().getAbsolutePath());
            Luban.with(InitApp.AppContext)
                    .load(uri.getPath().substring(5))
                    .ignoreBy(50)
                    .setTargetDir(InitApp.AppContext.getExternalCacheDir().getAbsolutePath())
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onSuccess(File file) {
                            IssueActivityFragment.this.file=file;
                            Log.i(TAG,"FILE路径"+file.getAbsolutePath());
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            IssueActivityViewBinder.ViewHolder childViewHolder = (IssueActivityViewBinder.ViewHolder)recyclerView.getChildViewHolder(IssueActivityViewBinder.view);
                            childViewHolder.addImageView.setImageBitmap(bitmap);

                        }

                        @Override
                        public void onError(Throwable e) {
                         Log.e(TAG,"图片压缩失败");
                        }
                    }).launch();

        }

    }

    public File getfile(){
        return file;
    }


}
