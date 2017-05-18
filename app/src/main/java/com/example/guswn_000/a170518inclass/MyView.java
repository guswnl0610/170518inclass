package com.example.guswn_000.a170518inclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by guswn_000 on 2017-05-18.
 */

public class MyView extends View { //뷰를 상속받으면 위젯의 역할을 한다
    public MyView(Context context, @Nullable AttributeSet attrs) { //attributeset이 붙어있는 녀석을 만들어야 레이아웃에 쓸 수 있다
        super(context, attrs);

    }
    Rect rect;
    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas); //canvas가 그리는 요소
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
//        canvas.drawRect(10,10,100,100, null);//x1 y1 x2 y2 색상이나 두께 둙기 이런 도구의 기능넣는 페인트. 근데페인트없으니 null

        rect = new Rect(10,10,100,100);
        canvas.drawRect(rect,paint);
//        canvas.drawRect(10,10,100,100, paint);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5); // 테두리두께
        paint.setStyle(Paint.Style.STROKE);//테두리만

        //페인트는 쭉~~영향미침. 바꾸고싶으면 중간에 바꿔줘야..
        canvas.drawRect(width/2 - 45,height/2-45,width/2+45,height/2+45,paint);


        paint.setTextSize(70);
        paint.setStrokeWidth(1);
        canvas.drawText("빼애애앵ㅇ!!",300,300,paint);



        //비트맵 가져오긔
        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(img,300,350,paint);
        canvas.drawBitmap(img,400,150,null);//null넣어도 잘 되넹..?
        Bitmap smallimg = Bitmap.createScaledBitmap(img, img.getWidth()/2,img.getHeight()/2,false);
        canvas.drawBitmap(smallimg,400,350,null);
        Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth()*2,img.getHeight()*2,false);
        canvas.drawBitmap(bigimg,150,400,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { // 이런 네모들 눌렀을때 이벤트 나오려면?
        int x = (int)event.getX();
        int y = (int)event.getY();
//        if (x >= 10 && x <= 100 && y >= 10 && y <= 100)//까만네모영역 터치했을때
//        {
//            Toast.makeText(getContext(),"BLACK BUTTON", Toast.LENGTH_SHORT).show();
//        }
        if(rect.contains(x,y))
        {
            Toast.makeText(getContext(),"BLACK BUTTON", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(),"BACKGROUND", Toast.LENGTH_SHORT).show();
        }
        return true; //true로 해줘야 이벤트 하겠다라는뜻
    }
}
