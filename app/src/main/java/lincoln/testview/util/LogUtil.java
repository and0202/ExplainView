package lincoln.testview.util;

import android.util.Log;

/**
 * LogUtil 输出工具类
 * @author lincoln
 *
 */
public class LogUtil {
	/**
	 * 测试状态下为true，输出日志；正式上线时为false，不输出日志
	 */
	public static boolean DEBUG = !GlobalUtil.IS_ONLINE_MODE;
	
	/**
	 * tag
	 */
	private static String tag = "lincoln";
	
	/**
	 * 正常输出日志
	 * @param content
	 */
	public static void d(String content){
		if (DEBUG) {
			if (content == null || content.equals("")) {
				return ;
			}
			Log.d(tag, content);
		}
	}
	public static void d(String tag, String content){
		if (DEBUG) {
			if (content == null || content.equals("")) {
				return ;
			}
			Log.d(tag, content);
		}
	}

	public final static String TAG = "WangYanTest";
	public final static String MATCH = "%s->%s->%d";
	public final static String CONNECTOR = ":<--->:";

	public final static boolean SWITCH = !GlobalUtil.IS_ONLINE_MODE;

	public static String buildHeader() {
		StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
		return String.format(MATCH, stack.getClassName(), stack.getMethodName(),
				stack.getLineNumber()) + CONNECTOR;
	}

	public static void v(Object msg) {
		if (SWITCH) {
			Log.v(TAG, buildHeader() + msg.toString());
		}
	}

	public static void d(Object msg) {
		if (SWITCH) {
			Log.d(TAG, buildHeader() + msg.toString());
		}
	}

	public static void i(Object msg) {
		if (SWITCH) {
			Log.i(TAG, buildHeader() + msg.toString());
		}
	}

	public static void w(Object msg) {
		if (SWITCH) {
			Log.w(TAG, buildHeader() + msg.toString());
		}
	}

	public static void e(Object msg) {
		if (SWITCH) {
			Log.e(TAG, buildHeader() + msg.toString());
		}
	}
	
}
