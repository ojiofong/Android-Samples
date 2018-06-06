package com.ojiofong.androidsamples.paging.repository;

import android.content.Context;

import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.repository.db.PagingDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class GithubSearchCache {

    private PagingDatabase pagingDatabase;
    private ExecutorService executorService;
    private static GithubSearchCache INSTANCE;

    public static GithubSearchCache instance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GithubSearchCache(PagingDatabase.instance(context), Executors.newSingleThreadExecutor());
        }
        return INSTANCE;
    }

    /**
     * @param pagingDatabase  RoomDatabase used for caching
     * @param executorService Single Thread executor.
     */
    public GithubSearchCache(PagingDatabase pagingDatabase, ExecutorService executorService) {
        this.pagingDatabase = pagingDatabase;
        this.executorService = executorService;
    }

    public void saveRepos(List<RepoItem> repoItems) {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
