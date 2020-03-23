package com.example.myapplication.Room.Repository

import android.util.Log
import com.example.myapplication.Room.Dao.AirshowParticipantDao
import com.example.myapplication.Room.Model.AirshowParticipant

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AirshowParticipantRepository(private val airshowParticipantDao: AirshowParticipantDao , private val firebaseFirestore: FirebaseFirestore) {


    fun getAll(): Flowable<List<AirshowParticipant>> {

        var checked = false
        return airshowParticipantDao.getAllAirshowParticipants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { localData ->
                    if (!checked) {
                        checked = true
                        firebaseFirestore.collection("AirshowParticipant")
                                .orderBy("name")
                                .get().addOnCompleteListener {
                                    val cloudData = it.result!!
                                            .documents.map {documentSnapshot ->

                                        val d = documentSnapshot.toObject<AirshowParticipant>()!!
                                        Log.d("snapppp","$d")
                                        d.id = documentSnapshot.id.toInt()
                                        return@map d

                                    }

                                    Log.i("firelogCloudData" , cloudData.toString())



                                    Log.i("firelog" , localData.toString())

                                    if (cloudData != localData) {
                                        //todo replace with differential updates ( add , update , delete )
                                        airshowParticipantDao.deleteAllAirshowParticipants().andThen(airshowParticipantDao.insertAirshowParticipant(cloudData))
                                                .subscribeOn(Schedulers.io())
                                                .subscribe()
                                        Log.i("firelog", "synced data from cloud")
                                    } else Log.i("firelog", "Same data as cloud , didn't sync")
                                }
                    }
                }
    }

}