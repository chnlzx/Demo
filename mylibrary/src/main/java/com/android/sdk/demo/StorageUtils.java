package com.android.sdk.demo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class StorageUtils {

    //获取缓存文件目录
    public static File getCacheFile(Context context) {
        File appCacheDir = null;

        if (Environment.MEDIA_MOUNTED
                .equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
            if(appCacheDir.exists() == false){
                appCacheDir.mkdirs();
            }
        }
        return appCacheDir;
    }

    //获取文件目录
    public static File getFile(Context context) {
        File appDir = null;
        if (Environment.MEDIA_MOUNTED
                .equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appDir = getExternalFilesDir(context);
        }
        if (appDir == null) {
            appDir = context.getFilesDir();
        }
        if (appDir == null) {
            String cacheDirPath = "/data/data/" + context.getPackageName() + "/files/";
            appDir = new File(cacheDirPath);
            if(appDir.exists() == false){
                appDir.mkdirs();
            }
        }
        return appDir;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    //获取外部缓存目录
    private static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    //获取外部文件目录
    private static File getExternalFilesDir(Context context) {
        return context.getExternalFilesDir("");
    }

    //获取内部缓存目录
    private static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    //获取内部文件目录
    private static File getFilesDir(Context context) {
        return context.getFilesDir();
    }

    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}