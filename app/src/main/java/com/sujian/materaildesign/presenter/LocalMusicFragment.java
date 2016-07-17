package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.adapter.BaseRecylerViewAdapter;
import com.sujian.materaildesign.adapter.LocalMusicAdapter;
import com.sujian.materaildesign.adapter.LocalMusicHolder$$ViewBinder;
import com.sujian.materaildesign.delegate.LocalMusicFDeletate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.model.music.ILocalMusicModel;
import com.sujian.materaildesign.model.music.LocalMusicModel;
import com.sujian.materaildesign.model.music.Song;

import java.util.List;

/**
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class LocalMusicFragment extends FragmentPresenter<LocalMusicFDeletate> {

    private ILocalMusicModel localMusicModel = new LocalMusicModel();

    @Override
    protected Class<LocalMusicFDeletate> getDelegateClass() {
        return LocalMusicFDeletate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getLocalMusic();
        initAdapter();
    }

    private void initAdapter() {
        viewDelegate.getAdapter().setOnItemClickListener(new BaseRecylerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), MusicActivity.class);
                intent.putExtra("path", viewDelegate.getList().get(position).getPath());
                startActivity(intent);
            }
        });
    }

    private void getLocalMusic() {
        List<Song> localMusicList = localMusicModel.getLocalMusicList(getActivity());
        Logger.e(localMusicList.toString());
        if (localMusicList != null) {
            viewDelegate.refreshRecyclerView(localMusicList);
        }
    }
}
