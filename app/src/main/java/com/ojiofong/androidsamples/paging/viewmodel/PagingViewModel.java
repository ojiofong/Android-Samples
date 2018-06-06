package com.ojiofong.androidsamples.paging.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.injection.PagingInjection;
import com.ojiofong.androidsamples.paging.model.RepoItem;
import com.ojiofong.androidsamples.paging.repository.PagingRepository;

import java.util.List;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class PagingViewModel extends AndroidViewModel {

    private static final String TAG = PagingViewModel.class.getSimpleName();
    private PagingRepository repository;

    public PagingViewModel(@NonNull Application application) {
        super(application);
        this.repository = PagingInjection.providesPagingRepository(application.getApplicationContext());
    }

    public void performSearch(String query) {
        this.repository.performSearch(query);
    }

    public LiveData<List<RepoItem>> getLiveRepos() {
        return this.repository.liveRepos;
    }

    public LiveData<String> getError() {
        return this.repository.liveError;
    }

}
