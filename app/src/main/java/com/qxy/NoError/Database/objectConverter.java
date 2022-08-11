package com.qxy.NoError.Database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.List;

public class objectConverter {

    /**
     * List<String> 类型转换
     * @param list
     * @return
     */
    @TypeConverter
    public static String stringsToString(List<String> list){
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<String> stringToStrings(String json){
        Type listType = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(json,listType);
    }

}

