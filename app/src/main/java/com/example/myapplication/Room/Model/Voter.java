package com.example.myapplication.Room.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "voter_table")

public class Voter {
    @PrimaryKey
    @NonNull
    private String id;

    @Ignore
    public Voter(){

    }
    public Voter(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
