package com.ojiofong.androidsamples.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;

import com.ojiofong.androidsamples.mvvm.api.MvvmApi;
import com.ojiofong.androidsamples.mvvm.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

public class MvvmViewModel extends ViewModel {
    private MutableLiveData<List<Repo>> repoLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> toggleLoadingLiveData = new MutableLiveData<>();

    public void fetchData() {
        if (repoLiveData.getValue() != null && !repoLiveData.getValue().isEmpty()) {
            return;
        }

        toggleLoadingLiveData.setValue(true);

        MvvmApi.getGithubRepoApi().getRepos("ojiofong").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call, @NonNull Response<List<Repo>> response) {
                repoLiveData.setValue(response.body());
                toggleLoadingLiveData.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Repo>> getRepoLiveData() {
        return repoLiveData;
    }

    public MutableLiveData<Boolean> getToggleLoadingLiveData() {
        return toggleLoadingLiveData;
    }
}
