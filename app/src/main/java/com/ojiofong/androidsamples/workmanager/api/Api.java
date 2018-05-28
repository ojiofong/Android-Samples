package com.ojiofong.androidsamples.workmanager.api;

import com.ojiofong.androidsamples.workmanager.model.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ojiofong on 5/28/18.
 */

public class Api {

    private static MyGithubApi githubApi;

    public interface MyGithubApi {
        String BASE_URL = "https://api.github.com/";

        @GET("users/{user}/repos")
        Call<List<RepoResponse>> getRepos(@Path("user") String username);
    }

    public static MyGithubApi getGithubApi() {
        if (githubApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyGithubApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            githubApi = retrofit.create(MyGithubApi.class);
        }
        return githubApi;
    }

}
