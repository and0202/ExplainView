package lincoln.testview.view.lession4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import lincoln.testview.R;

/**
 * 贝赛尔曲线：两阶曲线
 * Created by lincoln on 16/10/16.
 */
public class BezierTwoView extends View{
    private Paint mPaint;
    private int centerX,centerY;
    private PointF start,end,control;
    private Context context;
    public BezierTwoView(Context context) {
        super(context);
        init(context);
    }

    public BezierTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context){
        this.context = context;

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(8);
        mPaint.setStrokeWidth(5);

        start = new PointF(0,0);
        end = new PointF(0,0);
        control = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;


        start.x = centerX+100;
        start.y = centerY;

        end.x = centerX-100;
        end.y = centerY;

        control.x = centerX;
        control.y = centerY-100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指滑动，更新控制点坐标，并重新调用onDraw()
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制3个点
        mPaint.setColor(getResources().getColor(R.color.grey));
        mPaint.setStrokeWidth(20);
//        canvas.drawPoint(start.x,start.y,mPaint);
//        canvas.drawPoint(end.x,end.y,mPaint);
//        canvas.drawPoint(control.x,control.y,mPaint);

        canvas.drawCircle(start.x,start.y,1,mPaint);
        canvas.drawCircle(end.x,end.y,1,mPaint);
        canvas.drawCircle(control.x,control.y,1,mPaint);


        //绘制辅助直线(x-control线、y-control线)
        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine(end.x,end.y,control.x,control.y,mPaint);

        //绘制贝赛尔曲线
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStrokeWidth(10);
        Path path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,mPaint);
    }
}
