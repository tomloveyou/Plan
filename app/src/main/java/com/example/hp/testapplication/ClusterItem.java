/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.model.LatLng;

/**
 * ClusterItem represents a marker on the map.
 */
public interface ClusterItem {

    /**
     * The position of this marker. This must always return the same value.
     *
     * @return the id
     */
    String getId();

    /**
     * Gets position.
     *
     * @return the position
     */
    LatLng getPosition();

    /**
     * Gets bitmap descriptor.
     *
     * @return the bitmap descriptor
     */
    BitmapDescriptor getBitmapDescriptor();
}