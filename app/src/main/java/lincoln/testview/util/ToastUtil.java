package lincoln.testview.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * 自定义Toast，方便扩展
 *
 * @author lincoln
 */
public class ToastUtil {
    private static Toast mToast;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void ShowLong(Context context, String content) {
        showMessage(context, content, mToast.LENGTH_LONG);
    }

    public static void ShowShort(Context context, String content) {
        showMessage(context, content, mToast.LENGTH_SHORT);
    }


    public static void showMessage(final Context context, final String content, final int duration) {
            if (mToast != null) {
                mToast.setText(content);
                mToast.setDuration(duration);
            } else {
                mToast = Toast.makeText(context, content, duration);
            }
            mToast.show();
    }
}