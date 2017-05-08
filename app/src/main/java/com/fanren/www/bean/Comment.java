package com.fanren.www.bean;

/**
 * Author:      JerryChow
 * Date:        2017/4/24 15:17
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 评论类
 */
public class Comment {
    private String content;//评论内容
    private DynamicItem post;//评论的动态
    private JerryUser auther;//评论者
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DynamicItem getPost() {
        return post;
    }

    public void setPost(DynamicItem post) {
        this.post = post;
    }

    public JerryUser getAuther() {
        return auther;
    }

    public void setAuther(JerryUser auther) {
        this.auther = auther;
    }
}
