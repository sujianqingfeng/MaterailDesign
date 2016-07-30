package com.sujian.materaildesign;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.model.music.INetworkMusicModel;
import com.sujian.materaildesign.model.music.LinkSongList;
import com.sujian.materaildesign.model.music.NetworkMusicModel;

import org.junit.Test;

import rx.Subscriber;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        INetworkMusicModel iNetworkMusicModel = new NetworkMusicModel();
        iNetworkMusicModel.getLinkSongList(1, 10, new Subscriber<LinkSongList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e("shibai");
            }

            @Override
            public void onNext(LinkSongList linkSongList) {
                Logger.e(linkSongList.toString());
            }
        });
    }
}