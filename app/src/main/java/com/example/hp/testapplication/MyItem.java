package com.example.hp.testapplication;

import android.os.Bundle;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;


public class MyItem implements ClusterItem {
    /**
     * 经纬度
     */
    private final LatLng mPosition;
    /**
     * 含有数据的对象
     */
    private Bundle mBundle;
    /**
     * 对象id
     */
    private String id;


    public MyItem(LatLng latLng) {
        mPosition = latLng;
        mBundle = null;
    }

    public MyItem(String id, LatLng latLng, Bundle bundle) {
        mPosition = latLng;
        mBundle = bundle;
        this.id = id;

    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        int iconId = R.mipmap.ic_plan_normal;
        if (mBundle != null) {
            if ("1".equals(mBundle.getString("type"))) {
                iconId = R.mipmap.ic_plan_green;
            } else if ("2".equals(mBundle.getString("type"))) {
                iconId = R.mipmap.ic_plan_arm;
            }
        }

        return BitmapDescriptorFactory.fromResource(iconId);// R.drawable.icon_gcoding);
    }

    public Bundle getBundle() {
        return mBundle;
    }
}
