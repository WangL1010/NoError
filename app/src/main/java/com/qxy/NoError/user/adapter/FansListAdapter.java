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

import java.util.ArrayList;
import java.util.List;

public class FansListAdapter extends RecyclerView.Adapter<FansListAdapter.InnerHolder> {

    List<FanListData> data=new ArrayList<>();
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fan,parent,false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        FanListData fansData=data.get(position);
        holder.setData(fansData);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FanListData> fanListData) {
        data.clear();
        data.addAll(fanListData);
    }

    public void addData(List<FanListData> fanListData){
        data.addAll(fanListData);
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

        public void setData(FanListData fansData) {
            Glide.with(itemView.getContext()).load(fansData.getAvatar()).into(avatarIcon);
            if (fansData.getGender()==1){
                gender.setImageResource(R.drawable.male);
            }else {
                gender.setImageResource(R.drawable.female);
            }
            nickName.setText(fansData.getNickname());
            String location=fansData.getCountry()+" | "+fansData.getProvince()+" | "+fansData.getCity();
            fanLocation.setText(location);
        }
    }
}
