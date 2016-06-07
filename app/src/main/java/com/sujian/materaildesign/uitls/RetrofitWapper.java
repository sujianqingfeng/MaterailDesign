package com.sujian.materaildesign.uitls;

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

    /**
     * 将构造函数私有化
     */
    private RetrofitWapper() {
        mRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    /**
     * 获取RetrofitWapper实例（单例模式）
     *
     * @return
     */
    public static RetrofitWapper getRetrofitWapperInstance() {
        if (mRetrofitWapper == null) {
            synchronized (RetrofitWapper.class) {
                mRetrofitWapper = new RetrofitWapper();
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


    public class Constant {
        public static final String BASE_URL = "http://apis.baidu.com/";
    }

}
