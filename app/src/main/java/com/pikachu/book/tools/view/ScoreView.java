/**
 * 评分控件 - pikachu - 2020/12/03
 */
package com.pikachu.book.tools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class ScoreView extends View {
    private int maxPoints = 5;
    private float points = 2.6f;
    private int theColor = 0xffff0000;
    private int notColor = 0x50ff0000;
    private int textColor = 0xFF000000;
    private int gap = 20, gaps = 0;
    private Paint paint = new Paint();
    private String text = points+" 分";

    public ScoreView(Context context) {
        this(context, null);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeCap(Paint.Cap.ROUND);
        int i1 = getHeight() / 2;
        int i2 = floatToInt(points);
        for (int i = 0; i < maxPoints; i++) {
            if (i<i2){
                paint.setColor(theColor);
            }else {
                paint.setColor(notColor);
            }
            canvas.drawCircle(gaps += i1, i1, i1, paint);
            gaps += gap + i1 ;
        }
        paint.setTextSize(i1*2);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text,gaps += i1*3,i1*1.7f,paint);
        gaps = 0;
        super.onDraw(canvas);
    }


    public float getPoints() {
        return points;
    }

    public int getNotColor() {
        return notColor;
    }

    public int getTheColor() {
        return theColor;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setPoints(float points) {
        this.points = points;
        this.text = points+" 分";
        invalidate();
    }

    public void setNotColor(@ColorInt int notColor) {
        this.notColor = notColor;
        invalidate();
    }

    public void setTheColor(@ColorInt int theColor) {
        this.theColor = theColor;
        invalidate();
    }


    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
        invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }



    private int floatToInt(float f){
        int i = 0;
        if(f>0){
            i = (int)(f*10 + 5)/10;
        } else if(f<0){
            i =  (int)(f*10 - 5)/10;
        } else {
            i = 0;
        }
        return i;
    }

}
