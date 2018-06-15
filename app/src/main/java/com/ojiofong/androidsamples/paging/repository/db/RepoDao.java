package com.ojiofong.androidsamples.paging.repository.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

@Dao
public interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RepoDbModel> posts);

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or contains it in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM " + RepoDbModel.TABLE_NAME + " " +
            "WHERE (name LIKE :queryString) " +
            "OR (description LIKE :queryString) " +
            "ORDER BY name COLLATE NOCASE ASC, stars DESC")
    LiveData<List<RepoDbModel>> getReposByName(String queryString);

    @Query("SELECT * FROM " + RepoDbModel.TABLE_NAME)
    LiveData<List<RepoDbModel>> getAllRepos();

}
