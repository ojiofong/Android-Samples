package com.ojiofong.androidsamples.paging.injection;

import android.content.Context;

import com.ojiofong.androidsamples.paging.api.PagingApi.GithubSearchService;
import com.ojiofong.androidsamples.paging.api.PagingCache;
import com.ojiofong.androidsamples.paging.api.PagingRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ojiofong on 6/6/18.
 * .
 */

public class PagingInjection {


    private static GithubSearchService providesGithubSearchService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubSearchService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubSearchService.class);
    }

    public static PagingRepository providesPagingRepository(Context context) {
        return new PagingRepository(providesGithubSearchService(), PagingCache.instance(context));
    }

}
