package com.fanren.www.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Author:      JerryChow
 * Date:        2017/4/7 11:45
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 发布的动态帖子
 */
public class DynamicItem extends BmobObject {
    private JerryUser writer;   //动态发布者
    private List<BmobFile> photoList;//发布的图片
    private int count;              //获赞的数目
    private String content;         //发布的文字内容
    private String title;           //发布动态的缩写
    private BmobRelation likes;     //喜欢帖子的人;

    public JerryUser getWriter() {
        return writer;
    }

    public void setWriter(JerryUser writer) {
        this.writer = writer;
    }

    public List<BmobFile> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<BmobFile> photoList) {
        this.photoList = photoList;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
