package com.ojiofong.androidsamples.mvvm.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.mvvm.api.MvvmAdapter;
import com.ojiofong.androidsamples.mvvm.model.Repo;
import com.ojiofong.androidsamples.mvvm.viewmodel.MvvmViewModel;
import com.ojiofong.androidsamples.ui.BaseActivity;

import java.util.List;

public class MVVMActivity extends BaseActivity {

    private MvvmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle(R.string.mvvm_view_model_live_data);
        setupViews();
        MvvmViewModel mvvmViewModel = ViewModelProviders.of(this).get(MvvmViewModel.class);
        mvvmViewModel.fetchData();
        mvvmViewModel.getRepoLiveData().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                if (repos != null) {
                    adapter.setData(repos);
                }
            }
        });
        mvvmViewModel.getToggleLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null) {
                    findViewById(R.id.progressBar1).setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    private void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MvvmAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
