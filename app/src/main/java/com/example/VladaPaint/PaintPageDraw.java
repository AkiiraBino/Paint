package com.example.VladaPaint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.view.MotionEvent;
import android.util.TypedValue;

public class PaintPageDraw extends View{

    private Path path;
    private Paint paintDraw, paintCanvas;
    private int paintColorDefault = 0xFF000000, lastColor;
    private Canvas canvasDraw;
    private Bitmap canvasBitmap;
    private boolean erase = false;
    private float brushSize;


    public PaintPageDraw(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        setupPaintPage();
    }

    private void setupPaintPage()
    {
        path = new Path();
        paintDraw = new Paint();
        paintDraw.setColor(paintColorDefault);
        paintDraw.setAntiAlias(true);
        paintDraw.setStyle(Paint.Style.STROKE);
        paintDraw.setStrokeJoin(Paint.Join.ROUND);
        paintDraw.setStrokeCap(Paint.Cap.ROUND);
        paintCanvas = new Paint(Paint.DITHER_FLAG);
        brushSize = getResources().getInteger(R.integer.medium_size);
        paintDraw.setStrokeWidth(brushSize);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasDraw = new Canvas(canvasBitmap);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawBitmap(canvasBitmap, 0, 0, paintCanvas);
        canvas.drawPath(path, paintDraw);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                canvasDraw.drawPath(path, paintDraw);
                path.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor)
    {
        invalidate();
        paintColorDefault = Color.parseColor(newColor);
        paintDraw.setColor(paintColorDefault);
    }

    public void setBrushSize(float newSize)
    {
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        paintDraw.setStrokeWidth(brushSize);
    }

    public void setErase(boolean isErase)
    {
        erase = isErase;
        if(erase)
        {
            paintDraw.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        else
        {
            paintDraw.setXfermode(null);
        }
    }



    public void newList(View view)
    {
        canvasDraw.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

}