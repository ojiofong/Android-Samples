package com.ojiofong.androidsamples.paging.model;

import android.arch.lifecycle.LiveData;

import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

import java.util.List;

/**
 * Created by ojiofong on 6/14/18.
 * .
 */

public class PagingResult {

    public final LiveData<List<RepoDbModel>> data;
    public final LiveData<String> error;

    public PagingResult(LiveData<List<RepoDbModel>> data, LiveData<String> error) {
        this.data = data;
        this.error = error;
    }
}
