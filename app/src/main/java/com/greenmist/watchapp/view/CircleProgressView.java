package com.greenmist.watchapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.greenmist.watchapp.R;
import com.greenmist.watchapp.RingDrawable;

/**
 * User: geoffpowell
 * Date: 6/17/15
 */

public class CircleProgressView extends RelativeLayout {

    ProgressBar background;
    ProgressBar progressBar;
    ProgressBar progressBar2;

    RingDrawable backDrawable;
    RingDrawable ringDrawable;

    private int thickness;

    public CircleProgressView(Context context) {
        this(context, null, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);



        int height = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "layout_height", ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "layout_width", ViewGroup.LayoutParams.WRAP_CONTENT);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircledImageView);

        thickness = a.getInt(R.styleable.CircleProgressView_thickness, 3);

        ringDrawable = new RingDrawable();
        backDrawable = new RingDrawable();

        try {
            ringDrawable.setThickness(thickness);
            backDrawable.setThickness(thickness);
        } catch (IllegalAccessException e){} catch (NoSuchFieldException e){}

       // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((getResources().getDimensionPixelSize(R.dimen.radius_progress)+getResources().getDimensionPixelSize(R.dimen.ring_thickness))*2,
         //       (getResources().getDimensionPixelSize(R.dimen.radius_progress)+getResources().getDimensionPixelSize(R.dimen.ring_thickness))*2);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        background = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        background.setMax(100);
        background.setProgress(100);
        background.setIndeterminate(false);
        background.setLayoutParams(params);


        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        progressBar.setRotation(90);
        progressBar.setIndeterminate(false);
        progressBar.setLayoutParams(params);

        progressBar2 = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar2.setMax(100);
        progressBar2.setProgress(0);
        progressBar2.setScaleX(-1);
        progressBar2.setRotation(-90);
        progressBar2.setIndeterminate(false);
        progressBar2.setLayoutParams(params);


        backDrawable.setColor(getResources().getColor(R.color.semitransparent_grey));
        ringDrawable.setColor(getResources().getColor(R.color.colorAccent));

        background.setProgressDrawable(backDrawable);
        progressBar.setProgressDrawable(ringDrawable);
        progressBar2.setProgressDrawable(ringDrawable);

        addView(background);
        addView(progressBar);
        addView(progressBar2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
                try {
                ringDrawable.setInnerRadius(getWidth()/2 - thickness);
                backDrawable.setInnerRadius(getWidth() / 2 - thickness);
                } catch (IllegalAccessException e){} catch (NoSuchFieldException e){}
        }

        super.onLayout(changed, l, t, r, b);
    }


    public void setProgress(int progress) {
        progressBar.setProgress(progress/2);
        progressBar2.setProgress(progress/2);
    }
}
