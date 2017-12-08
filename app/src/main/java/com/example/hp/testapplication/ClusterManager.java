/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.Marker;


import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Groups many items on a map based on zoom level.
 * <p/>
 * ClusterManager should be added to the map
 * <li>
 *
 * @param <T> the type parameter
 */
public class ClusterManager<T extends ClusterItem> implements
        BaiduMap.OnMapStatusChangeListener, BaiduMap.OnMarkerClickListener {
    private final MarkerManager mMarkerManager;

    private final ReadWriteLock mAlgorithmLock = new ReentrantReadWriteLock();
    private final ReadWriteLock mAlgorithmLock2 = new ReentrantReadWriteLock();
    private ClusterRenderer<T> mRenderer;
    private ClusterRenderer<T> mRenderer2;
    private BaiduMap mMap;
    private MapStatus mPreviousCameraPosition;
    private ClusterTask mClusterTask;

    private final ReadWriteLock mClusterTaskLock = new ReentrantReadWriteLock();

    private final ReadWriteLock mClusterTaskLock2 = new ReentrantReadWriteLock();
    private OnClusterItemClickListener<T> mOnClusterItemClickListener;
    private OnClusterInfoWindowClickListener<T> mOnClusterInfoWindowClickListener;
    private OnClusterItemInfoWindowClickListener<T> mOnClusterItemInfoWindowClickListener;
    private OnClusterClickListener<T> mOnClusterClickListener;


    /**
     * The Handler.
     */
    Handler handler;
    /**
     * The Result.
     */
    int result;

    /**
     * Instantiates a new Cluster manager.
     *
     * @param context the context
     * @param map     the map
     */
    public ClusterManager(Context context, BaiduMap map) {
        this(context, map, new MarkerManager(map));

    }


    /**
     * Instantiates a new Cluster manager.
     *
     * @param context       the context
     * @param map           the map
     * @param markerManager the marker manager
     */
    public ClusterManager(Context context, BaiduMap map, MarkerManager markerManager) {
        mMap = map;
        mMarkerManager = markerManager;

        mClusterTask = new ClusterTask();

        mRenderer.onAdd();
        mRenderer2.onAdd();
        handler = null;
        result = -1;
    }


    /**
     * Force a re-cluster. You may want to call this after adding new item(s).
     */
    public void cluster() {
        mClusterTaskLock2.writeLock().lock();
        mClusterTaskLock.writeLock().lock();
        try {
            // Attempt to cancel the in-flight request.
            mClusterTask.cancel(true);
            mClusterTask = new ClusterTask();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                mClusterTask.execute(mMap.getMapStatus().zoom);

            } else {
                mClusterTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mMap.getMapStatus().zoom);

            }
        } finally {
            mClusterTaskLock.writeLock().unlock();
            mClusterTaskLock2.writeLock().unlock();
        }
    }


    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }


    @Override
    public void onMapStatusChange(MapStatus mapStatus) {
        if (mRenderer instanceof BaiduMap.OnMapStatusChangeListener) {
            ((BaiduMap.OnMapStatusChangeListener) mRenderer).onMapStatusChange(mapStatus);
        }
        if (mRenderer2 instanceof BaiduMap.OnMapStatusChangeListener) {
            ((BaiduMap.OnMapStatusChangeListener) mRenderer2).onMapStatusChange(mapStatus);
        }
        // Don't re-compute clusters if the map has just been panned/tilted/rotated.
        MapStatus position = mMap.getMapStatus();
        if (mPreviousCameraPosition != null && mPreviousCameraPosition.zoom == position.zoom) {
            return;
        }
        mPreviousCameraPosition = mMap.getMapStatus();
        cluster();
    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {

        Log.i("ClusterManger", "onMapStatusChangeFinish");
        Message message = handler.obtainMessage(result);
        message.obj = mapStatus;
        handler.sendMessage(message);

    }

    /**
     * Sets handler.
     *
     * @param handler the handler
     * @param result  the result
     */
    public void setHandler(Handler handler, int result) {
        this.handler = handler;
        this.result = result;
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
      //  return getMarkerManager().onMarkerClick(marker);
        return false;
    }

    public MarkerManager getMarkerManager() {
        return mMarkerManager;
    }

    public MarkerManager.Collection getClusterMarkerCollection() {
        return null;
    }

    public MarkerManager.Collection getMarkerCollection() {
        return null;
    }


    /**
     * Runs the clustering algorithm in a background thread, then re-paints when results come back.
     */
    private class ClusterTask extends AsyncTask<Float, Void, Set<? extends Cluster<T>>> {
        @Override
        protected Set<? extends Cluster<T>> doInBackground(Float... zoom) {
            mAlgorithmLock.readLock().lock();
            try {
               return null;
            } finally {
                mAlgorithmLock.readLock().unlock();
            }
        }

        @Override
        protected void onPostExecute(Set<? extends Cluster<T>> clusters) {
            mRenderer.onClustersChanged(clusters);
        }
    }


    /**
     * Sets a callback that's invoked when a Cluster is tapped. Note: For this listener to function,
     * the ClusterManager must be added as a info window click listener to the map.
     *
     * @param listener the listener
     */
    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener<T> listener) {
        mOnClusterInfoWindowClickListener = listener;
        mRenderer.setOnClusterInfoWindowClickListener(listener);
    }

    /**
     * Sets a callback that's invoked when an individual ClusterItem is tapped. Note: For this
     * listener to function, the ClusterManager must be added as a click listener to the map.
     *
     * @param listener the listener
     */
    public void setOnClusterItemClickListener(OnClusterItemClickListener<T> listener) {
        mOnClusterItemClickListener = listener;
        mRenderer.setOnClusterItemClickListener(listener);
        mRenderer2.setOnClusterItemClickListener(listener);
    }

    /**
     * Sets a callback that's invoked when an individual ClusterItem's Info Window is tapped. Note: For this
     * listener to function, the ClusterManager must be added as a info window click listener to the map.
     *
     * @param listener the listener
     */
    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener<T> listener) {
        mOnClusterItemInfoWindowClickListener = listener;
        mRenderer.setOnClusterItemInfoWindowClickListener(listener);
    }

    /**
     * Called when a Cluster is clicked.
     *
     * @param <T> the type parameter
     */
    public interface OnClusterClickListener<T extends ClusterItem> {
        /**
         * On cluster click boolean.
         *
         * @param cluster the cluster
         * @return the boolean
         */
        public boolean onClusterClick(Cluster<T> cluster);
    }

    /**
     * Called when a Cluster's Info Window is clicked.
     *
     * @param <T> the type parameter
     */
    public interface OnClusterInfoWindowClickListener<T extends ClusterItem> {
        /**
         * On cluster info window click.
         *
         * @param cluster the cluster
         */
        public void onClusterInfoWindowClick(Cluster<T> cluster);
    }

    /**
     * Called when an individual ClusterItem is clicked.
     *
     * @param <T> the type parameter
     */
    public interface OnClusterItemClickListener<T extends ClusterItem> {
        /**
         * On cluster item click boolean.
         *
         * @param item the item
         * @return the boolean
         */
        public boolean onClusterItemClick(T item);
    }

    /**
     * Called when an individual ClusterItem's Info Window is clicked.
     *
     * @param <T> the type parameter
     */
    public interface OnClusterItemInfoWindowClickListener<T extends ClusterItem> {
        /**
         * On cluster item info window click.
         *
         * @param item the item
         */
        public void onClusterItemInfoWindowClick(T item);
    }
}
