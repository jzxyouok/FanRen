package com.fanren.www.bean;

import cn.bmob.v3.BmobObject;

/**
 * Author:      JerryChow
 * Date:        2017/5/5 14:51
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 发布动态需要上传的图片
 */
public class DynamicPicture extends BmobObject {
    private String path;    //图片路径
    private boolean isChoose;   //是否选择

    public DynamicPicture(boolean isChoose, String path) {
        this.isChoose = isChoose;
        this.path = path;
    }

    public DynamicPicture(String tableName, boolean isChoose, String path) {
        super(tableName);
        this.isChoose = isChoose;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
