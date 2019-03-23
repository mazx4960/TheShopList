package com.example.theshoplist.SQL;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "ItemsDB")
public class Item implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int rowId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;

    public Item(){}

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    protected Item(Parcel in) {
        rowId = in.readInt();
        name = in.readString();
        type = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rowId);
        dest.writeString(name);
        dest.writeString(type);
    }
}
