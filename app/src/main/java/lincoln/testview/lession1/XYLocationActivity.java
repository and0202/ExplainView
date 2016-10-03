package lincoln.testview.lession1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lincoln.testview.R;
import lincoln.testview.util.DensityUtil;

public class XYLocationActivity extends AppCompatActivity {

    @BindView(R.id.lession1_imageview)
    ImageView lession1Imageview;
    @BindView(R.id.lession1_tv)
    TextView lession1Tv;
    @BindView(R.id.lession1_button)
    Button lession1Button;

    @BindView(R.id.lession1_tv_event)
    TextView lession1TvEvent;


    private static final String KEY_Zero = "坐标原点(0，0):屏幕左上角" + "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DensityUtil.setScreenAll(this);
        setContentView(R.layout.activity_xylocation);
        ButterKnife.bind(this);


        lession1Tv.setText(KEY_Zero + DensityUtil.getResolutionString(this));

        lession1Imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                String eventString = EventUtil.getEventString(event);
                lession1TvEvent.setText("触摸图片的Event监听 \n"+eventString);
                return true;
            }
        });

    }

    @OnClick(R.id.lession1_button)
    public void buttonClick() {
        int getLeft = lession1Imageview.getLeft();
        int getRight = lession1Imageview.getRight();
        int getTop = lession1Imageview.getTop();
        int getBottom = lession1Imageview.getBottom();


        lession1Tv.setText(
                KEY_Zero
                        +
                        DensityUtil.getResolutionString(this)
                        +
                        "图片左侧到父View左侧的距离:" + getLeft + "\n"
                        + "图片右侧到父View左侧的距离:" + getRight + "\n"
                        + "图片顶部到父View顶部的距离:" + getTop + "\n"
                        + "图片底部到父View顶部的距离:" + getBottom + "\n"

        );
    }

}
