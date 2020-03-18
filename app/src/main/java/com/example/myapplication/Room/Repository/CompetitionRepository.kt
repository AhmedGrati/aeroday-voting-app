package com.example.myapplication.Room.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.Room.Dao.CompetitionDao
import com.google.firebase.firestore.FirebaseFirestore
import com.example.myapplication.Room.Competition.Competition
import com.google.firebase.firestore.ktx.toObject
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.map

class CompetitionRepository(private val dao: CompetitionDao , private val db : FirebaseFirestore ) {


    fun getAll(): Flowable<List<Competition>> {

        var checked = false
        return dao.getAllCompetitions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { localData ->
                if (!checked) {
                    checked = true
                    db.collection("Competition")
                        .orderBy("name")
                        .get().addOnCompleteListener {
                            val cloudData = it.result!!
                                .documents.map { documentSnapshot ->
                                val d = documentSnapshot.toObject<Competition>()!!
                                d.id = documentSnapshot.id.toInt()
                                return@map d
                            }

                            Log.i("firelog" , cloudData.toString())



                            Log.i("firelog" , localData.toString())

                            if (cloudData != localData) {
                                //todo replace with differential updates ( add , update , delete )
                                dao.deleteAll().andThen(dao.insertCompetition(cloudData))
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()
                                Log.i("firelog", "synced data from cloud")
                            } else Log.i("firelog", "Same data as cloud , didn't sync")
                        }
                }
            }
    }
}