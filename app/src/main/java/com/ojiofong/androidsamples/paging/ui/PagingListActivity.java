package com.ojiofong.androidsamples.paging.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.paging.adapter.MyPagingAdapter;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;
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
    RecyclerView recyclerView;

    DiffUtil.ItemCallback<RepoDbModel> diffCallback = new DiffUtil.ItemCallback<RepoDbModel>() {
        @Override
        public boolean areItemsTheSame(RepoDbModel oldItem, RepoDbModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(RepoDbModel oldItem, RepoDbModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    MyPagingAdapter adapter = new MyPagingAdapter(diffCallback);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_list);
        setTitle(R.string.paging_list);
        setupRecyclerView();
        pagingViewModel = ViewModelProviders.of(this).get(PagingViewModel.class);
        pagingViewModel.reposLiveData.observe(this, new Observer<List<RepoDbModel>>() {
            @Override
            public void onChanged(@Nullable List<RepoDbModel> repoItems) {
                if (repoItems != null) {
                    adapter.submitList(repoItems);
                }
            }
        });
        pagingViewModel.errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, s);
            }
        });
        pagingViewModel.performSearch(lastQuery);

    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_paging);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
