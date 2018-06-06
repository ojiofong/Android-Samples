package com.ojiofong.androidsamples.paging.repository.api;

import com.ojiofong.androidsamples.paging.model.RepoSearchResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

public class PagingApi {

    private static GithubSearchService githubSearchService;

    public interface GithubSearchService {
        String BASE_URL = "https://api.github.com";

        @GET("/search/repositories?sort=stars")
        Call<RepoSearchResponse> searchRepos(@Query("q") String query,
                                             @Query("page") int page,
                                             @Query("per_page") int itemsPerPage);

    }

    public static GithubSearchService getGithubSearchService() {
        if (githubSearchService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GithubSearchService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(GithubSearchService.class);
        }
        return githubSearchService;
    }
}
