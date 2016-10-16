package lincoln.testview.view.lession3.AmuseShape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import lincoln.testview.R;

/**
 * 有趣的正方形
 * Created by lincoln on 16/10/6.
 */
public class ShapeRectView extends View {
    private int width,height;
    private Paint mPaint;
    private RectF rectF;
    private int NUMBER = 400;

    public ShapeRectView(Context context) {
        super(context);
        init(context);
    }

    public ShapeRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        rectF = new RectF(-NUMBER,-NUMBER,NUMBER,NUMBER);
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

        for (int index =0;index < 10; index++){
            canvas.scale(0.8f,0.8f);
            canvas.drawRect(rectF,mPaint);
        }
    }
}
