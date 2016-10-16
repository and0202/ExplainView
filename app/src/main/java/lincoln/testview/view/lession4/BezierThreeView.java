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
 * 贝赛尔曲线：
 * 三阶曲线：
 * 两个数据点和两个控制点来控制曲线状态。
 * Created by lincoln on 16/10/16.
 */
public class BezierThreeView extends View {

    private Paint mPaint;
    private int centerX, centerY;
    private PointF start, end;
    private PointF controlOne, controlTwo;
    private Context context;
    private  boolean mode =true;

    public BezierThreeView(Context context) {
        super(context);
        init(context);
    }

    public BezierThreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(8);
        mPaint.setStrokeWidth(5);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        controlOne = new PointF(0, 0);
        controlTwo = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;


        start.x = centerX + 200;
        start.y = centerY;

        end.x = centerX - 200;
        end.y = centerY;

        controlOne.x = centerX;
        controlOne.y = centerY - 100;

        controlTwo.x = centerX;
        controlTwo.y = centerY + 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指滑动，更新控制点坐标，并重新调用onDraw()
        if (mode){
            controlOne.x = event.getX();
            controlOne.y = event.getY();
        }else{
            controlTwo.x = event.getX();
            controlTwo.y = event.getY();
        }

        invalidate();
        return true;
    }

    //更改模式：使用上面哪个点控制
    public void setMode(boolean mode ){
        this.mode = mode;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制4个点
        mPaint.setColor(getResources().getColor(R.color.grey));
        mPaint.setStrokeWidth(20);
//        canvas.drawPoint(start.x,start.y,mPaint);
//        canvas.drawPoint(end.x,end.y,mPaint);
//        canvas.drawPoint(control.x,control.y,mPaint);

        canvas.drawCircle(start.x, start.y, 1, mPaint);
        canvas.drawCircle(end.x, end.y, 1, mPaint);
        canvas.drawCircle(controlOne.x, controlOne.y, 1, mPaint);
        canvas.drawCircle(controlTwo.x, controlTwo.y, 1, mPaint);


        //绘制4条辅助直线
        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x, start.y, controlOne.x, controlOne.y, mPaint);
        canvas.drawLine(end.x, end.y, controlOne.x, controlOne.y, mPaint);
        canvas.drawLine(start.x, start.y, controlTwo.x, controlTwo.y, mPaint);
        canvas.drawLine(end.x, end.y, controlTwo.x, controlTwo.y, mPaint);

        //绘制贝赛尔曲线
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStrokeWidth(10);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(controlOne.x, controlOne.y,controlTwo.x,controlTwo.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }
}
