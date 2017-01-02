package org.zky.zky.widget.Indicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by zkywalker on 2017/1/2.
 * package:org.zky.zky.widget.Indicator
 */

public interface IndicatorController {
    View newInstance(@NonNull Context context);
    void initialize(int count);
    void selectPosition(int position);
}
