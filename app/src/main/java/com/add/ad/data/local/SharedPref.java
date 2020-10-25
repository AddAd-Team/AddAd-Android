package com.add.ad.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private Context mContext;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;
    private static SharedPref instance;

    public static SharedPref init(Context context){
        if(instance == null)
            instance = new SharedPref(context);
        return instance;
    }

    private SharedPref(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences("pref", Context.MODE_PRIVATE );
        prefsEditor = prefs.edit();
    }

    public static String read(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public static void write(String key, String value) {
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }
}