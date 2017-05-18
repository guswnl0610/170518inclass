package com.example.guswn_000.a170518inclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by guswn_000 on 2017-05-18.
 */

public class MyCanvas2 extends View {
    public MyCanvas2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.setLayerType(LAYER_TYPE_SOFTWARE,null); // 블러링하려면 잠시 이렇게 해줘야한다
    }

    String operationtype = "";

    float startX,startY,stopX,stopY = -1;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(startX,startY,stopX,stopY,paint);

        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bigimg = Bitmap.createScaledBitmap(img,img.getWidth()*2,img.getHeight()*2,false);
        int cenX = (canvas.getWidth() - bigimg.getWidth())/2;
        int cenY = (canvas.getHeight() - bigimg.getHeight())/2;
        if(operationtype.equals("rotate"))
        {
            canvas.rotate(45,canvas.getWidth()/2,canvas.getHeight()/2);
        }
        else if (operationtype.equals("Blur"))
        {
            BlurMaskFilter blur = new BlurMaskFilter(200, BlurMaskFilter.Blur.INNER);
            paint.setMaskFilter(blur);
            canvas.drawBitmap(bigimg,cenX,cenY,paint);

        }
        //마스크필터
//        BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.INNER);
//        paint.setMaskFilter(blur);

        //색상필터
        float[] array = {
                2,0,0,0,-10f,
                0,2,0,0,-10f,
                0,0,2,0,-10f,
                0,0,0,2,0
        };
        ColorMatrix matrix = new ColorMatrix(array);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        paint.setColorFilter(filter);


        canvas.drawBitmap(bigimg,cenX,cenY,paint);
//        canvas.drawBitmap(bigimg,cenX,cenY,null);
    }

    public void setOperationtype(String operationtype)
    {
        this.operationtype = operationtype;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == event.ACTION_DOWN)
        {
            startX = event.getX();
            startY = event.getY();

        }
        else if (event.getAction() == event.ACTION_UP)
        {
            stopX = event.getX();
            stopY = event.getY();
            invalidate();
        }
        return true;
    }
}
