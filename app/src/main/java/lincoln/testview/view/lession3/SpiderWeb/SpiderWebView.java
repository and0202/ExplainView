package lincoln.testview.view.lession3.SpiderWeb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import lincoln.testview.R;

/**
 * 雷达图：
 * Path ：
 * move(x,y)
 * lineTo(x,y)
 * 步骤：
 * 1 绘制一个多边形
 * 2 绘制多个多边形
 * 3 多边形对角线相连
 * 4 绘制分值区
 * Created by lincoln on 16/10/6.
 */
public class SpiderWebView extends View {
    //多边形变数
    private int BORDER_COUNT = 8;

    //角度：在java中Math类的三角函数接收的参数并不角度，而是弧度，所以需要用2 * Math.PI表示360°
    private float mAngel = (float) (2 * Math.PI / BORDER_COUNT);
    private int width, height;
    //三种Paint：多边形Paint、直线Paint、分值区Paint
    private Paint mPaintShape, mPaintLine, mPaintRegion;
    //三种Path：图形Path、直线Path,分值区域Path
    private Path pathShape, pathLine, pathRegion;
    //多边形起始半径
    private int radiu = 0;
    //中心点坐标
    private int centerX, centerY;

    public SpiderWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpiderWebView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        //多边形绘制初始化
        mPaintShape = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintShape.setColor(getResources().getColor(R.color.black));
        mPaintShape.setStyle(Paint.Style.STROKE);
        mPaintShape.setStrokeWidth(3);

        pathShape = new Path();

        //绘制直线初始化
        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(getResources().getColor(R.color.red));
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setStrokeWidth(3);

        pathLine = new Path();

        //绘制域值初始化
        mPaintRegion = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRegion.setColor(Color.parseColor("#7f33b5e5"));//透明度
        mPaintRegion.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintRegion.setStrokeWidth(3);

        pathRegion = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("lincoln", "onSizeChanged");
        width = w;
        height = h;
        centerX = width / 2;
        centerY = height / 2;
        radiu = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.azure));

        //TODO OnDraw()被调用2次，问题待查找


        drawMutilShape(canvas);

        drawLine(canvas);

        drawRegion(canvas);
    }

    //1 绘制单个多边形
    private void drawSingleShape(Canvas canvas) {
        pathShape.moveTo(centerX + radiu, centerX);
        for (int index = 0; index < BORDER_COUNT; index++) {
            float x = (float) (centerX + Math.cos(index * mAngel) * radiu);
            float y = (float) (centerY + Math.sin(index * mAngel) * radiu);
            Log.d("lincoln", "drawSingleShape:" + " x:" + x + " y:" + y);
            pathShape.lineTo(x, y);
        }
        pathShape.close();
        canvas.drawPath(pathShape, mPaintShape);
//        pathShape.reset();
    }

    //2 绘制多个多边形
    public void drawMutilShape(Canvas canvas){
        //每一份的值
        int each = radiu / 5;
        //2 绘制多个多边形
        for (int index = 0; index < 5; index++) {
            Log.d("lincoln", "radiu:" + radiu + " each:" + each);
            radiu = radiu - each;
            drawSingleShape(canvas);
        }

    }


    //3 多边形对角线相连
    private void drawLine(Canvas canvas) {

        radiu = Math.min(width, height) / 2;
        int each = radiu / 5;
        radiu = radiu - each;

        for (int index = 0; index < BORDER_COUNT; index++) {
            pathLine.moveTo(centerX, centerX);
            float x = (float) (centerX + Math.cos(index * mAngel) * radiu);
            float y = (float) (centerY + Math.sin(index * mAngel) * radiu);
            Log.d("lincoln", "drawLine:" + " x:" + x + " y:" + y);
            pathLine.lineTo(x, y);
            canvas.drawPath(pathLine, mPaintLine);
        }
        //TODO 解决2次绘制的bug
        radiu = Math.min(width, height) / 2;
    }

    //4 绘制分值区域
    float[] percents = new float[]{
            0.2F,
            0.6F,
            0.8F,
            0.5F,
            0.6F,
            0.9F,
            0.2F,
            0.8F,
            0.3F,
            0.7F,
    };
    public void drawRegion(Canvas canvas) {
        radiu = Math.min(width, height) / 2;
        int each = radiu / 5;
        radiu = radiu - each;
        for (int index = 0; index < BORDER_COUNT; index++) {
            float x = (float) ( Math.cos(index * mAngel) * radiu * percents[index]);
            float y = (float) ( Math.sin(index * mAngel) * radiu * percents[index]);
            Log.d("lincoln", "drawRegion:" + " x:" + x + " y:" + y);
            if (index == 0) {
                pathRegion.moveTo( centerX+x, centerY);
            } else {
                pathRegion.lineTo(centerX+x, centerY+y);
            }
        }
        pathRegion.close();
        canvas.drawPath(pathRegion, mPaintRegion);
        radiu = Math.min(width, height) / 2;

    }
}
