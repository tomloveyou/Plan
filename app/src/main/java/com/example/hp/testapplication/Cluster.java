/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;

import com.baidu.mapapi.model.LatLng;

import java.util.Collection;

/**
 * A collection of ClusterItems that are nearby each other.
 *
 * @param <T> the type parameter
 */
public interface Cluster<T extends ClusterItem> {
    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId();

    /**
     * Gets position.
     *
     * @return the position
     */
    public LatLng getPosition();

    /**
     * Gets items.
     *
     * @return the items
     */
    Collection<T> getItems();

    /**
     * Gets size.
     *
     * @return the size
     */
    int getSize();
}