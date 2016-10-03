package lincoln.testview.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtil {

	public static final String SP_NAME_OTHER = "SUPRISE_SP";
	public static final String SP_KEY_CHECKIND = "SUPRISE_CHECKIN";
	public static final String SP_KEY_NEWVERSION = "SUPRISE_NEWVSERSION";
	public static final String SP_KEY_GUIDER ="SUPRISE_GUIDER";

	/**
	 * 向SP中写数据
	 */
	public static void putLong(Context context, String spName, String key, long value) {
		SharedPreferences sPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		Editor editor = sPreferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 从SP中读数据
	 */
	public static long getLong(Context context, String spName, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		return sPreferences.getLong(key, 0);
	}


	/**
	 * 新手引导
	 */
	public static String FILENAME_SHOW_USER_GUIDER = "user_guider";// 是否显示新手引导页Sharedpreference文件名
	public static String KEY_NEED_SHOW_GUIDER_WELCOME = "need_show_guider_welcome";// 启动图新手指导
	public static boolean judegIsShowUserGuider(Context context, String key) {
		SharedPreferences shaPreferences = context.getSharedPreferences(FILENAME_SHOW_USER_GUIDER, Context.MODE_PRIVATE);
		boolean showGuider = shaPreferences.getBoolean(key, true);
		// Logger.d(key + " " + showGuider);
		return showGuider;
	}

	/**
	 * 改变值，使其不需要显示新手指导
	 */
	public static void setNotShowUserGuider(Context context, String key) {
		SharedPreferences shaPreferences = context.getSharedPreferences(FILENAME_SHOW_USER_GUIDER, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = shaPreferences.edit();
		editor.putBoolean(key, false);
		boolean result = editor.commit();
		// Logger.d(key + " " + result);
	}

	public static void putString(Context context, String spName, String key, String value) {
		SharedPreferences sPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		Editor editor = sPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(Context context, String spName, String key) {
		SharedPreferences sPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		return sPreferences.getString(key, "default");
	}
}
