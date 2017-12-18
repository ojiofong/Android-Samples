package com.ojiofong.androidsamples.room.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
