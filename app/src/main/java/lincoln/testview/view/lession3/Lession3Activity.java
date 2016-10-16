package lincoln.testview.view.lession3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lincoln.testview.R;
import lincoln.testview.view.lession3.Pie.PaintView;
import lincoln.testview.view.lession3.Pie.PieData;

/**
 * Paint、 Canvas、Matrix示例
 */
public class Lession3Activity extends AppCompatActivity {

    @BindView(R.id.lession3_paintView)
    PaintView lession3PaintView;
    private int CHILD_COUNT = 5;
    private int[] colors = new int[]{R.color.darkgreen,R.color.green,R.color.orangered,R.color.orange,R.color.blue};
    private int[] angles = new int[]{30,80,100,20,130};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession3);
        ButterKnife.bind(this);
        initPaintView();
    }

    public void initPaintView(){
        List<PieData> list = new ArrayList<>();
        for (int index =0; index < CHILD_COUNT; index++){
            String name = "Order:"+index;
            float angle = angles[index] ;
            PieData pieData = new PieData(name,angle);
            pieData.color = colors[index];
            list.add(pieData);
        }

        lession3PaintView.setData(list);
    }
}
