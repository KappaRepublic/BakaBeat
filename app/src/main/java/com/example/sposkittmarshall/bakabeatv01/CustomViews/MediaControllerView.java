package com.example.sposkittmarshall.bakabeatv01.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sposk_000 on 2015/11/24.
 */
public class MediaControllerView extends View
{
    Paint bgPaint;

    public MediaControllerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Set up paint objects
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(0xff000000);
        bgPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
    }
}
