package com.ojiofong.androidsamples.paging.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.ojiofong.androidsamples.paging.db.RepoDbModel;

/**
 * Created by ojiofong on 6/14/18.
 * .
 */

public class PagingResult {

    public final LiveData<PagedList<RepoDbModel>> data;
    public final LiveData<String> error;

    public PagingResult(LiveData<PagedList<RepoDbModel>> data, LiveData<String> error) {
        this.data = data;
        this.error = error;
    }
}
