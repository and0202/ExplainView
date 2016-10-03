package lincoln.testview.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Locale;

import lincoln.testview.R;

/**
 * 获取手机设备本身的信息
 * 
 * @author lincoln
 * 
 */
public class DeviceInfoUtil {
	/**
	 * isRoot(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isRoot() {
		boolean result = false;
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes("exit\n");
			os.flush();

			int exitValue = process.waitFor();

			if (exitValue == 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * 判断当前手机是否有ROOT权限,不显示root选择对话框
	 * 
	 * @return
	 */
	public static boolean isRootNotShow() {
		boolean bool = false;
		try {
			if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
				bool = false;
			} else {
				bool = true;
			}
			LogUtil.d("bool = " + bool);
		} catch (Exception e) {

		}
		return bool;
	}

	/**
	 * 获得移动国家码
	 * 
	 * @return
	 */
	public static int getMcc(Context context) {
		int result = 0;
		try {
			TelephonyManager tel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String networkOperator = tel.getNetworkOperator();
			if (networkOperator != null && networkOperator.length() == 15) {
				result = Integer.parseInt(networkOperator.substring(0, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 获得移动网络码
	 * 
	 * @return
	 */
	public static int getmnc(Context context) {
		int result = 0;
		try {
			TelephonyManager tel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String networkOperator = tel.getNetworkOperator();

			if (networkOperator != null && networkOperator.length() == 15) {
				result = Integer.parseInt(networkOperator.substring(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获取输入法打开的状态
	 *
	 * @param context
	 * @return
	 */
	public static boolean isSofeKeyBoardOpen(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
		return isOpen;
	}

	/**
	 * 如果输入法在窗口上已经显示，则隐藏，反之则显示
	 *
	 * @param context
	 */
	public static void chageSofeKeyBoardState(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 关闭软键盘
	 *
	 * @param context
	 */
	public static void closeSofeKeyBoard(Context context, EditText edt) {
		try {
			InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			IBinder windowToken = edt.getWindowToken();
			if (windowToken != null) {
				im.hideSoftInputFromWindow(windowToken, 0);
			}
		} catch (Exception e) {

		}
		// if (isSofeKeyBoardOpen(context)) {
		// chageSofeKeyBoardState(context);
		// }
	}

	/**
	 * 初次获取设备唯一标识
	 *
	 * 9774d56d682e549c是模拟器的唯一标识，排除
	 *
	 * @param mContext
	 */
	public static String getAndroidID(Context mContext) {
		// Try to get the ANDROID_ID

		String androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
		if (androidId == null || androidId.equals("9774d56d682e549c") || androidId.length() < 15) {
			final SecureRandom random = new SecureRandom();
			androidId = new BigInteger(64, random).toString(16);
		}
		return androidId;
	}

	/**
	 * 获得应用的名字
	 */
	public static String getAppName(Context context) {
		return context.getResources().getString(R.string.app_name);
	}

	/**
	 * 获得设备的IP地址
	 *
	 * @param context
	 * @return
	 */

	public static String getIP(Context context) {
		String ip = null;
		// Method 3
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface inf = interfaces.nextElement();
			for (Enumeration<InetAddress> enumAddress = inf.getInetAddresses(); enumAddress.hasMoreElements();) {
				InetAddress in = enumAddress.nextElement();
				if (!in.isLinkLocalAddress()) {
					return in.getHostAddress();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw newversion RuntimeException();
		}
		return ip;
	}


	/**
	 * 获得设备型号：ME526
	 */
	public static String getDeviceModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获得IMEI账号
	 *
	 * @param context
	 * @return
	 */
	public static String getImei(Context context) {
		String Imei = "";
		Imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if (Imei == null) {
			Imei = "";
		}
		return Imei;
	}

	/**
	 * 获取系统语言
	 */
	public static String getOSLanguage() {
		return Locale.getDefault().getLanguage();
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

	/**
	 * 获得设备名字
	 *
	 * @return
	 */
	public static String getDeviceName() {
		return android.os.Build.PRODUCT;
	}

	/**
	 * 获得设备厂商 :MOTOROLAR
	 *
	 * @return
	 */
	public static String getFactoryName() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * 获得应用的包名
	 */
	public static String getPackageName(Context context) {
		return context.getPackageName();
	}
	/**
	 * 获得版本Name
	 */
	public static String getAppVersionName(Context context) {
		String result = "";
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			result = pi.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取系统版本:4.1.1
	 *
	 * @return
	 */
	public static String getOSVersion() {
		return android.os.Build.VERSION.RELEASE;
	}


}
