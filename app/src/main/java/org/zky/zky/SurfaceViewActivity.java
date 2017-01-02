package org.zky.zky;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.zky.zky.utils.GetRes;
import org.zky.zky.widget.surfaceview.RockerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurfaceViewActivity extends BaseThemeActivity {

    @BindView(R.id.rv)
    RockerView rv;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        ButterKnife.bind(this);

        toolbar.setTitle(GetRes.getString(R.string.rocker_view));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv.setRockerListener(new RockerView.RockerOnStatusChangeListener() {
            @Override
            public void change(int newStatus) {
                String s = "";
                switch (newStatus) {
                    case 0:
                        s = GetRes.getString(R.string.str_center);
                        break;
                    case 1:
                        s = GetRes.getString(R.string.str_up);
                        break;
                    case 2:
                        s = GetRes.getString(R.string.str_down);

                        break;
                    case 3:
                        s = GetRes.getString(R.string.str_left);
                        break;
                    case 4:
                        s = GetRes.getString(R.string.str_right);
                        break;

                }
                tvStatus.setText(s);
            }
        });
    }


}
