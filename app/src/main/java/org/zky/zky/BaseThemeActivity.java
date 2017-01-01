package org.zky.zky;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import org.zky.zky.utils.PreferenceUtils;

/**
 * Created by zkywalker on 2017/1/1.
 * package:org.zky.zky
 */

public class BaseThemeActivity extends AppCompatActivity {

    public final static String THEME_DEFAULT = "-1";
    public final static String THEME_BLUE = "0";
    public final static String THEME_RED = "1";
    public final static String THEME_GREEN = "2";
    public final static String THEME_CYAN = "3";
    public final static String THEME_PURPLE = "4";
    public final static String THEME_ORANGE = "5";
    public final static String THEME_PINK = "6";
    public final static String THEME_TEAL = "7";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }

    private void updateTheme() {
        Log.e("tag", "updateTheme: ----");
        switch (PreferenceUtils.getTheme(getApplicationContext())){
            case THEME_DEFAULT:

                applyTheme(R.style.AppTheme);
                break;
            case THEME_BLUE:
                applyTheme(R.style.AppTheme_Blue);
                break;
            case THEME_RED:
                applyTheme(R.style.AppTheme_Red);
                break;
            case THEME_GREEN:
                applyTheme(R.style.AppTheme_Green);
                break;
            case THEME_CYAN:
                applyTheme(R.style.AppTheme_Cyan);
                break;
            case THEME_PURPLE:
                applyTheme(R.style.AppTheme_Purple);
                break;
            case THEME_ORANGE:
                applyTheme(R.style.AppTheme_Orange);
                break;
            case THEME_PINK:
                applyTheme(R.style.AppTheme_Pink);
                break;
            case THEME_TEAL:
                applyTheme(R.style.AppTheme_Teal);
                break;
        }
    }

    private void applyTheme(int r){
        setTheme(r);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getPrimaryDark(this));
        }
    }

    public static int getAccentColor(final Context context){
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }

    public static int getPrimaryDark(final Context context) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, value, true);
        return value.data;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
