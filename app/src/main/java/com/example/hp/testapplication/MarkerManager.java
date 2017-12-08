/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Keeps track of collections of markers on the map. Delegates all
 * Marker-related events to each collection's individually managed listeners.
 * <p/>
 * All marker operations (adds and removes) should occur via its collection
 * class. That is, don't add a marker via a collection, then remove it via
 * Marker.remove()
 */
public class MarkerManager implements BaiduMap.OnMarkerClickListener, BaiduMap.OnMarkerDragListener {
    private final BaiduMap mMap;

    private Collection collection = new Collection();

    /**
     * Instantiates a new Marker manager.
     *
     * @param map the map
     */
    public MarkerManager(BaiduMap map) {
        this.mMap = map;
    }




    @Override
    public boolean onMarkerClick(Marker marker) {

        if (collection != null && collection.mMarkerClickListener != null) {
            // you can set the click action
            return collection.mMarkerClickListener.onMarkerClick(marker);
        } else {
            ; // click single maker out of cluster
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragStart(marker);
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {

        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDrag(marker);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragEnd(marker);
        }
    }

    /**
     * Removes a marker from its collection.
     *
     * @param marker the marker to remove.
     * @return true if the marker was removed.
     */
    public boolean remove(Marker marker) {

        return collection != null && collection.remove(marker);
    }

    /**
     * Remove marker.
     *
     * @param marker the marker
     */
    public void removeMarker(Marker marker) {

        collection.remove(marker);

        marker.remove();

    }

    /**
     * The type Collection.
     */
    public class Collection {
        private final Set<Marker> mMarkers = new HashSet<Marker>();
        private BaiduMap.OnMarkerClickListener mMarkerClickListener;
        private BaiduMap.OnMarkerDragListener mMarkerDragListener;

        /**
         * Instantiates a new Collection.
         */
        public Collection() {
        }

        /**
         * Add marker marker.
         *
         * @param opts the opts
         * @return the marker
         */
        public Marker addMarker(MarkerOptions opts) {
            Marker marker = (Marker) mMap.addOverlay(opts);
            mMarkers.add(marker);
            return marker;
        }

        /**
         * Remove boolean.
         *
         * @param marker the marker
         * @return the boolean
         */
        public boolean remove(Marker marker) {
            if (mMarkers.remove(marker)) {
                marker.remove();
                return true;
            }
            return false;
        }

        /**
         * Clear.
         */
        public void clear() {
            for (Marker marker : mMarkers) {
                marker.remove();
            }
            mMarkers.clear();
        }

        /**
         * Gets markers.
         *
         * @return the markers
         */
        public java.util.Collection<Marker> getMarkers() {
            return Collections.unmodifiableCollection(mMarkers);
        }

        /**
         * Sets on marker click listener.
         *
         * @param markerClickListener the marker click listener
         */
        public void setOnMarkerClickListener(BaiduMap.OnMarkerClickListener markerClickListener) {
            mMarkerClickListener = markerClickListener;
        }

        /**
         * Sets on marker drag listener.
         *
         * @param markerDragListener the marker drag listener
         */
        public void setOnMarkerDragListener(BaiduMap.OnMarkerDragListener markerDragListener) {
            mMarkerDragListener = markerDragListener;
        }
    }
}
