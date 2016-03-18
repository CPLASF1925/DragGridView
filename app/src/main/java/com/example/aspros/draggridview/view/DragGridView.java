package com.example.aspros.draggridview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

/**
 * Created by aspros on 16/3/18.
 */
public class DragGridView extends GridView
{
    private static final int INVALID_ID = -1;
    private static final int MOVE_DURATION = 300;
    private static final int SCROLL_SPEED = 60;
    private static final int EXTEND_LENGTH=20;

    private int lastX = -1;
    private int lastY = -1;

    /**
     * 拖动时的图像 和 它的位置
     */
    private BitmapDrawable hoverCell;
    private Rect originRect;
    private Rect currentRect;

    /**
     * 要拖动的view
     */
    private View selectView;
    private int originPosition;
    private int currentPosition;

    private boolean isEdit;
    private boolean isDrag;
    private boolean isSwap;


    public DragGridView(Context context)
    {
        this(context, null);
    }

    public DragGridView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        int x= (int) ev.getX();
        int y= (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX=x;
                lastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDrag)
                {
                    int offsetX=x-lastX;
                    int offsetY=y-lastY;

                    currentRect.offset(offsetX,offsetY);
                    hoverCell.setBounds(currentRect);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
        }

        return super.onTouchEvent(ev);
    }

    public void resumeView()
    {
        if(selectView!=null)
        {

        }
    }
    public void startDrag()
    {

    }

    private BitmapDrawable getHoverCell(View view)
    {
        int left=view.getLeft();
        int top=view.getTop();
        int w= view.getWidth();
        int h=view.getHeight();

        Bitmap bitmap=getBitmapFromView(view);
        BitmapDrawable drawable=new BitmapDrawable(getResources(),bitmap);
        originRect=new Rect(left-EXTEND_LENGTH,top-EXTEND_LENGTH,left+w+EXTEND_LENGTH,top+h+EXTEND_LENGTH);
        currentRect=new Rect(originRect);

        drawable.setBounds(currentRect);
        return drawable;
    }

    private Bitmap getBitmapFromView(View v)
    {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        if (hoverCell != null)
        {
            hoverCell.draw(canvas);
        }
    }
}
