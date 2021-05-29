package com.raon.sessionpreffer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreference {

    private final String PREF_NAME = "sessionfile";

    static Context mContext;
    SharedPreferences pref;

    public SessionPreference(Context c){
        mContext = c;
        pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
    }

    public void put(String key, String value){
         SharedPreferences.Editor editor = pref.edit();
         editor.putString(key, value);
         editor.commit();
    }

    public String getValue(String key, String dfValue){
        try{
            return pref.getString(key, dfValue);
        }
        catch(Exception e){
            return dfValue;
        }
    }
}
