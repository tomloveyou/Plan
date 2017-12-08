package com.example.hp.testapplication.api;


import com.standards.library.network.retrofit.RetrofitDao;

import okhttp3.HttpUrl;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public class Dao {
    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (Dao.class) {
                if (mApiService == null) {
                    mApiService = RetrofitDao.buildRetrofit(builder -> buildPublicParams(builder)).create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    private static HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {

        builder.addQueryParameter("companyid", "2358");
        builder.addQueryParameter("userId", "1456");
        builder.addQueryParameter("level", "1");

        return builder;
    }


}
