package com.zzc.elegantcommunity.binder.activitylist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzc.elegantcommunity.R;
import com.zzc.elegantcommunity.activity.activityDetails.ActivityDetailsActivity;
import com.zzc.elegantcommunity.model.issueactivity.BriefActivityModel;
import com.zzc.elegantcommunity.util.ImageLoader;
import com.zzc.elegantcommunity.widget.CircleImageView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Meiji on 2017/6/8.
 */

public class ActivitListViewBinder extends ItemViewBinder<BriefActivityModel, ActivitListViewBinder.ViewHolder> {

    private static final String TAG = "ActivitListViewBinder";
//    private Context context;
//
//    public ActivitListViewBinder(Context context){
//        this.context=context;
//    }

    @NonNull
    @Override
    protected ActivitListViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_activity_brief_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ActivitListViewBinder.ViewHolder holder, @NonNull final BriefActivityModel item) {

        final Context context = holder.itemView.getContext();
        holder.tv_title.setText(item.getTitle());
        holder.tv_extra.setText(item.getContent()+"id:"+item.getId());
        ImageLoader.loadCenterCrop(context, item.getPoster(), holder.iv_video_image, R.color.viewBackground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityDetailsActivity.class);
                intent.putExtra("BriefActivityModel",item);
                context.startActivity(intent);
            }
        });
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
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
        }
    }
}
