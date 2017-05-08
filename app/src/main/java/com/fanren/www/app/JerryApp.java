package com.fanren.www.app;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fanren.www.constant.Constant;
import com.fanren.www.event.JerryMessageHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Stack;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Author:      JerryChow
 * Date:        2017/3/28 18:23
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class JerryApp extends Application {
    private static JerryApp instance;
    private static Stack<Activity> activityList = null;
    private static PackageInfo packageInfo;

    public static final boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(Constant.APPLICATION_ID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

        BmobIM.init(this);
        BmobIM.registerDefaultMessageHandler(new JerryMessageHandler());

        instance = this;

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

        activityList = new Stack<>();

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JerryApp getJerryApp() {
        return instance;
    }

    /**
     * 获取当前app版本号
     *
     * @return
     */
    public static String getVersionName() {
        return packageInfo.versionName;
    }

    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new Stack<Activity>();
        }
        activityList.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public static Activity currentActivity() {

        if (activityList.size() > 0) {
            return activityList.lastElement();
        }
        return null;
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
            activity.finish();

        }
    }

    /**
     * 结束所有activity
     */
    private static void finishAllActivity() {
        for (int i = 0; i < activityList.size(); i++) {
            if (null != activityList.get(i)) {
                activityList.get(i).finish();
            }
        }
        activityList.clear();
    }

    /**
     * 退出应用程序
     */
    public static void Exit() {
        finishAllActivity();
    }
}
