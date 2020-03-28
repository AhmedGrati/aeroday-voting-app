package com.example.myapplication.Room.Repository

import android.util.Log
import com.example.myapplication.Room.Dao.VoterDao
import com.example.myapplication.Room.Model.Voter
import io.reactivex.Completable
import io.reactivex.Flowable

class VoterRepository(private val voterDao: VoterDao) {

    fun findUserById(id : String) : Flowable<Voter?>{
        Log.d("fromvoterviewmodel : ", "${voterDao.findVoterById(id)}");
        return voterDao.findVoterById(id)
    }

    fun insertVoters(voters : List<Voter?>?) : Completable{
        return voterDao.insertAllVoters(voters)
    }
    fun returnAllVoters() : Flowable<List<Voter>>{
        return voterDao.getAllVoters();
    }
}