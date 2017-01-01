package org.zky.zky.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.zky.zky.BaseThemeActivity;
import org.zky.zky.R;

/**
 * Created by zkywalker on 2017/1/1.
 * package:org.zky.zky.utils
 */

public class PreferenceUtils {

    public static String getTheme(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(GetText.getString(R.string.key_theme), BaseThemeActivity.THEME_DEFAULT);
    }

    public static void setTheme(Context context,String theme){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(GetText.getString(R.string.key_theme),theme).apply();
    }
}
