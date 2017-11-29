package com.qinlong275.android.draganddraw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 秦龙 on 2017/10/9.
 */

public class BoxDrawingView extends View {

    private static final String TAG="BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxen=new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    //Used when create the view in code
    public BoxDrawingView(Context context){
        this(context,null);
    }

    //Used when inflating the view from XML
    public BoxDrawingView(Context context, AttributeSet attrs){
        super(context,attrs);

        mBoxPaint=new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint=new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Fill the background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box:mBoxen){
            float left= Math.min(box.getOrigin().x,box.getCurrent().x);
            float right= Math.max(box.getOrigin().x,box.getCurrent().x);
            float top= Math.min(box.getOrigin().y,box.getCurrent().y);
            float bottom=Math.max(box.getOrigin().y,box.getCurrent().y);

            canvas.drawRect(left,top,right,bottom,mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current=new PointF(event.getX(),event.getY());
        String action="";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action="ACTION_DOWN";
                //Reset drawing state
                mCurrentBox=new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action="ACTION_MOVE";
                if (mCurrentBox!=null){
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action="ACTION_UP";
                mCurrentBox=null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action="ACTION_CANCEL";
                mCurrentBox=null;
                break;
        }
        Log.i(TAG,action+" at x="+current.x+", y="+current.y);
        return true;
    }
}
