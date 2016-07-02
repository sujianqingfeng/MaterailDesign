package com.sujian.materaildesign.uitls;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sujian.materaildesign.constant.Constant;


/**
 * SharedPreferences的封装
 *
 * @author 12111
 */
public class SharedPreferencesUitls {

    private static SharedPreferencesUitls inStance = null;
    private Context context;

    private SharedPreferencesUitls(Context context) {
        this.context = context;
    }


    public static SharedPreferencesUitls getInstance(Context context) {
        if (inStance == null) {
            synchronized (SharedPreferencesUitls.class) {
                if (inStance == null) {
                    inStance = new SharedPreferencesUitls(context);
                }
            }
        }

        return inStance;
    }

    /**
     * 得到一个字符串
     */
    public String getStringValue(String key, String defuleValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        return sp.getString(key, defuleValue);
    }

    /**
     * 得到一个boolean值
     */
    public boolean getBooleanValue(String key, boolean defuleValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        return sp.getBoolean(key, defuleValue);
    }

    /**
     * 设置一个字符串
     */
    public void setStringValue(String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 设置一个boolean值
     */
    public void setBooleanValue(String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    /**
     * 得到一个long值
     */
    public long getLongValue(String key, long defuleValue) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        return sp.getLong(key, defuleValue);
    }

    /**
     * 设置一个long
     */
    public void setLongValue(String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(Constant.INFO, Activity.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }


}
