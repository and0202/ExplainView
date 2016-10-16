package lincoln.testview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lincoln.testview.view.lession1.XYLocationActivity;
import lincoln.testview.view.lession2.ViewLifeActivity;
import lincoln.testview.view.lession3.Lession3Activity;
import lincoln.testview.view.lession4.BezierAcivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lession1)
    Button lession1;
    @BindView(R.id.lession2)
    Button lession2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.lession1)
    public void lession1() {
        MainUtil.goToNewActivity(this, XYLocationActivity.class);
    }

    @OnClick(R.id.lession2)
    public void lession2() {
        MainUtil.goToNewActivity(this, ViewLifeActivity.class);
    }

    @OnClick(R.id.lession3)
    public void lession3() {
        MainUtil.goToNewActivity(this, Lession3Activity.class);
    }

    @OnClick(R.id.lession4)
    public void lession4() {
        MainUtil.goToNewActivity(this, BezierAcivity.class);
    }

}
