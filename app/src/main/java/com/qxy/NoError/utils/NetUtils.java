package com.qxy.NoError.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.qxy.NoError.MyApplication;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * 网络相关的工具类
 *
 * @author 徐鑫
 */
public class NetUtils {

    private static final String TAG = "NetUtils";

    private static final String IP_PRE = "https://open.douyin.com/";

    /**
     * 创建retrofit实例
     *
     * @param tClass 需要创建的接口类型
     * @param <T>    接口类型
     * @return 创建的接口实例
     * todo 每一次调用都需要创建一个okhttp客户端，是否可以优化？
     */
    public static <T> T createRetrofit(Class<T> tClass) {
        return createRetrofit(tClass, MyApplication.CLIENT_TOKEN);
    }

    public static <T> T createRetrofit(Class<T> tClass,String token) {
        ObjectMapper mapper = new ObjectMapper();
        //将下划线转成驼峰式
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        MyApplication instance = MyApplication.getInstance();
        String accessToken = null;
        //判断token时access_token还是client_token,并赋给accessToken相应的值
        if(token.equals(MyApplication.ACCESS_TOKEN)){
            accessToken  = instance.get(MyApplication.ACCESS_TOKEN);
        }else if (token.equals(MyApplication.CLIENT_TOKEN)){
            accessToken=instance.get(MyApplication.CLIENT_TOKEN);
            if(accessToken == null){
                accessToken = "clt:";
            }
        }
        Log.d(TAG, "createRetrofit: "+accessToken);
        //给请求添加请求头
        OkHttpClient okHttpClient = getOkHttpClient(MyApplication.ACCESS_TOKEN
                , accessToken == null
                        ? Constants.ACCESS_TOKEN
                        : accessToken);

        Retrofit build = new Retrofit.Builder()
                .baseUrl(IP_PRE)
                //使用RxJava
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                //使用jackson解析，并且将_格式数据转化为驼峰式
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(okHttpClient)
                .build();
        return build.create(tClass);
    }


    /**
     * 创建一个okhttp客户端
     *
     * @param heads 需要添加的请求头信息，注意需要成对出现
     *              例如 getOkHttpClient("auth_code", "123", "Content-Type", "json")
     *              就会添加上两个请求头，分别是
     *              auth_code: 123
     *              Content-Type: json
     * @return 创建好的实例
     */
    public static OkHttpClient getOkHttpClient(String... heads) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        int length = heads.length;
        if (length == 0 || (length - ((length >> 1) << 1)) != 0) {
            //heads长度不是偶数则返回一个最简单的client
            return builder.build();
        }
        return builder.addInterceptor(chain -> {
                    Request.Builder builder1 = chain.request().newBuilder();
                    for (int i = 0; i < heads.length - 1; i += 2) {
                        builder1.addHeader(heads[i], heads[i + 1]);
                    }
                    Request request = builder1.build();
                    return chain.proceed(request);
                })
                //5秒超时
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * 刷新client_token
     * @param finishCliTokenCallBack 用于处理完成后的回调函数
     */
    public static void refreshClientToken(FinishCliTokenCallBack finishCliTokenCallBack) {

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/oauth/client_token/")).newBuilder();
        HttpUrl url = urlBuilder.addQueryParameter("client_key", Constants.CLIENT_KEY)
                .addQueryParameter("client_secret", Constants.CLIENT_SECRET)
                .addQueryParameter("grant_type", "client_credential").build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "multipart/form-data")
                .get()
                .build();

        Call call = getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: 重新获取client_token失败！错误信息：" + e.getMessage() + "造成原因：" + e.getCause());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: 获取client_token的请求码" + response.code());
                if (response.code() != 200) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return;
                }
                String dataStr = responseBody.string();
                Log.d(TAG, "onResponse: 重新获取client_token成功, 获取到的数据为：" + dataStr);
                if (StrUtil.isEmpty(dataStr)) {
                    return;
                }
                JSONObject jsonObject = JSONUtil.parseObj(dataStr);
                JSONObject data = jsonObject.getJSONObject("data");
                if (data.getInt("error_code") == 0) {
                    String accessToken = data.getStr("access_token");
                    MyApplication.getInstance().put(MyApplication.CLIENT_TOKEN, accessToken);
                    //通过回调机制，通知完成获取token
                    finishCliTokenCallBack.dealData();
                }
            }
        });
    }

    /**
     * 获取access_token
     */
    public static void getAccessToken() {

        HttpUrl url = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/oauth/access_token/"));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("client_secret", Constants.CLIENT_SECRET);
        builder.add("code",MyApplication.getInstance().get(MyApplication.AUTH_CODE));
        builder.add("grant_type","authorization_code");
        builder.add("client_key",Constants.CLIENT_KEY);
        RequestBody formBody=builder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        Call call = getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: 重新获取啊accessToken失败！错误信息：" + e.getMessage() + "造成原因：" + e.getCause());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: 获取accessToken的请求码" + response.code());
                if (response.code() != 200) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return;
                }
                String dataStr = responseBody.string();
                Log.d(TAG, "onResponse: 重新获取accessToken成功, 获取到的数据为：" + dataStr);
                if (StrUtil.isEmpty(dataStr)) {
                    return;
                }
                JSONObject jsonObject = JSONUtil.parseObj(dataStr);
                JSONObject data = jsonObject.getJSONObject("data");
                if (data.getInt("error_code") == 0) {
                    String accessToken = data.getStr("access_token");
                    String openId = data.getStr("open_id");
                    //把open_id和access_token存入全局变量
                    MyApplication.getInstance().put(MyApplication.OPEN_ID, openId);
                    MyApplication.getInstance().put(MyApplication.ACCESS_TOKEN, accessToken);
                    //把authCode存储到SharedPreferences文件中
                    SharedPreferences.Editor edt = MyApplication.getAppContext().getSharedPreferences("data",MODE_PRIVATE).edit();
                    edt.putString(MyApplication.ACCESS_TOKEN,accessToken);
                    edt.putString(MyApplication.OPEN_ID,openId);
                    edt.commit();
                }
            }
        });
    }



    /**
     * 用于通知获取client-token完成
     */
    public interface FinishCliTokenCallBack {
        /**
         * 回调函数，通知操作已完成
         */
        void dealData();
    }
}
