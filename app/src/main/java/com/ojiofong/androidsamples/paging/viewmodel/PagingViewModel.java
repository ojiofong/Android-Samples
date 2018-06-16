package com.ojiofong.androidsamples.paging.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ojiofong.androidsamples.paging.model.PagingResult;
import com.ojiofong.androidsamples.paging.repository.PagingRepository;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

import java.util.List;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class PagingViewModel extends AndroidViewModel {

    private static final String TAG = PagingViewModel.class.getSimpleName();
    private PagingRepository repository;
    private MutableLiveData<String> queryLiveData;
    private LiveData<PagingResult> pagingResultLiveData;
    public LiveData<PagedList<RepoDbModel>> reposLiveData;
    public LiveData<String> errorLiveData;

    public PagingViewModel(@NonNull Application application) {
        super(application);
        this.repository = PagingRepository.instance(application);
        initLiveData();
    }

    private void initLiveData() {
        this.queryLiveData = new MutableLiveData<>();
        this.pagingResultLiveData = Transformations.map(queryLiveData, new Function<String, PagingResult>() {
            @Override
            public PagingResult apply(String input) {
                return repository.search(input);
            }
        });
        this.reposLiveData = Transformations.switchMap(pagingResultLiveData, new Function<PagingResult, LiveData<PagedList<RepoDbModel>>>() {
            @Override
            public LiveData<PagedList<RepoDbModel>> apply(PagingResult input) {
                return input.data;
            }
        });
        this.errorLiveData = Transformations.switchMap(pagingResultLiveData, new Function<PagingResult, LiveData<String>>() {
            @Override
            public LiveData<String> apply(PagingResult input) {
                return input.error;
            }
        });

    }

    public void performSearch(String query) {
        this.queryLiveData.postValue(query);
    }

}
