
package com.example.hp.testapplication.projection;

/**
 * Represents an area in the cartesian plane.
 */
public class Bounds {
    /**
     * The Min x.
     */
    public final double minX;
    /**
     * The Min y.
     */
    public final double minY;

    /**
     * The Max x.
     */
    public final double maxX;
    /**
     * The Max y.
     */
    public final double maxY;

    /**
     * The Mid x.
     */
    public final double midX;
    /**
     * The Mid y.
     */
    public final double midY;

    /**
     * Instantiates a new Bounds.
     *
     * @param minX the min x
     * @param maxX the max x
     * @param minY the min y
     * @param maxY the max y
     */
    public Bounds(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;

		midX = (minX + maxX) / 2;
		midY = (minY + maxY) / 2;
	}

    /**
     * Contains boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean contains(double x, double y) {
		return minX <= x && x <= maxX && minY <= y && y <= maxY;
	}

    /**
     * Contains boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean contains(Point point) {
		return contains(point.x, point.y);
	}

    /**
     * Contains boolean.
     *
     * @param bounds the bounds
     * @return the boolean
     */
    public boolean contains(Bounds bounds) {
		return bounds.minX >= minX && bounds.maxX <= maxX && bounds.minY >= minY && bounds.maxY <= maxY;
	}

    /**
     * Intersects boolean.
     *
     * @param minX the min x
     * @param maxX the max x
     * @param minY the min y
     * @param maxY the max y
     * @return the boolean
     */
    public boolean intersects(double minX, double maxX, double minY, double maxY) {
		return minX < this.maxX && this.minX < maxX && minY < this.maxY && this.minY < maxY;
	}

    /**
     * Intersects boolean.
     *
     * @param bounds the bounds
     * @return the boolean
     */
    public boolean intersects(Bounds bounds) {
		return intersects(bounds.minX, bounds.maxX, bounds.minY, bounds.maxY);
	}

}