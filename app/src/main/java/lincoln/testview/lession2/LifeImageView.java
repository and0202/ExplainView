package lincoln.testview.lession2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lincoln on 16/10/4.
 */
public class LifeImageView extends ImageView{
    public LifeImageView(Context context) {
        super(context);
        Lession2LogUtil.init();
    }

    public LifeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Lession2LogUtil.init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Lession2LogUtil.onFinishInflate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Lession2LogUtil.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Lession2LogUtil.onMeasure();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Lession2LogUtil.onLayout();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Lession2LogUtil.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Lession2LogUtil.onDraw();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Lession2LogUtil.onDetachedFromWindow();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Lession2LogUtil.onSizeChanged();
    }
}
