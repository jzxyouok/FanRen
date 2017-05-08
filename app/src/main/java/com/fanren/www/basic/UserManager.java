package com.fanren.www.basic;


import com.fanren.www.bean.DynamicItem;
import com.fanren.www.bean.JerryUser;
import com.fanren.www.impl.OnRequestBmobListener;
import com.fanren.www.util.CommonUtil;
import com.fanren.www.util.LogUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Author:      JerryChow
 * Date:        2017/4/21 11:29
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description: 用户管理类
 */
public class UserManager {
    private static UserManager userManager;

    /**
     * 获取当前用户管理类对象
     *
     * @return
     */
    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public JerryUser getCurUser() {
        return BmobUser.getCurrentUser(JerryUser.class);
    }

    /**
     * 根据手机,密码注册账号
     *
     * @param phone
     * @param pwd
     */
    public void registerByPhone(String phone, String pwd, final OnRequestBmobListener listener) {
        JerryUser user = new JerryUser();
        user.setMobilePhoneNumber(phone);
        user.setUsername(phone);
        user.setPassword(pwd);
        user.signUp(new SaveListener<JerryUser>() {
            @Override
            public void done(JerryUser user, BmobException exception) {
                if (exception == null) {
                    listener.onSuccess(user);
                } else {
                    listener.onFail(exception);
                }
            }
        });
    }

    /**
     * 根据手机,密码登录
     *
     * @param phone
     * @param password
     * @param listener
     */
    public void loginByPhone(String phone, String password, final OnRequestBmobListener listener) {
        BmobUser.loginByAccount(phone, password, new LogInListener<JerryUser>() {
            @Override
            public void done(JerryUser user, BmobException e) {
                if (user != null) {
                    listener.onSuccess(user);   //登录成功
                } else {
                    listener.onFail(e);
                    LogUtil.d("失败:" + "ErrorCode:" + e.getErrorCode() + ",ErrorMsg:" + e.getMessage());
                }
            }
        });


    }

    /**
     * 退出登录
     */
    public void logout() {
        BmobUser.logOut();
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @param listener
     */
    public void updateUserInfo(final JerryUser user, final OnRequestBmobListener<JerryUser> listener) {
        BmobQuery<JerryUser> query = new BmobQuery();
        query.addWhereEqualTo("username", user.getUsername());
        query.findObjects(new FindListener<JerryUser>() {
            @Override
            public void done(List<JerryUser> list, BmobException e) {
                if (list != null) {
                    listener.onSuccess(list.get(0));

                } else {
                    listener.onFail(e);
                }
            }
        });
    }

    /**
     * 获取动态
     * @param sort
     * @param limit
     * @param page
     * @param listener
     */
    public void getDynamic(String sort,int limit,int page, final OnRequestBmobListener listener){
        BmobQuery<DynamicItem> query = new BmobQuery<DynamicItem>();
        query.order(sort);
        query.setLimit(limit);
        query.setSkip(page*10-10);
        query.findObjects(new FindListener<DynamicItem>() {
            @Override
            public void done(List<DynamicItem> list, BmobException e) {
                if (list != null) {
                    if(list.size()==0){
                        CommonUtil.showLong("没有更多数据");
                    }
                    listener.onSuccess(list);

                } else {
                    listener.onFail(e);
                }
            }
        });
    }

    /**
     * 发送动态
     * @param item
     * @param listener
     */
    public void sendDynamic(final DynamicItem item, final OnRequestBmobListener listener){

        if(item.getPhotoList().size()!=0){  //发送了图片动态信息
            final String [] photoPath=new String[item.getPhotoList().size()];
            for (int i = 0; i <item.getPhotoList().size() ; i++) {
                photoPath[i]=item.getPhotoList().get(i).getLocalFile().getAbsolutePath();
            }
            BmobFile.uploadBatch(photoPath, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if(list1.size()==photoPath.length){     //上传成功
                        item.setPhotoList(list);
                        item.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){
                                    listener.onSuccess(null);
                                }else{
                                    listener.onFail(e);
                                }
                            }
                        });
                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {

                }

                @Override
                public void onError(int i, String s) {
                    listener.onFail(new BmobException(i,s));
                }
            });
        }else{                              //只发送文字动态信息
            item.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e==null){
                        listener.onSuccess(null);
                    }else{
                        listener.onFail(e);
                    }
                }
            });
        }

    }
}
