package com.fanren.www.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.fanren.www.app.JerryApp;
import com.fanren.www.constant.Constant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:      JerryChow
 * Date:        2017/3/29 10:47
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class CommonUtil {
    private static String TAG="jerry";
    public static boolean log_file_write_mode;
    //长吐司
    public static void showLong(String text){
        Toast.makeText(JerryApp.getJerryApp(),text,Toast.LENGTH_LONG).show();
    }
    //短吐司
    public static void showShort(String text){
        Toast.makeText(JerryApp.getJerryApp(),text,Toast.LENGTH_SHORT).show();
    }


    //动态生成布局id
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    @SuppressLint({"NewApi"})
    public static int generateViewId() {
        if(Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        } else {
            int result;
            int newValue;
            do {
                result = sNextGeneratedId.get();
                newValue = result + 1;
                if(newValue > 16777215) {
                    newValue = 1;
                }
            } while(!sNextGeneratedId.compareAndSet(result, newValue));

            return result;
        }
    }
    public static void setFullScreen(Activity activity) {
        activity. getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //判断是否第一次登陆
    public static boolean isFirstLogin(Context context){

        SharedPreferences sharedPreferences=context.getSharedPreferences(Constant.SHREF_NAME, Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean(Constant.ISLOGIN_KEY,true);
        if(isLogin){
            sharedPreferences.edit().putBoolean(Constant.ISLOGIN_KEY,false).apply(); //apply效率高
        }
        return isLogin;
    }
    @SuppressWarnings("unchecked")
    public static <T extends View> T getAdapterView(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
