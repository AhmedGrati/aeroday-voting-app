package com.example.myapplication.Room.Competition;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "competition_table")
public class CompetitionOld {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "isActive")
    private boolean isActive;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="place")
    private String place;

    @ColumnInfo(name="time")
    private String time;

    public CompetitionOld(int id, boolean isActive, String name, String place, String time) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.place = place;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
