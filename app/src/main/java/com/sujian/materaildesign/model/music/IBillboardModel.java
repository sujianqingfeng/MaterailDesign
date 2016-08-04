package com.sujian.materaildesign.model.music;

import java.util.List;

import rx.Subscriber;

/**
 * 榜单接口模型
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public interface IBillboardModel {
    void getBillboard(Integer[] types, Subscriber<Billboard> subscriber);

    void unsubscribe();
}
