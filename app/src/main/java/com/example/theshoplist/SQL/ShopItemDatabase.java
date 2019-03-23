package com.example.theshoplist.SQL;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ShopItem.class}, version = 1)
public abstract class ShopItemDatabase extends RoomDatabase {
    public abstract ShopItemDAO shopItemDAO();
}
