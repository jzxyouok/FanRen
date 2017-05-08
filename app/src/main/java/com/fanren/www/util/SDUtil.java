package com.fanren.www.util;

import android.os.Environment;

import java.io.File;

/**
 * Author:      JerryChow
 * Date:        2017/3/10 10:37
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 */
public class SDUtil {
    /**
     * 返回SD卡是否挂载
     * @return
     */
    public static boolean isSDCardMounted() {
        //首先获取SD卡状态
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
    /**
     * 返回SD卡根目录
     * @return
     */
    public static String getSDCardRootDir(){
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return externalStorageDirectory.getPath();
    }

    public static String getAbsolutePath(){
        if(!isSDCardMounted()){
            LogUtil.d("SD卡没挂载....");
            return null;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
