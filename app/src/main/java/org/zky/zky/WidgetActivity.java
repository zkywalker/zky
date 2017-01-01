package org.zky.zky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.zky.zky.utils.GetText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WidgetActivity extends BaseThemeActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(GetText.getString(R.string.widget));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void rocker(View view) {
        startActivity(new Intent(this,SurfaceViewActivity.class));
    }

    public void card(View view) {
        startActivity(new Intent(this,RecyclerViewActivity.class));
    }
}
