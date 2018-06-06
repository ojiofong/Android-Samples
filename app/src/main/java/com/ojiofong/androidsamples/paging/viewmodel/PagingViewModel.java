package com.ojiofong.androidsamples.paging.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ojiofong.androidsamples.paging.api.PagingApi;
import com.ojiofong.androidsamples.paging.model.RepoSearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class PagingViewModel extends AndroidViewModel {

    private static final String TAG = PagingViewModel.class.getSimpleName();

    public PagingViewModel(@NonNull Application application) {
        super(application);
    }

    public void performSearch(String query, int page, int itemsPerPage) {
        PagingApi.getGithubSearchService().searchRepos(query, page, itemsPerPage).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepoSearchResponse> call, @NonNull Response<RepoSearchResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Success-> " + response.body().getItems().size());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RepoSearchResponse> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
