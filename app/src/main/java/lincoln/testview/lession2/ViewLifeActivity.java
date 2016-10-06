package lincoln.testview.lession2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.testview.R;

/**
 * 第二节课：View的生命周期
 */
public class ViewLifeActivity extends AppCompatActivity {

    @BindView(R.id.lession2_lifeImageView)
    LifeImageView lession2LifeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life);
        ButterKnife.bind(this);


        Lession2LogUtil.onCreate();

        //获取View的宽高
        lession2LifeImageView.post(new Runnable() {
            @Override
            public void run() {
                int width = lession2LifeImageView.getWidth();
                int height = lession2LifeImageView.getHeight();
                Log.d("lincoln", "view--->Width:" + width + " -->Height:" + height);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Lession2LogUtil.onResume();
    }
}
