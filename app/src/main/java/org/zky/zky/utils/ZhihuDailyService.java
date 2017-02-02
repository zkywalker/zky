package org.zky.zky.utils;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by zkywalker on 2017/2/2.
 * package:org.zky.zky.utils
 */

public interface ZhihuDailyService {
    @GET("start-image/{px}")
    Call<SplashImage> getUrl(@Path("px") String px);
}
