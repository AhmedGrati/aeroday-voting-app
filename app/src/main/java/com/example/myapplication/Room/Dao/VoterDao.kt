package com.example.myapplication.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Room.Model.Voter
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface VoterDao {

    @Query("SELECT * FROM voter_table")
    fun getAllVoters() : Flowable<List<Voter>>

    @Insert
    fun insertVoter(voter: Voter) : Completable

    @Query("DELETE FROM voter_table")
    fun deleteAllVoters() : Completable
}