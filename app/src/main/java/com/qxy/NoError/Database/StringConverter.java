package com.qxy.NoError.Database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StringConverter {

    @TypeConverter
    public static String objectToString(List<String> list){
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<String> stringToObject(String json){
        Type listType = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(json,listType);
    }
}
