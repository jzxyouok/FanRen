package com.fanren.www.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Author:      JerryChow
 * Date:        2017/4/21 11:08
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 广告轮播条bean类
 */
public class Banner extends BmobObject {
    private String title;   //轮播标题
    private String link;    //点击图片跳转的链接
    private BmobFile content;   //显示的图片

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public BmobFile getContent() {
        return content;
    }

    public void setContent(BmobFile content) {
        this.content = content;
    }


}

