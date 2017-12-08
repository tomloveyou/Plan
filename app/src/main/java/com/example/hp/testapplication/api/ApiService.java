package com.example.hp.testapplication.api;


import com.example.hp.testapplication.Fly;
import com.example.hp.testapplication.ResultDataBean;
import com.standards.library.model.ListData;
import com.standards.library.model.Response;


import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @author caiyk@cncn.com
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public interface ApiService {

    /**
     * 查询屏幕内飞机
     * @param Latitudeleft 屏幕左上角经度
     * @param Latituderight 屏幕右下角经度
     * @param Longitudeleft 屏幕左上角纬度
     * @param Longituderight 屏幕右下角纬度
     * @return 飞机数据集合
     */
    @FormUrlEncoded
    @POST("/fly-mob/adj.do?s=hhService&m=queryUavPoints&platform=android")
    Observable<Response<Fly<ResultDataBean>>> getTicketList(@Field("areaLatitudeleft") String Latitudeleft,@Field("areaLatituderight") String Latituderight,@Field("areaLongitudeleft") String Longitudeleft,@Field("areaLongituderight") String Longituderight);


}
