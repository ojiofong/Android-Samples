package com.ojiofong.androidsamples.paging.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.paging.adapter.MyPagingAdapter;
import com.ojiofong.androidsamples.paging.db.RepoDbModel;
import com.ojiofong.androidsamples.paging.viewmodel.PagingViewModel;

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
        pagingViewModel.reposLiveData.observe(this, new Observer<PagedList<RepoDbModel>>() {
            @Override
            public void onChanged(@Nullable PagedList<RepoDbModel> repoItems) {
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
