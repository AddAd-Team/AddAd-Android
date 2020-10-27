package com.add.ad.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPref {
    Context context;

    @Inject
    public SharedPref(Context context){
        this.context = context;
    }

    public void saveToken(String token, Boolean access){
        getPref(context).edit().putString(getKey(access),token).apply();
    }

    public String getToken(Boolean access){
        if(getPref(context).getString(getKey(access), "") != null) {
            return "Bearer " + getPref(context).getString(getKey(access), "");
        } else return "";
    }
    private SharedPreferences getPref(Context context) {
        return context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    private String getKey(Boolean access) {
        if (access) return "Access";
        else return "Refresh";
    }
}
