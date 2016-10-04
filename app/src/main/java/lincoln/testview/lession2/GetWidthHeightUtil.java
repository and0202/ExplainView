package lincoln.testview.lession2;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 用来获取View的宽度和高度
 * Created by lincoln on 16/10/4.
 */
public class GetWidthHeightUtil {

    /**
     * 在绘制View之前获取宽高。
     * 存在多次调用的问题，获取到宽高后需要立即移除监听
     */
    public static void getWH_OnPreDrawListener(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        int width = view.getWidth();
                        int height = view.getHeight();
                        print(width, height);
                        view.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
    }

    /**
     * 在layout state变化或者visibility发生变化时获取宽高
     * <p>
     * 存在多次调用问题
     */
    public static void getWH_onGlobalListener(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= 16) {
                            view.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } else {
                            view.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                        int width = view.getWidth(); // 获取宽度
                        int height = view.getHeight(); // 获取高度
                        print(width, height);
                    }
                });
    }

    /**
     * 在布局边界发生变化时调用
     *
     * @param view
     */
    public static void getWH_(final View view) {
        view.addOnLayoutChangeListener(
                new View.OnLayoutChangeListener() {

                    @Override
                    public void onLayoutChange(View v, int l, int t, int r, int b,
                                               int oldL, int oldT, int oldR, int oldB) {
                        view.removeOnLayoutChangeListener(this);
                        view.getWidth(); // 获取宽度
                        view.getHeight(); // 获取高度
                    }
                });
    }

    public static void print(int width, int height) {
        Log.d("lincoln", "width:" + width + " height:" + height);

    }
}
