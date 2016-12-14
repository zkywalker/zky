package org.zky.zky;

import android.app.Application;

import org.zky.zky.utils.GetText;

/**
 * 包:org.zky.zky
 * Created by zhangkun on 2016/12/13.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        GetText.init(this);

    }
}
