package lincoln.testview.move;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * 移动View的几种方法
 *
 * Created by lincoln on 16/10/1.
 */
public class CustomMoveView extends View {

    private int lastX;
    private int lastY;

    private Scroller mScroller;

    public CustomMoveView(Context context) {
        super(context);
        inite(context);
    }

    public CustomMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inite(context);
    }

    public CustomMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inite(context);
    }

    public void inite(Context context){
        mScroller = new Scroller(context);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY =y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x -lastX;
                int offsetY = y - lastY;
                //第一种方法：layout();
//                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                //第二种方法：offsetLeftAndRight()与offsetTopAndBottom()
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //第三种：使用LayoutParams参数
//                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //第四种：我们还可以用ViewGroup.MarginLayoutParams来实现
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

                //第五种：Scroller + computeScroll
                ((View)getParent()).scrollBy(-offsetX,-offsetY);

                break;
        }

        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View) getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX=getScrollX();
        int delta=destX-scrollX;
        //1000秒内滑向destX
        mScroller.startScroll(scrollX,0,delta,0,8000);
        invalidate();
    }


}
