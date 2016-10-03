package lincoln.testview.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class DensityUtil {
	// dp(dip): device independent pixels(设备独立像素).
	// 不同设备有不同的显示效果,这个和设备硬件有关，一般我们为了支持WVGA、HVGA和QVGA 推荐使用这个，不依赖像素。
	// dp也就是dip，这个和sp基本类似。如果设置表示长度、高度等属性时可以使用dp
	// 或sp。但如果设置字体，需要使用sp。dp是与密度无关，sp除了与密度无关外，还与scale无关。如果屏幕密度为160，这时dp和sp和px是一
	// 样的。1dp=1sp=1px，但如果使用px作单位，如果屏幕大小不变（假设还是3.2寸），而屏幕密度变成了320。那么原来TextView的宽度
	// 设成160px，在密度为320的3.2寸屏幕里看要比在密度为160的3.2寸屏幕上看短了一半。但如果设置成160dp或160sp的话。系统会自动
	// 将width属性值设置成320px的。也就是160 * 320 / 160。其中320 /
	// 160可称为密度比例因子。也就是说，如果使用dp和sp，系统会根据屏幕密度的变化自动进行转换。
	// px: pixels(像素). 不同设备显示效果相同，一般我们HVGA代表320x480像素，这个用的比较多。
	// pt: point，是一个标准的长度单位，1pt＝1/72英寸，用于印刷业，非常简单易用；
	// sp: scaled pixels(放大像素). 主要用于字体显示best for textsize。

	/**
	 *    将px值转换为dip或dp值，保证尺寸大小不变  
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int result = (int) (pxValue / scale + 0.5f);
		return result;
	}

	/**
	 *  将dip或dp值转换为px值，保证尺寸大小不变     
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 *  将px值转换为sp值，保证文字大小不变
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 *  将sp值转换为px值，保证文字大小不变
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	/**
	 * 获得手机分辨率
	 *
	 * @param context
	 * @return
	 */
	public static int[] getResolution(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		int[] arrays = { metrics.widthPixels, metrics.heightPixels };
		// LogUtil.d("arrays:" + arrays[0] + "  " + arrays[1]);
		return arrays;
	}



	public static int getScreenWidth(Context context) {
		int[] array = getResolution(context);
		return array[0];
	}

	public static int getScreenHeight(Context context) {
		int[] array = getResolution(context);
		return array[1];
	}

	public static String getResolutionString(Context context) {
		String result = "";
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		int[] arrays = { metrics.widthPixels, metrics.heightPixels };
		result = "Screenwidth:"+arrays[0]+"  ScreenHeight:"+arrays[1]+"\n";
		return result;
	}

	public static void setScreenAll(Activity activity){
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
	}
}
