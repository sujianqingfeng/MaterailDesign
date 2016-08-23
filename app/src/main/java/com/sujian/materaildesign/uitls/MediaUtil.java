package com.sujian.materaildesign.uitls;

import com.sujian.materaildesign.rxkit.SchedulersCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by sujian on 2016/8/11.
 * Mail:121116111@qq.com
 */
public class MediaUtil {
    /**
     * 将毫秒转换成秒
     * @param time
     * @return
     */
    public static int formatTime(int time){
        return time/1000;
    }

    /**
     * 将毫秒转换成字符串
     * @param time
     * @return
     */
    public static String formaTime(int time){
        DecimalFormat format = new DecimalFormat("00");
        return format.format(formatTime(time) / 60) + ":" + format.format(time / 1000 % 60);
    }

    /**
     * 通过url得到输入流
     * @param url
     * @param sub
     */
    public static void getInputStreamByUrl(String url, Subscriber<InputStream> sub){
        Observable.just(url)
                .map(new Func1<String, InputStream>() {
                    @Override
                    public InputStream call(String s) {
                        return getInputStream(s);
                    }
                })
                .compose(SchedulersCompat.<InputStream>applyIoSchedulers())
                .subscribe(sub);
    }


    private static InputStream getInputStream(String url){
        try {
            URL u=new URL(url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) u.openConnection();
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
