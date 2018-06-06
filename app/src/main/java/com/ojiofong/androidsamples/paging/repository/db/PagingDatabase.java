package com.ojiofong.androidsamples.paging.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ojiofong.androidsamples.paging.model.RepoDbModel;

/**
 * Created by ojiofong on 6/5/18.
 * .
 */

@Database(entities = {RepoDbModel.class}, version = 1, exportSchema = false)
public abstract class PagingDatabase extends RoomDatabase {
    private static final String TAG = PagingDatabase.class.getSimpleName();
    private static PagingDatabase INSTANCE;

    public static PagingDatabase instance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PagingDatabase.class, TAG)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract RepoDao getRepoDao();
}