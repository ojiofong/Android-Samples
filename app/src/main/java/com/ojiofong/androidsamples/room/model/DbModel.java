package com.ojiofong.androidsamples.room.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

@Entity(tableName = DbModel.TABLE_NAME)
public class DbModel {
    public static final String TABLE_NAME = "db_model_table";

    // region Db Model Object

    @PrimaryKey(autoGenerate = true)
    private long id;
    private int repoId;
    private String name;
    private String description;

    public DbModel() {
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // endregion
}
