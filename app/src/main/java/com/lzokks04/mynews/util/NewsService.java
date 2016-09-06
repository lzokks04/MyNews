package com.lzokks04.mynews.util;

import com.lzokks04.mynews.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Liu on 2016/8/30.
 */
public interface NewsService {
    @GET("toutiao/index")
    Observable<NewsBean> getNews(@Query("type") String newsType, @Query("key") String key);

}
