package lincoln.testview.view.lession4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import lincoln.testview.R;

/**
 * 贝赛尔曲线：实现圆变成心形的效果:
 *  1 绘制坐标系
 *  2 绘制数据点和辅助线
 *  3 绘制贝赛尔曲线，动态变更数据
 *  4 把1，2步注释掉，即可。
 * Created by lincoln on 16/10/16.
 */
public class BezierHeartView extends View {
    private Context context;

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置

    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);
    private float mCircleRadius = 200;                  // 圆的半径
    private float mDifference = mCircleRadius * C;        // 圆形的控制点与数据点的差值

    private float[] mData = new float[8];               // 顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration / mCount;            // 每一份的时长

    public BezierHeartView(Context context) {
        super(context);
        init(context);

    }

    public BezierHeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public void init(Context context) {
        this.context = context;
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.STROKE);


        //初始化4个数据点，分别表示x，y轴位置：
        mData[0] = 0;            //第1个数据点x轴坐标
        mData[1] = mCircleRadius;//第1个数据点y轴坐标

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        //初始化控制点x,y轴坐标
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;
        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];
        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;
        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        drawCoordinate(canvas);

        canvas.translate(mCenterX, mCenterY);
        canvas.scale(-1, -1);

//        drawLine(canvas);

        drawbezier(canvas);

    }

    //绘制坐标系
    private void drawCoordinate(Canvas canvas) {
        canvas.save();
        mPaint.setStrokeWidth(8);
        mPaint.setColor(getResources().getColor(R.color.gray));
        //绘制x轴
        canvas.drawLine(0, mCenterY, mCenterX * 2, mCenterY, mPaint);
        canvas.drawLine(mCenterX, 0, mCenterX, mCenterY * 2, mPaint);

        canvas.restore();

    }

    //绘制数据点和辅助线
    public void drawLine(Canvas canvas) {
        //绘制数据点
        mPaint.setColor(getResources().getColor(R.color.blue));
        mPaint.setStrokeWidth(8);
        for (int index = 0; index < mData.length; index += 2) {
            canvas.drawCircle(mData[index], mData[index + 1], 3, mPaint);
        }
        //绘制控制点
        mPaint.setColor(getResources().getColor(R.color.green));
        for (int index = 0; index < mCtrl.length; index += 2) {
            canvas.drawCircle(mCtrl[index], mCtrl[index + 1], 3, mPaint);
        }
        //绘制辅助线
        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }
        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);

    }

    //绘制贝赛尔曲线
    public void drawbezier(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStrokeWidth(15);
        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);

        canvas.drawPath(path, mPaint);


        mCurrent += mPiece;
        if (mCurrent < mDuration){

            mData[1] -= 120/mCount;
            mCtrl[7] += 80/mCount;
            mCtrl[9] += 80/mCount;

            mCtrl[4] -= 20/mCount;
            mCtrl[10] += 20/mCount;

            Log.d("lincoln","current:"+mCurrent+" mDuration:"+mDuration+" mData[1]"+mData[1]);

            postInvalidateDelayed((long) mPiece);
        }

    }

}
