package lincoln.testview.lession3.AmuseShape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import lincoln.testview.R;

/**
 * 有趣的圆形
 * Created by lincoln on 16/10/6.
 */
public class ShapeCircleView extends View{
    private int width,height;
    private Paint mPaint;
    //大圆半径
    private int MAX_RADIU = 150;
    //小圆半径
    private int MIN_RADIU = 140;


    public ShapeCircleView(Context context) {
        super(context);
        init(context);

    }

    public ShapeCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public void init(Context context){
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,height/2);

        // 绘制两个圆形
        canvas.drawCircle(0,0,MAX_RADIU,mPaint);
        canvas.drawCircle(0,0,MIN_RADIU,mPaint);
        for (int index =0;index < 20; index++){
            canvas.drawLine(0,MIN_RADIU,0,MAX_RADIU,mPaint);
            canvas.rotate(20);
        }

    }
}
