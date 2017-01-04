package org.zky.zky.widget.Indicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by zkywalker on 2017/1/4.
 * package:org.zky.zky.widget.Indicator
 */

public interface TimeIndicatorController {
    View newInstance(@NonNull Context context);
    void initialize(long count);
    void start();
    void setOnCountDownListener(TimeIndicatorControllerImpl.OnCountDownListener listener);
}
