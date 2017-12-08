/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;



import java.util.Set;

/**
 * Renders clusters.
 *
 * @param <T> the type parameter
 */
public interface ClusterRenderer<T extends ClusterItem> {

    /**
     * Called when the view needs to be updated because new clusters need to be
     * displayed.
     *
     * @param clusters the clusters to be displayed.
     */
    void onClustersChanged(Set<? extends Cluster<T>> clusters);

    /**
     * Sets on cluster click listener.
     *
     * @param listener the listener
     */
    void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> listener);

    /**
     * Sets on cluster info window click listener.
     *
     * @param listener the listener
     */
    void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> listener);

    /**
     * Sets on cluster item click listener.
     *
     * @param listener the listener
     */
    void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> listener);

    /**
     * Sets on cluster item info window click listener.
     *
     * @param listener the listener
     */
    void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> listener);

    /**
     * Called when the view is added.
     */
    void onAdd();

    /**
     * Called when the view is removed.
     */
    void onRemove();
}