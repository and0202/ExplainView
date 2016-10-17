package lincoln.testview.view.lession4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lincoln.testview.R;

public class BezierAcivity extends AppCompatActivity {


    @BindView(R.id.water)
    BezierWaterView water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_acivity);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.water_btn)
    public void startWaterAnimation(){
        water.startAnimation();
    }


}
