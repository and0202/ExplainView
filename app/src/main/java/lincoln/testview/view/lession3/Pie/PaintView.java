package lincoln.testview.view.lession3.Pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lincoln.testview.R;

/**
 * 饼状图:
 * 步骤如下：
 * 1 获取宽高，计算出半径R
 * 2 移动到圆心
 * 3 根据角度绘制 『弧度』
 * Created by lincoln on 16/10/6.
 */
public class PaintView extends View {
    private Paint mPaint;
    private int width ,height;
    private int circleR;
    private List<PieData> list = new ArrayList<>();
    private float currentAngle = 0;

    public PaintView(Context context) {
        super(context);
        init(context);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.green));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //1 获取宽高，计算出半径R
        width = w;
        height = h;
        circleR = Math.min(width,height)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //2 移动到圆心开始绘制
        canvas.translate(width/2,height/2);
        RectF rectF = new RectF(-circleR,-circleR,circleR,circleR);

        for (int index=0;index < list.size();index++){
            PieData pieData =list.get(index);
            mPaint.setColor(getResources().getColor(pieData.color));
            //3 根据角度确定绘制弧度
            canvas.drawArc(rectF,currentAngle,pieData.angle,true,mPaint);
            currentAngle = currentAngle+pieData.angle;
        }

    }

    //填充数据，更新View
    public void setData(List<PieData> list){
        this.list = list;
        invalidate();
    }
}
