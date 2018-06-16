package com.ojiofong.androidsamples.paging.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.db.RepoDbModel;
import com.ojiofong.androidsamples.paging.injection.PagingInjection;
import com.ojiofong.androidsamples.paging.model.PagingResult;
import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.model.RepoSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ojiofong.androidsamples.paging.api.PagingApi.GithubSearchService;

/**
 * Created by ojiofong on 6/5/18.
 * .
 * The Repository acts as a data layer responsible for web service and local cache handling
 */

public class PagingRepository {

    private static final int NETWORK_PAGE_SIZE = 50;
    private static final int DATABASE_PAGE_SIZE = 20; // chunks to load from db
    private static PagingRepository instance;
    private GithubSearchService service;
    private PagingCache cache;
    private String lastQuery = "";
    private int lastRequestedPage = 1;
    private boolean isRequestInProgress;
    public final MutableLiveData<String> error = new MutableLiveData<>();

    public PagingRepository(GithubSearchService service, PagingCache cache) {
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

        // Construct the boundary callback
        // Let the callback determine when to load more data
        MyPagingBoundaryCallback myBoundaryCallback = new MyPagingBoundaryCallback(query, this);

        // Get data source factory from the local cache
        DataSource.Factory<Integer, RepoDbModel> dataSourceFactory = cache.getLiveReposByName(query);

        // Build a LivePagedList with BoundaryCallback
        LivePagedListBuilder<Integer, RepoDbModel> builder = new LivePagedListBuilder<>(dataSourceFactory, DATABASE_PAGE_SIZE);
        builder.setBoundaryCallback(myBoundaryCallback);
        LiveData<PagedList<RepoDbModel>> data = builder.build();

        return new PagingResult(data, error);
    }

    public void requestMore(String query) {
        requestAndSaveData(query);
    }

    public void requestAndSaveData(String query) {

        if (isRequestInProgress) return;

        isRequestInProgress = true;

        service.searchRepos(query, lastRequestedPage, NETWORK_PAGE_SIZE).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepoSearchResponse> call, @NonNull Response<RepoSearchResponse> response) {
                RepoSearchResponse body = response.body();
                List<RepoItem> list = body != null ? body.getItems() : null;
                if (list != null) {
                    onSuccess(list);
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
        cache.saveRepos(RepoDbModel.convertFrom(repoItems), new PagingCache.Callback() {
            @Override
            public void onCompleted() {
                lastRequestedPage++;
                isRequestInProgress = false;
            }
        });
    }

    private void onError(String error) {
        this.error.postValue(error);
        isRequestInProgress = false;
    }

}
