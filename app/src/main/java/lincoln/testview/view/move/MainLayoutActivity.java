package lincoln.testview.view.move;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lincoln.testview.R;

public class MainLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);


        CustomMoveView customMoveView = (CustomMoveView) findViewById(R.id.customMoveView);
//        customMoveView.smoothScrollTo(0,-800);
    }
}
