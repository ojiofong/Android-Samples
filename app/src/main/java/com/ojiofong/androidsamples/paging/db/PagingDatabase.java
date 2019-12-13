package com.ojiofong.androidsamples.paging.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

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