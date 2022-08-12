package com.qxy.NoError;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.qxy.NoError.Database.AppDatabase;
import com.qxy.NoError.list.dao.ListDataDao;

/**
 * 我的主界面
 * @author 徐鑫
 */
public class TestActivity extends AppCompatActivity {
    public static final String TAG="TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ListDataDao listDataDao= AppDatabase.getDatabase().getListDataDao();
        Gson gson=new Gson();
        Log.d(TAG, "onCreate: "+gson.toJson(listDataDao.getAllData()));


    }
}