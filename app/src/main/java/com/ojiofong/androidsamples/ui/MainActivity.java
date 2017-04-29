package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ojiofong.androidsamples.CutomItemDecoration;
import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.adapter.MainAdapter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        return new String[]{
                "Sensor", "RxJava", "Video", "Constraint",
                "RecyclerPlay", "Animation", "Dagger",
                "ButterKnife", "BoundServiceActivity",
                "Bluetooth", "WebRTC", "ThreadPoolExecutor",
                "AsyncTaskLoader", "InputDetection", "RetainAsyncTask", "ViewPager"
        };
    }


}
