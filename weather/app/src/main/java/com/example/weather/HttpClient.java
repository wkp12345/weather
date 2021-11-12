package com.example.weather;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 旋风小伙 on 2017/4/27.
 */

public class HttpClient {
    public static final String WEATHER_TYPE_BASE = "base";
    public static final String WEATHER_TYPE_ALL = "all";
    private static Gson jsonConvert = new Gson();

    private static OkHttpClient okHttpClient = new OkHttpClient();  //创建OkHttpClient对象

    private static final String SERVER_HOST = "http://restapi.amap.com/v3/weather/weatherInfo?";  //天气查询API服务地址

    public static <T> void query(String adcode, String type, final Class<T> tClass
            , final IHttpCallback callback){
        String parameters = "key=081dfed09d9dc82c1e9d75f7b937133c"  //请求参数
                + "&city=" + adcode
                + "&extensions=" + type
                + "&output=JSON";

        Request request = new Request.Builder()
                .url(SERVER_HOST + parameters) //请求接口
                .get()
                .build();   //创建Request 对象

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onSuccess(null,false);
            }

            @Override
            public void onResponse(Response response) throws IOException {   //得到Response 对象
                try{
                    callback.onSuccess(jsonConvert.fromJson(response.body().string(),tClass),  //fromJson方法可以将Json字符串转换成JavaBean，
                            true);                                                    // 但是要解析的JavaBean的属性必须
                                                                                              //是Json字符串中的字段，可以少于Json字符串中的字段。
                }catch (Exception e){
                    System.out.println("--------------"+"输入错误");
                    e.printStackTrace();
                }
            }
        });
    }

    public interface IHttpCallback {
        <T> void onSuccess(T result,boolean isSuccess);
    }

}
