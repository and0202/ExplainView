package lincoln.testview.lession2;

import android.util.Log;

/**
 * Created by lincoln on 16/10/4.
 */
public class Lession2LogUtil {

    public static void init(){
        Log.d("lincoln","init");
    }

    
    public static void onFinishInflate(){
        Log.d("lincoln","onFinishInflate");
    }
    
    public static void onAttachedToWindow(){
        Log.d("lincoln","onAttachedToWindow");
    }
    
    public static void onMeasure(){
        Log.d("lincoln","onMeasure");
    }
    
    public static void onLayout(){
        Log.d("lincoln","onLayout");
    }

    
    public static void onWindowFocusChanged(boolean hasWindowFocus){
        Log.d("lincoln","onWindowFocusChanged:"+hasWindowFocus);
    }
    
    public static void onDraw(){
        Log.d("lincoln","onDraw");
    }
    
    public static void onDetachedFromWindow(){
        Log.d("lincoln","onDetachedFromWindow");
    }
    
    public static void onSizeChanged(){
        Log.d("lincoln","onSizeChanged");
    }
}
