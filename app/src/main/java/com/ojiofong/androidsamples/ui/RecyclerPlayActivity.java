package com.ojiofong.androidsamples.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.adapter.RecyclerPlayAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerPlayActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerPlayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        int N = 20;
        List<String> items = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            items.add("item " + i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addItemDecoration(
                    new HorizontalDividerItemDecoration.Builder(this)
                            .color(Color.GRAY)
                            .sizeResId(R.dimen.activity_vertical_margin)
                            .marginResId(R.dimen.activity_vertical_margin, R.dimen.activity_vertical_margin)
                            .build());
            //mRecyclerView.addItemDecoration(new CutomItemDecoration.DividerItemDecoration(this, R.drawable.divider));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new RecyclerPlayAdapter(this, items);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }

    public void onClickItemView(View v) {
        int pos = mRecyclerView.getChildAdapterPosition(v);
        if (pos == RecyclerView.NO_POSITION) return;
        Toast.makeText(this, "pos " + pos, Toast.LENGTH_SHORT).show();
        adapter.removeItemAtPosition(pos);
//        adapter.insertItemAtPosition(pos);
//        adapter.changeItemAtPosition(pos);

    }

    public void onLongClickItemView(View v) {
        int pos = mRecyclerView.getChildAdapterPosition(v);
        if (pos == RecyclerView.NO_POSITION) return;
        Toast.makeText(this, "long click " + pos, Toast.LENGTH_SHORT).show();

    }

}
