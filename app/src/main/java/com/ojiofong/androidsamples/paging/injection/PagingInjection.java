package com.ojiofong.androidsamples.paging.injection;

import android.content.Context;

import com.ojiofong.androidsamples.paging.repository.GithubSearchCache;
import com.ojiofong.androidsamples.paging.repository.PagingRepository;
import com.ojiofong.androidsamples.paging.repository.api.PagingApi.GithubSearchService;
import com.ojiofong.androidsamples.paging.repository.db.PagingDatabase;

import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ojiofong on 6/6/18.
 * .
 */

public class PagingInjection {

    public static GithubSearchCache providesGithubSearchCache(Context context) {
        return new GithubSearchCache(providesPagingDatabase(context), Executors.newSingleThreadExecutor());
    }

    public static GithubSearchService providesGithubSearchService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubSearchService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubSearchService.class);
    }

    public static PagingDatabase providesPagingDatabase(Context context) {
        return PagingDatabase.instance(context);
    }

    public static PagingRepository providesPagingRepository(Context context) {
        return new PagingRepository(providesGithubSearchService(), providesGithubSearchCache(context));
    }

}
