package lincoln.testview;

import android.content.Context;
import android.content.Intent;

/**
 * Created by lincoln on 16/10/4.
 */
public class MainUtil {

    public static void goToNewActivity(Context context, Class<?> classActivity){
        Intent intent = new Intent();
        intent.setClass(context,classActivity);
        context.startActivity(intent);
    }
}
