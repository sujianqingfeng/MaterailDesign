package com.sujian.materaildesign;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.model.music.INetworkMusicModel;
import com.sujian.materaildesign.model.music.LinkSongList;
import com.sujian.materaildesign.model.music.NetworkMusicModel;

import org.junit.Test;
import org.junit.runner.RunWith;


import rx.Subscriber;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentationTest {
    @Test
    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.sujian.materaildesign", appContext.getPackageName());

        INetworkMusicModel iNetworkMusicModel = new NetworkMusicModel();
        iNetworkMusicModel.getLinkSongList(1, 10, new Subscriber<LinkSongList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(LinkSongList linkSongList) {
                Logger.e("ceshishuju" + linkSongList.toString());
            }
        });
    }
}