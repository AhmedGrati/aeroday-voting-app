package com.example.myapplication.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Room.Competition.Competition
import io.reactivex.rxjava3.core.Completable

import io.reactivex.rxjava3.core.Flowable

@Dao
interface CompetitionDao {
    @Query("SELECT * from competition_table")
    fun getAllCompetitions() : Flowable<List<Competition>>

    @Insert
    fun insertCompetition(competition: Competition?) : Completable

    @Query("DELETE FROM competition_table")
    fun deleteAll() : Completable;
}