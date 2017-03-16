package org.zky.zky.utils.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zkywalker on 2017/2/2.
 * package:org.zky.zky.utils
 */

public interface ZhihuDailyService {
    @GET("start-image/{px}")
    rx.Observable<SplashImage> getUrl(@Path("px") String px);

    @GET("start-image/{px}")
    Call<SplashImage> getUrl2(@Path("px") String px);
}
