package com.example.theshoplist.SQL;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    public int rowId;

    @ColumnInfo(name = "startDate")
    public String startDate;

    @ColumnInfo(name = "type")
    public String type;

    public Item(){}

    public Item(String startDate, String type) {
        this.startDate = startDate;
        this.type = type;
    }
}
