package lincoln.testview.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lincoln on 16/4/19.
 */
public class NetUtils {

    private static final String TAG = NetUtils.class.getSimpleName();

    /**
     * 检查网络连接的状态
     *
     * @param c
     * @return
     */
    public static boolean enableNetwork(final Context c) {
        boolean isnetwork = false;
        if (c == null) {
            return false;
        }
        // 获得检查网络连接状态的系统服务
        ConnectivityManager connectivityManager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null
                && (networkInfo.isAvailable() || networkInfo.isConnected())) {
            isnetwork = true;
        }
        return isnetwork;
    }

    /**
     * 判读当前网络是否为wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        if (mContext == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 当前网络是否为3G或WIFI
     *
     * @param ctx
     * @return
     */
    public static boolean isWifiOr3G(Context ctx) {
        boolean isok = false;
        if (ctx == null) {
            return isok;
        }
        ConnectivityManager connManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return isok;
        }
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return isok;
        }
        String netTypeName = networkInfo.getTypeName();
        if (netTypeName != null
                && (netTypeName.toUpperCase().indexOf("WIFI") >= 0 || netTypeName
                .toUpperCase().indexOf("NET") >= 0)) {
            isok = true;
        }

        return isok;
    }

    /**
     * 网络连接方式
     *
     * @param c
     * @return
     */
    public static String getConnectType(Context c) {
		/*
		 * ConnectivityManager connManager = (ConnectivityManager)
		 * c.getSystemService(Context.CONNECTIVITY_SERVICE); String netTypeName
		 * = connManager.getActiveNetworkInfo().getTypeName(); return
		 * netTypeName;
		 */
        if (c != null) {
            ConnectivityManager connManager = (ConnectivityManager) c
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connManager == null) {
                return null;
            }
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return null;
            }
            String netTypeName = networkInfo.getTypeName();
            return netTypeName;
        }
        return null;

    }

    /**
     * 打开wifi设置
     *
     * @param ctx
     */
    public static void openWIFI(Context ctx) {
        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }



}
