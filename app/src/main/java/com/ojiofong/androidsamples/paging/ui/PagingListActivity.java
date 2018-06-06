package com.ojiofong.androidsamples.paging.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.viewmodel.PagingViewModel;

import java.util.List;

/**
 * Created by ojiofong on 6/4/18.
 * .
 */

public class PagingListActivity extends AppCompatActivity {

    private static final String TAG = PagingListActivity.class.getSimpleName();
    PagingViewModel pagingViewModel;
    String lastQuery = "android";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_list);
        setTitle(R.string.paging_list);
        pagingViewModel = ViewModelProviders.of(this).get(PagingViewModel.class);
        pagingViewModel.getLiveRepos().observe(this, new Observer<List<RepoItem>>() {
            @Override
            public void onChanged(@Nullable List<RepoItem> repoItems) {
                Log.d(TAG, repoItems.get(0).toString());
            }
        });
        pagingViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, s);
            }
        });
        pagingViewModel.performSearch(lastQuery);
    }
}
