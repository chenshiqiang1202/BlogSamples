/**
 * description :
 * Created by csq E-mail:csqwyyx@163.com
 * 15-4-26
 */
package com.csq.mvcdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SpUtil {

    // ------------------------ Constants ------------------------
    private static final String PREFS_NAME = "com.csq.mvcdemo.prefs";

    public static final String KEY_TRACK_RECORD_INFO = "KEY_TRACK_RECORD_INFO";

    // ------------------------- Fields --------------------------


    // ----------------------- Constructors ----------------------


    // -------- Methods for/from SuperClass/Interfaces -----------


    // --------------------- Methods public ----------------------

    private static HashMap<String, Integer> cachedIntValues = new HashMap<String, Integer>();
    /**
     * 通用int的保存读取
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(editor!=null){
            editor.putInt(key, value);
            editor.commit();

            cachedIntValues.put(key, value);
        }
    }
    public static int getInt(Context context, String key, int defValue){
        Integer cache = cachedIntValues.get(key);
        if(cache != null){
            return cache;
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, defValue);
    }


    private static HashMap<String, Long> cachedLongValues = new HashMap<String, Long>();
    /**
     * 通用long的保存读取
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(editor!=null){
            editor.putLong(key, value);
            editor.commit();

            cachedLongValues.put(key, value);
        }
    }
    public static long getLong(Context context, String key, long defValue){
        Long cache = cachedLongValues.get(key);
        if(cache != null){
            return cache;
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(key, defValue);
    }


    private static HashMap<String, Boolean> cachedBooleanValues = new HashMap<String, Boolean>();
    /**
     * 通用boolean的保存读取
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(editor!=null){
            editor.putBoolean(key, value);
            editor.commit();

            cachedBooleanValues.put(key, value);
        }
    }
    public static boolean getBoolean(Context context, String key, boolean defValue){
        Boolean cache = cachedBooleanValues.get(key);
        if(cache != null){
            return cache;
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defValue);
    }

    /**
     * 通用String的保存读取
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(editor!=null){
            editor.putString(key, value);
            editor.commit();
        }
    }
    public static String getString(Context context, String key, String defValue){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, defValue);
    }

    // --------------------- Methods private ---------------------


    // --------------------- Getter & Setter -----------------


    // --------------- Inner and Anonymous Classes ---------------


    // --------------------- logical fragments -----------------

}
