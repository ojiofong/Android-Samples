package com.ojiofong.androidsamples.room.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.room.adapter.RoomAdapter;
import com.ojiofong.androidsamples.room.model.DbModel;
import com.ojiofong.androidsamples.room.viewmodel.RoomViewModel;
import com.ojiofong.androidsamples.ui.BaseActivity;

import java.util.List;

public class RoomActivity extends BaseActivity {

    RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle(R.string.mvvm_room_live_data);
        setupViews();
        RoomViewModel roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        roomViewModel.getLiveData().observe(this, new Observer<List<DbModel>>() {
            @Override
            public void onChanged(@Nullable List<DbModel> dbModels) {
                adapter.setData(dbModels);
            }
        });
        roomViewModel.fetchData();
    }

    private void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoomAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
