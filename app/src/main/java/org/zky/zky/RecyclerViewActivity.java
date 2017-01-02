package org.zky.zky;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import org.zky.zky.recyclerview.MyAdapter;
import org.zky.zky.recyclerview.MyCallback;
import org.zky.zky.recyclerview.OverlayLayoutManager;
import org.zky.zky.recyclerview.TestBean;
import org.zky.zky.recyclerview.ViewHolder;
import org.zky.zky.utils.GetRes;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends BaseThemeActivity {

    private ArrayList<TestBean> list;
    private RecyclerView.Adapter adapter;
    private OverlayLayoutManager manager;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    int i = 0;

    private void initData() {
        list = new ArrayList<>();
        list.add(new TestBean((i++) + "   ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "   ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
        list.add(new TestBean((i++) + "  ", GetRes.getString(R.string.str_test)));
    }

    private void initView() {
        toolbar.setTitle(GetRes.getString(R.string.recycler_view_card));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv.setAdapter(adapter = new MyAdapter<TestBean>(this, list, R.layout.item_recycler_view) {

            @Override
            public void convert(ViewHolder var1, TestBean var2) {
                var1.setText(R.id.tv_test, var2.getName() + var2.getUrl());
            }
        });
        rv.setLayoutManager(manager = new OverlayLayoutManager(this));
        ItemTouchHelper helper = new ItemTouchHelper(new MyCallback(rv, adapter, list, manager.getTranslationY(), manager.getScale(), manager.getMaxShowCount()));
        helper.attachToRecyclerView(rv);
    }


}
