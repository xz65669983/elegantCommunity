package com.zzc.elegantcommunity.binder.activitylist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.bean.ActivityList.ActivityListBean;
import com.zzc.elegantcommunity.widget.CircleImageView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Meiji on 2017/6/8.
 */

public class ActivitListViewBinder extends ItemViewBinder<ActivityListBean, ActivitListViewBinder.ViewHolder> {

    private static final String TAG = "ActivitListViewBinder";

    @NonNull
    @Override
    protected ActivitListViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_article_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ActivitListViewBinder.ViewHolder holder, @NonNull final ActivityListBean item) {

        final Context context = holder.itemView.getContext();
        holder.tv_title.setText(item.getTitle());
        holder.tv_extra.setText(item.getExtra());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView iv_media;
        private TextView tv_extra;
        private TextView tv_title;
        private ImageView iv_video_image;
        private TextView tv_video_time;
        private ImageView iv_dots;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_media = itemView.findViewById(R.id.iv_media);
            this.tv_extra = itemView.findViewById(R.id.tv_extra);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.iv_video_image = itemView.findViewById(R.id.iv_video_image);
            this.tv_video_time = itemView.findViewById(R.id.tv_video_time);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
        }
    }
}
