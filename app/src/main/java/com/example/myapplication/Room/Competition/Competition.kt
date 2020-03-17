package com.example.myapplication.Room.Competition

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.FirebaseFirestore


@Entity(tableName = "competition_table")
data class Competition(
        var isActive : Boolean = false
        , var name :String = ""
        , var place :String = ""
        , var time :String = ""
        , @PrimaryKey
        var id: Int = -1
          ) {
}