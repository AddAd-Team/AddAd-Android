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
        getPref(context, true).edit().putString(getKey(access),token).apply();
    }

    public String getToken(Boolean access){
        if(getPref(context,true).getString(getKey(access), "").isEmpty()) {
            return "";
        } else return "Bearer " + getPref(context,true).getString(getKey(access), "");
    }

    public void saveInfo(String info, Boolean bool){
        getPref(context, false).edit().putString(getKey(bool),info).apply();
    }

    public String getInfo(Boolean bool){
         return getPref(context,false).getString(getKey(bool), "");
    }

    public void removeToken(Boolean bool){
        getPref(context, true).edit().remove(getKey(bool)).apply();
    }

    private SharedPreferences getPref(Context context, Boolean bool) {
        return bool ? context.getSharedPreferences("auth", Context.MODE_PRIVATE) : context.getSharedPreferences("info", Context.MODE_PRIVATE);
    }

    private String getKey(Boolean access) {
        if (access) return "Access";
        else return "Info";
    }
}
