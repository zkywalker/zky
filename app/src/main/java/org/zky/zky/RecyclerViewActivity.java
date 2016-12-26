package org.zky.zky;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.zky.zky.recyclerview.MyAdapter;
import org.zky.zky.recyclerview.TestBean;
import org.zky.zky.recyclerview.ViewHolder;
import org.zky.zky.recyclerview.swipecard.CardConfig;
import org.zky.zky.recyclerview.swipecard.OverLayCardLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {
    private ArrayList<TestBean> list;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.activity_recycler_view)
    ConstraintLayout activityRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    int i =0;
    private void initData() {
        list = new ArrayList<>();
        list.add(new TestBean((i++) + "   ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "   ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
        list.add(new TestBean((i++) + "  ", "吃玻璃不吐玻璃皮"));
    }

    private void initView() {
        rv.setAdapter(new MyAdapter<TestBean>(this,list,R.layout.item_recycler_view){

            @Override
            public void convert(ViewHolder var1, TestBean var2) {
                var1.setText(R.id.tv_test,var2.getName()+var2.getUrl());
            }
        });
        CardConfig.initConfig(this);
        rv.setLayoutManager(new OverlayLayoutManager(this));

    }

}
