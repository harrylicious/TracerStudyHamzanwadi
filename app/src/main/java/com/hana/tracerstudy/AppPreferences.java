package com.hana.tracerstudy;

import android.content.Context;

public class AppPreferences {

    public final String mypref = "mypref";

    public void setLevel(Context context, String key, String level) {
        android.content.SharedPreferences pref = context.getSharedPreferences(mypref, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = pref.edit();


        editor.putString(key, level).apply();
    }

    public String getLevel(Context context, String key) {
        android.content.SharedPreferences pref = context.getSharedPreferences(mypref, Context.MODE_PRIVATE);

        return pref.getString(key, "alumni");
    }


}
