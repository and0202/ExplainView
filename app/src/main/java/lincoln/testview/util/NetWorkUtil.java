package lincoln.testview.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/**
 * 网络相关工具类
 * 
 * @author lincoln
 * 
 */
/**
 * 获取wifi的名字
 */
/**
 * 获得网管硬件地址
 * 
 * @param context
 * @return
 */
/**
 * 获得网络链接方式
 */
/**
 * 判断是否有网络连接
 * 
 * @param context
 * @return
 */
/**
 * 判读wifi是否可用
 */
/**
 * 判断Mobile是否可用
 */
/**
 * 判断链接状态
 */
/**
 * 判断连接状态，Admaster专用
 * 
 * @param context
 * @return
 */
/**
 * 获取纬度
 * @param context
 * @return
 */
/**
 * 获取经度
 * @param context
 * @return
 */

/**
 * 网络相关工具类
 * 
 * @author lincoln
 * 
 */
public class NetWorkUtil {
	/**
	 * 获取wifi的名字
	 */
	public static String getWifiName(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			if (wifiInfo != null) {
				result = wifiInfo.getSSID();
			}
		}
		return result;
	}

	/**
	 * 获得网管硬件地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getMacAddress(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			if (wifiInfo != null) {
				result = wifiInfo.getMacAddress();
			}
		}
		if (result == null) {
			result = "";
		}
		return result;
	}

	/**
	 * 获得网络链接方式
	 */
	public static String getConnectMethod(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager != null) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			if (wifiInfo != null) {
				result = wifiInfo.getMacAddress();
			}
		}
		return result;
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager != null) {
				NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
				if (mNetworkInfo != null) {
					return mNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	/**
	 * 判读wifi是否可用
	 */
	public boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			
			if (mConnectivityManager!= null) {
				NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (mWiFiNetworkInfo != null) {
					return mWiFiNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	/**
	 * 判断Mobile是否可用
	 */
	public boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			
			if (mConnectivityManager!= null) {
				NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (mMobileNetworkInfo != null) {
					return mMobileNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	/**
	 * 判断链接状态
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager!= null) {
				NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
				if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
					return mNetworkInfo.getType();
				}
			}
			
		}
		return -1;
	}

	/**
	 * 判断连接状态，Admaster专用
	 * 
	 * @param context
	 * @return
	 */
	public static String getConnectedTypeString(Context context) {
		String result = "";
		int netWorkType = getConnectedType(context);
		switch (netWorkType) {
		case -1:
			result = "无网络";
			break;
		case 1:
			result = "wifi";
			break;
		case 2:
			result = "mobile";
			break;
		}
		return result;

	}

	/**
	 * 获取纬度
	 * 
	 * @param context
	 * @return
	 */
	public static double getLocationLatitude(final Context context) {
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		double latitude = -1;
		try {
			final boolean gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
			final boolean wirelessEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (gpsEnabled) {
				location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			} else {
				if (wirelessEnabled) {
					location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				}
			}
			if (null != location) {
				latitude = location.getLatitude();
			}
		} catch (IllegalArgumentException e) {
			latitude = -1;
		} catch (SecurityException e) {
			latitude = -1;
		}

		return latitude;
	}

	/**
	 * 获取经度
	 * 
	 * @param context
	 * @return
	 */
	public static double getLocationLongitude(final Context context) {
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Location location = null;
		double longitude = -1;
		try {
			final boolean gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
			final boolean wirelessEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (gpsEnabled) {
				location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			} else {
				if (wirelessEnabled) {
					location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				}
			}
			if (null != location) {
				longitude = location.getLatitude();
			}
		} catch (IllegalArgumentException e) {
			longitude = -1;
		} catch (SecurityException e) {
			longitude = -1;
		}
		return longitude;
	}

	/**
	 * 获取基站位置区域码
	 * 
	 */
	public static int getLac(Context mContext) {
		int lac = -1;
		try {
			TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			// 中国移动和中国联通获取LAC、CID的方式
			GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
			if (location != null) {
				lac = location.getLac();
			}
			if (lac == -1) {//电信LAC的方式
				CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation();
				if (location1 != null) {
					lac = location1.getNetworkId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lac;
	}
	/**
	 * 获取基站基站编号
	 * 
	 */
	public static int getCellId(Context mContext) {
		int cellId = -1;
		try {
			TelephonyManager mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
			// 中国移动和中国联通获取LAC、CID的方式
			GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
			if (location != null) {
				cellId = location.getCid();
			}
			if (cellId == -1) {//电信CID的方式
				CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation();
				if (location1 != null ) {
					cellId = location1.getBaseStationId();
					cellId /= 16;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cellId;
	}

}
