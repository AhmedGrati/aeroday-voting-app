package com.example.myapplication.Room.Model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "airshow_participant_table")
public class AirshowParticipant{
        @PrimaryKey
        var id : Int = 0
        var avatar : String = ""
        var detail : String = ""
        var name : String = ""
        var score : Int = 0
        var votes : Int = 0
        var voters : ArrayList<Voter> = ArrayList<Voter>()


        @Ignore
        constructor(){
        }

        constructor(id:Int , avatar : String , detail : String , name : String , score : Int , votes:Int , voters : ArrayList<Voter>){
                this.id = id;
                this.avatar = avatar
                this.detail = detail
                this.name = name
                this.score = score
                this.voters = voters
        }
}