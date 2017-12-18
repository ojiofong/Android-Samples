package com.ojiofong.androidsamples.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ojiofong.androidsamples.room.model.DbModel;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

@Database(entities = {DbModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static String TAG = AppDatabase.class.getSimpleName();

    public static AppDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, TAG).build();
        }
        return INSTANCE;
    }

    public abstract DbModelDao getDbModelDao();
}
