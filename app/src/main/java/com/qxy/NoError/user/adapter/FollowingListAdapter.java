package com.qxy.NoError.user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.NoError.R;
import com.qxy.NoError.user.bean.FanListData;
import com.qxy.NoError.user.bean.FollowListData;

import java.util.ArrayList;
import java.util.List;

public class FollowingListAdapter extends RecyclerView.Adapter<FollowingListAdapter.InnerHolder> {

    List<FollowListData> data=new ArrayList<>();
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fan,parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        FollowListData followListData = data.get(position);
        holder.setData(followListData);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FollowListData> followList) {
        data.clear();
        data.addAll(followList);

    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        ImageView avatarIcon,gender;
        TextView nickName,fanLocation;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            avatarIcon=itemView.findViewById(R.id.avatar_icon);
            gender=itemView.findViewById(R.id.gender);
            nickName=itemView.findViewById(R.id.nickname);
            fanLocation=itemView.findViewById(R.id.fan_location);
        }

        public void setData(FollowListData fanListData) {
            Glide.with(itemView.getContext()).load(fanListData.getAvatar()).into(avatarIcon);
            if (fanListData.getGender()==1){
                gender.setImageResource(R.drawable.male);
            }else {
                gender.setImageResource(R.drawable.female);
            }
            nickName.setText(fanListData.getNickname());

            String location;
            if(fanListData.getCountry().length() == 0){
                location = "中国";
            }else if(fanListData.getProvince().length() == 0){
                location = fanListData.getCountry()+" | "+fanListData.getCity();
            }else{
                location = fanListData.getCountry()+" | "+fanListData.getProvince()+" | "+fanListData.getCity();
            }
            fanLocation.setText(location);
        }
    }
}
