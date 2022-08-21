package com.qxy.NoError.user.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.NoError.R;
import com.qxy.NoError.user.bean.VideoListData;

import java.util.ArrayList;
import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.InnerHolder> {
    List<VideoListData> videoListData = new ArrayList<>();
    private OnListItemClickListener mItemClickListener;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        VideoListData data = this.videoListData.get(position);
        holder.setData(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    VideoListData data = VideoListAdapter.this.videoListData.get(position);
                    mItemClickListener.onItemClick(data);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoListData.size();
    }

    public void setData(List<VideoListData> data) {
        videoListData.clear();
        if(data != null && data.size() == 0){
            videoListData.addAll(data);
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        ImageView coverVideo;
        CardView isTop;
        TextView playNumber,commentNumber;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            coverVideo=itemView.findViewById(R.id.cover_video);
            playNumber=itemView.findViewById(R.id.play_number_videolist);
            commentNumber=itemView.findViewById(R.id.comment_number_videolist);
            isTop=itemView.findViewById(R.id.isTop);
        }

        public void setData(VideoListData data) {
            Glide.with(itemView.getContext()).load(data.getCover()).into(coverVideo);
            playNumber.setText(""+data.getStatistics().getPlayCount());
            commentNumber.setText(""+data.getStatistics().getCommentCount());
            if (data.getIsTop()){
                isTop.setVisibility(View.VISIBLE);
            }else {
                isTop.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setOnListItemClickListener(OnListItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnListItemClickListener {
        void onItemClick(VideoListData videoData);
    }
}
