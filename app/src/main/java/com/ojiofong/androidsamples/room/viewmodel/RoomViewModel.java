package com.ojiofong.androidsamples.room.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.ojiofong.androidsamples.mvvm.api.MvvmApi;
import com.ojiofong.androidsamples.mvvm.model.Repo;
import com.ojiofong.androidsamples.room.db.AppDatabase;
import com.ojiofong.androidsamples.room.model.DbModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

public class RoomViewModel extends AndroidViewModel {

    private boolean isLoaded = false;
    private AppDatabase database;
    private LiveData<List<DbModel>> mData;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getINSTANCE(application);
        mData = database.getDbModelDao().getAllDbModel();
    }

    public LiveData<List<DbModel>> getLiveData() {
        return mData;
    }

    public void delete(final DbModel dbModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.getDbModelDao().deleteDbModel(dbModel);
            }
        }).start();
    }

    public void insert(final List<DbModel> dbModels) {
        if (dbModels == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (DbModel d : dbModels) {
                    database.getDbModelDao().insertDbModel(d);
                }
            }
        }).start();
    }

    public void fetchData() {
        if (isLoaded) return;
        MvvmApi.getGithubRepoApi().getRepos("ojiofong").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call, @NonNull Response<List<Repo>> response) {
                isLoaded = response.isSuccessful();
                List<Repo> body = response.body();
                if (body != null) {
                    List<DbModel> dbModelList = new ArrayList<>();
                    for (Repo r : body) {
                        DbModel dbModel = new DbModel();
                        dbModel.setRepoId(r.getId());
                        dbModel.setName(r.getName());
                        dbModel.setDescription(r.getDescription());
                        dbModelList.add(dbModel);
                    }
                    insert(dbModelList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {

            }
        });
    }
}
