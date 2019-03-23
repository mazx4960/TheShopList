package com.example.theshoplist.SQL;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;




    @Dao
    public interface ShopItemDAO {
        @Query("SELECT * FROM ShopItemsDB")
        List<ShopItem> getAll();

        @Query("SELECT * FROM ShopItemsDB WHERE type = :type")
        List<ShopItem> queryByType(String type);

        @Insert
        void insertAll(ShopItem... items);

        @Delete
        void delete(ShopItem items);
    }


