package com.example.myapplication.Room.Repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import com.example.myapplication.Room.Dao.AirshowParticipantDao
import com.example.myapplication.Room.Model.AirshowParticipant
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.util.Listener
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import java.net.URL
import java.util.*

class AirshowParticipantRepository(private val airshowParticipantDao: AirshowParticipantDao , private val firebaseFirestore: FirebaseFirestore){


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

    /*fun getAirshowParticipantImage(avatar: String?) : Bitmap?{
        if(avatar!=""){
            var url = URL("https://scontent.ftun11-1.fna.fbcdn.net/v/t1.0-9/36589673_2068603200018117_192489786982793216_n.jpg?_nc_cat=101&_nc_sid=09cbfe&_nc_ohc=-wF2CL4269oAX9t3qxQ&_nc_ht=scontent.ftun11-1.fna&oh=282ef16c9df53ed1e66d158f29994279&oe=5EA8075C")
            var bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            return bitmap
        }
        return null
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        return getAirshowParticipantImage(params.get(0))
    }*/
}