package com.example.myapplication.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Room.Model.AirshowParticipant
import io.reactivex.Completable

import io.reactivex.Flowable

@Dao
interface AirshowParticipantDao {

    @Query("SELECT * FROM airshow_participant_table")
    fun getAllAirshowParticipants() : Flowable<List<AirshowParticipant>>

    @Insert
    fun insertAirshowParticipant(airshowParticipants: List<AirshowParticipant>?) : Completable

    @Insert
    fun insertAirshowParticipant(airshowParticipants: AirshowParticipant?) : Completable

    @Query("DELETE FROM airshow_participant_table")
    fun deleteAllAirshowParticipants() : Completable

}