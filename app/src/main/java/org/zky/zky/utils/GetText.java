package org.zky.zky.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 *
 * Created by zhan9 on 2016/12/15.
 */

public class GetText {
    private static Resources sResources;

    public static void init(Context context){
        sResources = context.getApplicationContext().getResources();
    }

    @NonNull
    public static String getString(@StringRes int id){
        return sResources.getString(id);
    }

    @NonNull
    public static String getString(@StringRes int id,Object... formatArgs){
        return sResources.getString(id,formatArgs);
    }
}
