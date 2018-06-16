package com.ojiofong.androidsamples.paging.api;

import android.arch.paging.DataSource;
import android.content.Context;

import com.ojiofong.androidsamples.paging.db.PagingDatabase;
import com.ojiofong.androidsamples.paging.db.RepoDao;
import com.ojiofong.androidsamples.paging.db.RepoDbModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class PagingCache {

    private ExecutorService executorService;
    private RepoDao repoDao;
    private static PagingCache INSTANCE;

    public static PagingCache instance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PagingCache(PagingDatabase.instance(context).getRepoDao(), Executors.newSingleThreadExecutor());
        }
        return INSTANCE;
    }

    /**
     * @param executorService Single Thread executor.
     */
    public PagingCache(RepoDao repoDao, ExecutorService executorService) {
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

    public DataSource.Factory<Integer, RepoDbModel> getLiveReposByName(String query) {
        // sqlite pattern = %query% i.e. contains the query
        query = new StringBuilder(query.trim()).append('%').insert(0, '%').toString();
        return repoDao.getReposByName(query);
    }

    public DataSource.Factory<Integer, RepoDbModel> getAllRepos() {
        return repoDao.getAllRepos();
    }

    public interface Callback {
        void onCompleted();
    }
}
