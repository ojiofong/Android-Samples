package com.ojiofong.androidsamples.room.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ojiofong.androidsamples.room.model.DbModel;

import java.util.List;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

@Dao
public interface DbModelDao {

    @Query("select * from " + DbModel.TABLE_NAME)
    LiveData<List<DbModel>> getAllDbModel();

    @Query("select * from " + DbModel.TABLE_NAME + " where id = :itemId")
    DbModel getDbModel(long itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDbModel(DbModel dbModel);

    @Delete
    void deleteDbModel(DbModel dbModel);
}
