package com.ojiofong.androidsamples.paging.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.ojiofong.androidsamples.paging.repository.db.PagingDatabase;
import com.ojiofong.androidsamples.paging.repository.db.RepoDao;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class GithubSearchCache {

    private ExecutorService executorService;
    private RepoDao repoDao;
    private static GithubSearchCache INSTANCE;

    public static GithubSearchCache instance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GithubSearchCache(PagingDatabase.instance(context).getRepoDao(), Executors.newSingleThreadExecutor());
        }
        return INSTANCE;
    }

    /**
     * @param executorService Single Thread executor.
     */
    public GithubSearchCache(RepoDao repoDao, ExecutorService executorService) {
        this.executorService = executorService;
        this.repoDao = repoDao;
    }

    public void saveRepos(final List<RepoDbModel> items, final Callback callback) {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                repoDao.insert(items);
                if (callback != null) callback.onCompleted();
            }
        });
    }

    public LiveData<List<RepoDbModel>> getLiveReposByName(String query) {
        // squlite pattern = %query% i.e. contains the query
        query = new StringBuilder(query.trim()).append('%').insert(0, '%').toString();
        return repoDao.getReposByName(query);
    }

    public LiveData<List<RepoDbModel>> getAllRepos() {
        return repoDao.getAllRepos();
    }

    public interface Callback {
        void onCompleted();
    }
}
