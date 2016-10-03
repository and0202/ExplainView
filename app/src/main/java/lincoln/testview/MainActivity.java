package lincoln.testview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lincoln.testview.lession1.XYLocationActivity;
import lincoln.testview.move.MainLayoutActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.layout).setOnClickListener(this);
        findViewById(R.id.lession1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.layout:
                intent.setClass(this,MainLayoutActivity.class);
                break;
            case R.id.lession1:
                intent.setClass(this,XYLocationActivity.class);
                break;
        }
        startActivity(intent);

    }
}
