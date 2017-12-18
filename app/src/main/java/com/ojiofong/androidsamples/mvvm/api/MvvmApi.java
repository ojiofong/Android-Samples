package com.ojiofong.androidsamples.mvvm.api;

import com.ojiofong.androidsamples.mvvm.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

public class MvvmApi {

    public interface GithubRepoApi {
        @GET("users/{user}/repos")
        Call<List<Repo>> getRepos(@Path("user") String user);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GithubRepoApi getGithubRepoApi() {
        return getRetrofit().create(GithubRepoApi.class);
    }
}
