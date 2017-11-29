package com.qinlong275.android.draganddraw;

import android.graphics.PointF;

/**
 * Created by 秦龙 on 2017/10/9.
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin){
        mOrigin=origin;
        mCurrent=origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }
}
