package com.ojiofong.androidsamples.paging.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
    DataSource.Factory<Integer, RepoDbModel> getReposByName(String queryString);

    @Query("SELECT * FROM " + RepoDbModel.TABLE_NAME)
    DataSource.Factory<Integer, RepoDbModel> getAllRepos();

}
