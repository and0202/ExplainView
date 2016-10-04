package lincoln.testview.lession2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lincoln.testview.R;

/**
 * 第二节课：View的生命周期
 */
public class ViewLifeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life);
    }
}
