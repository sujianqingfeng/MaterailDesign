package com.sujian.materaildesign.model.picture;


import com.sujian.materaildesign.frame.model.IModel;

import java.util.List;

/**
 * 图片返回json实体
 * Created by sujian on 2016/5/30.
 * Mail:121116111@qq.com
 */

public class PictureEntity implements IModel {

    //返回状态
    private int code;
    //返回的消息
    private String msg;
    //数据集合
    private List<PictureListItem> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PictureListItem> getNewslist() {
        return newslist;
    }


    public void setNewslist(List<PictureListItem> newslist) {
        this.newslist = newslist;
    }


    public class PictureListItem {
        //图片标题
        private String title;
        //图片描述
        private String description;
        //图片url
        private String picUrl;
        //详细地址
        private String url;
        //时间
        private String ctime;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "PictureEntity{" +
                    "description='" + description + '\'' +
                    ", title='" + title + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
