/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication.projection;

/**
 * The type Point.
 */
public class Point {
    /**
     * The X.
     */
    public final double x;
    /**
     * The Y.
     */
    public final double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point{" + "x=" + x + ", y=" + y + '}';
	}
}
