package com.example.myapplication.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Room.Model.Voter
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface VoterDao {

    @Query("SELECT * FROM voter_table")
    fun getAllVoters() : Flowable<List<Voter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllVoters(voters: List<Voter?>?) : Completable

    @Query("DELETE FROM voter_table")
    fun deleteAllVoters() : Completable

    @Query(value = "SELECT * FROM voter_table where id LIKE :voter_id ")
    fun findVoterById(voter_id: String): Flowable<List<Voter>>

    @Query("DELETE from voter_table")
    fun deleteAll() : Completable

}