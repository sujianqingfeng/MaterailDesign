package com.sujian.materaildesign.uitls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sujian on 2016/6/2.
 * Mail:121116111@qq.com
 */
public class RetrofitWapper {

    private static RetrofitWapper mRetrofitWapper;
    private Retrofit mRetrofit;
    private Retrofit.Builder builder;


    /**
     * 将构造函数私有化
     */
    private RetrofitWapper() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        builder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    /**
     * 设置根url
     * @param url
     * @return
     */
    public Retrofit setBaseUrl(String url) {
        mRetrofit = builder.baseUrl(url)
                .build();
        return mRetrofit;
    }

    /**
     * 获取RetrofitWapper实例（单例模式）
     *
     * @return
     */
    public static RetrofitWapper getRetrofitWapperInstance() {
        if (mRetrofitWapper == null) {
            synchronized (RetrofitWapper.class) {
                if (mRetrofitWapper == null) {
                    mRetrofitWapper = new RetrofitWapper();
                }
            }
        }
        return mRetrofitWapper;
    }

    /**
     * 创建接口访问入口
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


}
