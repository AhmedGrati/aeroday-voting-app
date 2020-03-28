package com.example.myapplication.Room.Model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "airshow_participant_table")
data class AirshowParticipant(
        @PrimaryKey
        var id : Int = -1,
        var avatar : String = "",
        var detail : String = "",
        var name : String = "",
        var score : Int = 0,
        var votes : String = "0",
        var voters : ArrayList<Voter> = ArrayList<Voter>()
){
        override fun toString() : String{
                return ("AirshowParticipan {" +
                        "id : $id , " +
                        "avatar : $avatar , " +
                        "detail : $detail , " +
                        "name : $name , " +
                        "score : $score , " +
                        "votes : $votes , " +
                        "voters : $voters , " +
                        "}")
        }
}