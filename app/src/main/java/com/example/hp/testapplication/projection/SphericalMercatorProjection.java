/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.example.hp.testapplication.projection;

import com.baidu.mapapi.model.LatLng;

/**
 * The type Spherical mercator projection.
 */
public class SphericalMercatorProjection {
    /**
     * The M world width.
     */
    final double mWorldWidth;

    /**
     * Instantiates a new Spherical mercator projection.
     *
     * @param worldWidth the world width
     */
    public SphericalMercatorProjection(final double worldWidth) {
		mWorldWidth = worldWidth;
	}

    /**
     * To point point.
     *
     * @param latLng the lat lng
     * @return the point
     */
    @SuppressWarnings("deprecation")
	public Point toPoint(final LatLng latLng) {
		final double x = latLng.longitude / 360 + .5;
		final double siny = Math.sin(Math.toRadians(latLng.latitude));
		final double y = 0.5 * Math.log((1 + siny) / (1 - siny)) / -(2 * Math.PI) + .5;

		return new Point(x * mWorldWidth, y * mWorldWidth);
	}

    /**
     * To lat lng lat lng.
     *
     * @param point the point
     * @return the lat lng
     */
    public LatLng toLatLng(Point point) {
		final double x = point.x / mWorldWidth - 0.5;
		final double lng = x * 360;

		double y = .5 - (point.y / mWorldWidth);
		final double lat = 90 - Math.toDegrees(Math.atan(Math.exp(-y * 2 * Math.PI)) * 2);

		return new LatLng(lat, lng);
	}
}
