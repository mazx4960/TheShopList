package com.example.theshoplist.SQL;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ItemDAO {
    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item items);
}
