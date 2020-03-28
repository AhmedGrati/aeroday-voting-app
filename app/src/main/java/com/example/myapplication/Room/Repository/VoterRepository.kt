package com.example.myapplication.Room.Repository

import android.util.Log
import com.example.myapplication.Room.Dao.VoterDao
import com.example.myapplication.Room.Model.Voter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class VoterRepository(private val voterDao: VoterDao) {

    fun findUserById(id : String) : Flowable<Optional<Voter>> {
        return voterDao.findVoterById(id)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun insertVoters(voters : List<Voter?>?) : Completable {
        return voterDao.deleteAllVoters().andThen(voterDao.insertAllVoters(voters)).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun returnAllVoters() : Flowable<List<Voter>> {
        return voterDao.getAllVoters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}