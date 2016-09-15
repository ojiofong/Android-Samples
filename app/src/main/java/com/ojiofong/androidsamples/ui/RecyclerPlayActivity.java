package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
    ActionMode actionMode;

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
//            mRecyclerView.addItemDecoration(
//                    new HorizontalDividerItemDecoration.Builder(this)
//                            .color(Color.GRAY)
//                            .sizeResId(R.dimen.divider_margin)
//                            .marginResId(R.dimen.activity_vertical_margin, R.dimen.activity_vertical_margin)
//                            .build());

            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

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


        if (actionMode != null) {
            toggleSelection(pos);
        } else {

//        adapter.removeItemAtPosition(pos);
//        adapter.insertItemAtPosition(pos);
//        adapter.changeItemAtPosition(pos);

            Toast.makeText(this, "pos " + pos, Toast.LENGTH_SHORT).show();
        }


    }

    public void onLongClickItemView(View v) {
        int pos = mRecyclerView.getChildAdapterPosition(v);
        if (pos == RecyclerView.NO_POSITION) return;

        if (actionMode == null) {
            actionMode = startSupportActionMode(new MyActionModeCallBack());
        }

        toggleSelection(pos);

    }


    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        int count = adapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle("Selected items " + count);
            actionMode.invalidate();
        }
    }

    private class MyActionModeCallBack implements ActionMode.Callback {


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.list_select_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    adapter.deleteSelectedPositions();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelection();
            actionMode = null;
        }
    }

}
