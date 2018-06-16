package com.ojiofong.androidsamples.paging.repository.api;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.repository.PagingCache;
import com.ojiofong.androidsamples.paging.repository.PagingRepository;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

/**
 * Created by ojiofong on 6/16/18.
 * <p>
 * callback for zero items from the initial loading of the data
 * or because we've reached the end of the data from the DataSource
 * Handles the network requests and database data
 */

public class MyPagingBoundaryCallback extends PagedList.BoundaryCallback<RepoDbModel> {

    private String query;
    private PagingApi.GithubSearchService service;
    private PagingCache cache;
    private PagingRepository repository;

    public MyPagingBoundaryCallback(String query, PagingApi.GithubSearchService service, PagingCache cache) {
        this.query = query;
        this.service = service;
        this.cache = cache;
    }

    public MyPagingBoundaryCallback(String query, PagingRepository repository) {
        this.repository = repository;
        this.query = query;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        repository.requestAndSaveData(query);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull RepoDbModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        repository.requestAndSaveData(query);
    }
}
