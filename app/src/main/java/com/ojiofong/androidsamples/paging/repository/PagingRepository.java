package com.ojiofong.androidsamples.paging.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.injection.PagingInjection;
import com.ojiofong.androidsamples.paging.model.PagingResult;
import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.model.RepoSearchResponse;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

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


    private static final int NETWORK_PAGE_SIZE = 50;
    private static PagingRepository instance;
    private GithubSearchService service;
    private GithubSearchCache cache;
    private String lastQuery = "";
    private int lastRequestedPage;
    private boolean isRequestInProgress;
    public final MutableLiveData<String> liveError = new MutableLiveData<>();


    public PagingRepository(GithubSearchService service, GithubSearchCache cache) {
        this.service = service;
        this.cache = cache;
    }

    public static PagingRepository instance(Context context) {
        if (instance == null) {
            instance = PagingInjection.providesPagingRepository(context);
        }
        return instance;
    }

    public PagingResult search(String query) {
        lastRequestedPage = 1;
        performSearchAndSave(query);

        // Get data from the local cache
        LiveData<List<RepoDbModel>> data = cache.getLiveReposByName(query);

        return new PagingResult(data, liveError);
    }

    public void requestMore(String query) {
        performSearchAndSave(query);
    }

    public void performSearchAndSave(String query) {

        service.searchRepos(query, 1, NETWORK_PAGE_SIZE).enqueue(new Callback<RepoSearchResponse>() {
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

    private void onSuccess(List<RepoItem> repoItems) {
        cache.saveRepos(RepoDbModel.convertFrom(repoItems), new GithubSearchCache.Callback() {
            @Override
            public void onCompleted() {
                lastRequestedPage++;
                isRequestInProgress = false;
            }
        });
    }

    private void onError(String error) {
        liveError.postValue(error);
        isRequestInProgress = false;
    }

}
