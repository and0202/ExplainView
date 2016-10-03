package lincoln.testview.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by lincoln on 16/4/27.
 */
public class AppUtil {
    public static String getVersionName(Context context) {
        String result = "";
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        result = pi.versionName;

        return result;
    }
}
