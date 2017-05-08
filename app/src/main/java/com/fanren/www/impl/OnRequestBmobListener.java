package com.fanren.www.impl;

import cn.bmob.v3.exception.BmobException;

/**
 * Author:      JerryChow
 * Date:        2017/3/23 10:32
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 请求bmob服务器回调
 */
public interface OnRequestBmobListener<T> {
     void onSuccess(T t);
     void onFail(BmobException e);
}
