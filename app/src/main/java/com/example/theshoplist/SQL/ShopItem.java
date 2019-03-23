package com.example.theshoplist.SQL;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "ShopItemsDB")
public class ShopItem{
    @PrimaryKey(autoGenerate = true)
    public int rowId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;



    public ShopItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

}