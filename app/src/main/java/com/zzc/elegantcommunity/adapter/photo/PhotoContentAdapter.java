package com.zzc.elegantcommunity.adapter.photo;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.bean.photo.PhotoGalleryBean;
import com.zzc.elegantcommunity.module.base.BaseActivity;
import com.zzc.elegantcommunity.util.ImageLoader;
import com.zzc.elegantcommunity.util.SettingUtil;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Meiji on 2017/2/17.
 */

public class PhotoContentAdapter extends PagerAdapter {
    private static final String TAG = "PhotoContentAdapter";
    private Context context;
    private PhotoGalleryBean galleryBean;
    private SparseArray<View> cacheView;
    private ViewGroup containerTemp;

    public PhotoContentAdapter(Context context, PhotoGalleryBean galleryBean) {
        this.context = context;
        this.galleryBean = galleryBean;
        this.cacheView = new SparseArray<>(galleryBean.getCount());
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        if (containerTemp == null)
            containerTemp = container;

        View view = cacheView.get(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_photo_content, container, false);
            view.setTag(position);
            final ImageView iv_image = view.findViewById(R.id.iv_image);
            final TextView tv_abstract = view.findViewById(R.id.tv_abstract);
            final TextView tv_onclick = view.findViewById(R.id.tv_onclick);
            final ProgressBar progressBar = view.findViewById(R.id.pb_progress);
            int color = SettingUtil.getInstance().getColor();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
                DrawableCompat.setTint(wrapDrawable, color);
                progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
            } else {
                progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }

            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(iv_image);
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    BaseActivity activity = (BaseActivity) context;
                    activity.finish();
                }
            });

            final List<PhotoGalleryBean.SubImagesBean> sub_images = galleryBean.getSub_images();
            List<String> sub_abstracts = galleryBean.getSub_abstracts();

            final RequestListener<String, GlideDrawable> listener = new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            };

            if (!SettingUtil.getInstance().getIsNoPhotoMode()) {
                ImageLoader.loadCenterCrop(context, sub_images.get(position).getUrl(), iv_image, listener);
            } else {
                progressBar.setVisibility(View.GONE);
                tv_onclick.setVisibility(View.VISIBLE);
                view.findViewById(R.id.layout_onclick).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                        tv_onclick.setVisibility(View.GONE);
                        ImageLoader.loadCenterCrop(context, sub_images.get(position).getUrl(), iv_image, listener);
                    }
                });
            }
            tv_abstract.setText(sub_abstracts.get(position));
            cacheView.put(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return galleryBean.getCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
