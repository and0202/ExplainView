package lincoln.testview.view.lession1;

import android.view.MotionEvent;

/**
 * Created by lincoln on 16/10/3.
 */
public class EventUtil {
    
    public static String getEventString(MotionEvent event){
        String result = "";
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        float getX = event.getX();
        float getY = event.getY();

        result = "RawX:"+rawX+"   RawY:"+rawY
                +" \n"+"GetX:"+getX+"   GetY:"+getY
                +"\n \n"+"解释："+"\n"
                +"RawX、RawY：触摸在整个屏幕坐标体系中的位置 \n"
                +"GetX、GetY：触摸点在组件ImageView体系中的位置";
        return result;
    }
}
