package com.example.hp.testapplication.api;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.hp.testapplication.Fly;
import com.example.hp.testapplication.ResultDataBean;
import com.standards.library.model.ListData;
import com.standards.library.model.Response;

import java.util.List;

import rx.Observable;

/**
 * @author yl
 * @version 1.0
 * @date 2017/12/6 11:32
 */
public class DataManager extends ResponseHandle {

public Observable<Fly<ResultDataBean>> getti( LatLngBounds latLngBounds){
    return Dao.getApiService().getTicketList(latLngBounds.northeast.latitude+"",latLngBounds.southwest.latitude+"",latLngBounds.northeast.longitude+"",latLngBounds.southwest.longitude+"")
            .flatMap(newEntityresult())
            .compose(applySchedulersWithToken());

}
}
