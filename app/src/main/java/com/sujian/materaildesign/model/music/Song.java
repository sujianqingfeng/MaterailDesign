package com.sujian.materaildesign.model.music;

/**
 * 歌曲实体类
 * Created by sujian on 2016/7/16.
 * Mail:121116111@qq.com
 */
public class Song {
    private long id;
    private String title;
    private long size;
    private long duration;
    private String artist;
    private String album;
    private long albumId;
    private String path;
    private MusicType musicType;
    private String picUrl;
    private String lycUrl;

    public enum MusicType {
        LocalMusic, NetworkMusic
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MusicType getMusicType() {
        return musicType;
    }

    public void setMusicType(MusicType musicType) {
        this.musicType = musicType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getLycUrl() {
        return lycUrl;
    }

    public void setLycUrl(String lycUrl) {
        this.lycUrl = lycUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "album='" + album + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", albumId=" + albumId +
                ", path='" + path + '\'' +
                ", musicType=" + musicType +
                ", picUrl='" + picUrl + '\'' +
                ", lycUrl='" + lycUrl + '\'' +
                '}';
    }
}
