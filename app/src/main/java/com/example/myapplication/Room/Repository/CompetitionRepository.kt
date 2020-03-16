package com.example.myapplication.Room.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.Room.Dao.CompetitionDao
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.myapplication.Room.Competition.Competition
import kotlin.collections.map

class CompetitionRepository(private val competitionDao: CompetitionDao , private val db : FirebaseFirestore ) {

    fun getAll() : Flowable<List<Competition1>> {
        var checked = false;
        return competitionDao.getAllCompetitions().subscribeOn(Schedulers.io()).doOnNext { localData ->
            if (!checked) {
                checked = true
                db.collection("Competitions").get().addOnCompleteListener {
                    val cloudData = it.result!!.documents.map{
                        documentSnapshot -> documentSnapshot.data
                    }

                    if(localData != cloudData){
                        competitionDao.deleteAll().andThen(competitionDao.insertCompetition(cloudData))
                    }
                }
            }
        }

}