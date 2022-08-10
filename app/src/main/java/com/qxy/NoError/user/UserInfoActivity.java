package com.qxy.NoError.user;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.qxy.NoError.R;
import com.qxy.NoError.user.bean.UserInfo;
import com.qxy.NoError.user.vm.UserInfoViewModel;

public class UserInfoActivity extends AppCompatActivity {
    private UserInfoViewModel viewModel;
    private String TAG="UserInfoActivity";

    private Button bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.go_to_auth);
        viewModel=new ViewModelProvider(this).get(UserInfoViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                bt.setText("message=="+userInfo.getMessage());
            }
        });

    }
}
