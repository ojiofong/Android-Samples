package com.ojiofong.androidsamples.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  Reference http://www.iayon.com/consuming-rest-api-with-retrofit-2-0-in-android/
 * **/
public interface GitApiInterface {

    //https://api.github.com/search/users?q=tom

    @GET("/search/users")
    Call<GitResult> getUsersNamedTom(@Query("q") String name);

}
