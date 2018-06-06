package com.ojiofong.androidsamples.paging.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.paging.viewmodel.PagingViewModel;

/**
 * Created by ojiofong on 6/4/18.
 * .
 */

public class PagingListActivity extends AppCompatActivity {

    PagingViewModel pagingViewModel;
    String lastQuery = "android";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_list);
        setTitle(R.string.paging_list);
        pagingViewModel = ViewModelProviders.of(this).get(PagingViewModel.class);
        pagingViewModel.performSearch(lastQuery, 1, 20);
    }
}
