package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ojiofong.androidsamples.CutomItemDecoration;
import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.adapter.MainAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addItemDecoration(new CutomItemDecoration.DividerItemDecoration(this, R.drawable.divider));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(new MainAdapter(this, getItems()));
//            mRecyclerView.setBackgroundColor(LibControl.OJI_GREEN);

        }
    }


    private String[] getItems() {
        String[] items = {getString(R.string.sensor), getString(R.string.rx_java), getString(R.string.video)
                , getString(R.string.constraint_layout), getString(R.string.animation), getString(R.string.dagger)
                , getString(R.string.butter_knife), getString(R.string.bound_service), getString(R.string.bluetooth)
                , getString(R.string.web_rtc), getString(R.string.thread_pool_executor), getString(R.string.async_task_loader)
                , getString(R.string.input_detection), getString(R.string.retain_async_task), getString(R.string.view_pager)
                , getString(R.string.mvp), getString(R.string.fragment_dialog_fragment), getString(R.string.mvvm_view_model_live_data)
                , getString(R.string.mvvm_room_live_data)};
        Arrays.sort(items);
        return items;
    }


}
