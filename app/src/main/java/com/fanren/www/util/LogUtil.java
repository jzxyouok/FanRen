package com.fanren.www.util;

import android.os.Environment;
import android.util.Log;

import com.fanren.www.app.JerryApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:      JerryChow
 * Date:        2017/4/21 14:18
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class LogUtil {
    public static final String tag = "jerry";
    public static boolean log_file_write_mode;
    static File f;
    private static SimpleDateFormat fmt;

    public LogUtil() {
    }



    public static void d(String msg) {
        Exception e = new Exception();
        StackTraceElement[] element = e.getStackTrace();
        String log_msg = element[1].getFileName() + "\t[Line:" + element[1].getLineNumber() + "]\t" + element[1].getMethodName() + " -> " + msg;
        if(JerryApp.isDebug) {
            Log.d("LogUtil", log_msg);
        }

        if(log_file_write_mode) {
            fileWrite(log_msg);
        }

    }

    public static void e(String msg) {
        Exception e = new Exception();
        StackTraceElement[] element = e.getStackTrace();
        String log_msg = element[1].getFileName() + "\t[Line:" + element[1].getLineNumber() + "]\t" + element[1].getMethodName() + " -> " + msg;
        if(JerryApp.isDebug) {
            Log.e("LogUtil", log_msg);
        }

        if(log_file_write_mode) {
            fileWrite(log_msg);
        }

    }



    private static void fileWrite(String string) {
        if(f == null) {
            String raf = Environment.getExternalStorageDirectory() + "/f_log_" + fmt.format(new Date(System.currentTimeMillis())) + ".log";
            f = new File(raf);

            try {
                f.createNewFile();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        try {
            RandomAccessFile raf1 = new RandomAccessFile(f, "rw");
            raf1.seek(raf1.length());
            raf1.writeBytes("\r\n" + fmt.format(new Date(System.currentTimeMillis())) + "\t" + string);
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    static {
        if(JerryApp.isDebug) {
            ;
        }

        log_file_write_mode = false;
        f = null;
        fmt = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
    }
}
