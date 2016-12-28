package org.zky.zky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.zky.zky.utils.GetText;
import org.zky.zky.widget.RockerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurfaceViewActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RockerView rv;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        ButterKnife.bind(this);

        toolbar.setTitle(GetText.getString(R.string.rocker_view));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv.setRockerListener(new RockerView.RockerOnStatusChangeListener() {
            @Override
            public void change(int newStatus) {
                String s = "";
                switch (newStatus) {
                    case 0:
                        s = "中";
                        break;
                    case 1:
                        s = "上";
                        break;
                    case 2:
                        s = "下";

                        break;
                    case 3:
                        s = "左";
                        break;
                    case 4:
                        s = "右";
                        break;

                }
                tvStatus.setText(s);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
