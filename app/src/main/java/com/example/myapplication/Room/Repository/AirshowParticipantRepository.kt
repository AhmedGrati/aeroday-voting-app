package com.example.myapplication.Room.Repository

import android.util.Log
import com.example.myapplication.Room.Dao.AirshowParticipantDao
import com.example.myapplication.Room.Model.AirshowParticipant
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.util.Listener
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

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
                                            .documents.map { documentSnapshot ->
                                        Log.i("ddebug", documentSnapshot.data.toString())


                                        val d = documentSnapshot.toObject<AirshowParticipant>()!!

                                        Log.d("wadhah", "$d")
                                        //    d.id = documentSnapshot.id.toInt()
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

    fun updateAirshowParticipant(airshowParticipant: AirshowParticipant): Task<Void> {
        Log.d("mydocumentis : ","${airshowParticipant.id.toString()}")
        val addOnSuccessListener = firebaseFirestore.collection("AirshowParticipant")
            .document(airshowParticipant.id.toString())
            .set(airshowParticipant)
            .addOnSuccessListener {
                return@addOnSuccessListener
            }.addOnFailureListener {
                return@addOnFailureListener
            }
        return addOnSuccessListener
    }

}