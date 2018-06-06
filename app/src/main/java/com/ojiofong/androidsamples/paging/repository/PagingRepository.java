package com.ojiofong.androidsamples.paging.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.model.RepoSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ojiofong.androidsamples.paging.repository.api.PagingApi.GithubSearchService;

/**
 * Created by ojiofong on 6/5/18.
 * .
 * The Repository acts as a data layer responsible for web service and local cache handling
 */

public class PagingRepository {

    private GithubSearchService service;
    private GithubSearchCache cache;
    public final MutableLiveData<List<RepoItem>> liveRepos = new MutableLiveData<>();
    public final MutableLiveData<String> liveError = new MutableLiveData<>();

    public PagingRepository(GithubSearchService service, GithubSearchCache cache) {
        this.service = service;
        this.cache = cache;
    }

    public void performSearch(String query) {
        service.searchRepos(query, 1, 20).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepoSearchResponse> call, @NonNull Response<RepoSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getItems() != null) {
                    RepoSearchResponse repoSearchResponse = response.body();
                    List<RepoItem> list = repoSearchResponse != null ? repoSearchResponse.getItems() : null;
                    if (list != null) {
                        onSuccess(list);
                    }
                } else {
                    onError("Unknown error with response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RepoSearchResponse> call, @NonNull Throwable t) {
                onError(t.getMessage());
            }
        });
    }

    public void saveRepoItems(List<RepoItem> repoItems) {

    }

    private void onSuccess(List<RepoItem> repoItems) {
        liveRepos.postValue(repoItems);
    }

    private void onError(String error) {
        liveError.postValue(error);
    }


}
