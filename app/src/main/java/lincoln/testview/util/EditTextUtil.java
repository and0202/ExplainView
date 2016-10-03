package lincoln.testview.util;

import android.text.InputFilter;
import android.widget.EditText;

/**
 * Created by lincoln on 16/9/6.
 */
public class EditTextUtil {
    public static void setMaxLength(EditText editText, int maxLength){
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }
}
