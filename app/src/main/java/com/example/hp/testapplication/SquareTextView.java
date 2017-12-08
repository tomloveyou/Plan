/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.hp.testapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * The type Square text view.
 */
public class SquareTextView extends TextView {
	private int mOffsetTop = 0;
	private int mOffsetLeft = 0;

    /**
     * Instantiates a new Square text view.
     *
     * @param context the context
     */
    public SquareTextView(Context context) {
		super(context);
	}

    /**
     * Instantiates a new Square text view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public SquareTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

    /**
     * Instantiates a new Square text view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public SquareTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int dimension = Math.max(width, height);
		if (width > height) {
			mOffsetTop = width - height;
			mOffsetLeft = 0;
		} else {
			mOffsetTop = 0;
			mOffsetLeft = height - width;
		}
		setMeasuredDimension(dimension, dimension);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.translate(mOffsetLeft / 2, mOffsetTop / 2);
		super.draw(canvas);
	}
}
