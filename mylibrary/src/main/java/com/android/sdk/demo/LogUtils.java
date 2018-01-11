package com.android.sdk.demo;

import android.util.Log;

public class LogUtils {

    /**
     * 是否开启debug
     */
    private static boolean DEBUG = true;

    public static void setDebug(boolean debug){
        DEBUG = debug;
    }

    /**
     * 错误
     */
    public static void e(Class<?> clazz, String msg) {
        if (DEBUG) {
            Log.e(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 信息
     */
    public static void i(Class<?> clazz, String msg) {
        if (DEBUG) {
            Log.i("tag", msg + "");
        }
    }

    public static void D(String msg){
        if (DEBUG){
            Log.d("itchen", msg);
        }
    }

    /**
     * 警告
     */
    public static void w(Class<?> clazz, String msg) {
        if (DEBUG) {
            Log.w(clazz.getSimpleName(), msg + "");
        }
    }
}
