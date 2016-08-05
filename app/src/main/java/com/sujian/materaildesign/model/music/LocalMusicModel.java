package com.sujian.materaildesign.model.music;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class LocalMusicModel implements ILocalMusicModel {
    @Override
    public List<Song> getLocalMusicList(Context context) {
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        List<Song> songs = new ArrayList<>();
        Song song;
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            song = new Song();
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //专辑
            long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
            if (isMusic != 0) { // 只把音乐添加到集合当中
                song.setId(id);
                song.setTitle(title);
                song.setArtist(artist);
                song.setAlbum(album);
                song.setAlbumId(albumId);
                song.setDuration(duration);
                song.setSize(size);
                song.setPath(url);
                song.setMusicType(Song.MusicType.LocalMusic);
                songs.add(song);
            }
        }
        return songs;
    }
}
