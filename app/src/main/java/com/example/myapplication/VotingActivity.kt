package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.RecyclerViews.AirshowVotingAdapter
import com.example.myapplication.Room.Model.AirshowParticipant
import com.example.myapplication.Room.Model.Voter

import com.example.myapplication.Room.ViewModel.AirshowParticipantViewModel
import kotlinx.android.synthetic.main.activity_voting.*
import java.security.Permission
import java.util.jar.Manifest

class VotingActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voting)

        //recyclerview settings
        voting_recycler_view.layoutManager = LinearLayoutManager(this)
        voting_recycler_view.setHasFixedSize(true)

        val airshowVotingAdapter  = AirshowVotingAdapter()
        voting_recycler_view.adapter = airshowVotingAdapter
        val airshowParticipantViewModel : AirshowParticipantViewModel by viewModels()


        airshowParticipantViewModel.all.subscribe {
            airshowParticipants ->

            airshowVotingAdapter.setAirshowParticipants(airshowParticipants as ArrayList<AirshowParticipant>)
            Log.d("mymessagevote : ","${airshowParticipants}")
        }

        confirm_button.setOnClickListener {
            if(AirshowVotingAdapter.lastElementChecked != -1){
                var uniqueId : String = Settings.Secure.getString(this.contentResolver , Settings.Secure.ANDROID_ID)
                Log.d("myuniqueid : ","$uniqueId")
                Log.d("thevotedparticipantis: ","${airshowVotingAdapter.getAirshowParticipants().get(AirshowVotingAdapter.lastElementChecked)}")

                Log.d("lastElementChecked : ","${AirshowVotingAdapter.lastElementChecked}")
                var voter = Voter(uniqueId);
                airshowVotingAdapter.getAirshowParticipants().get(AirshowVotingAdapter.lastElementChecked).voters.add(voter)
                var nbrOfVotes : Int = airshowVotingAdapter.getAirshowParticipants().get(AirshowVotingAdapter.lastElementChecked).votes.toInt() + 1
                Log.d("nbrOfVotes : ",nbrOfVotes.toString())
                airshowVotingAdapter.getAirshowParticipants().get(AirshowVotingAdapter.lastElementChecked).votes = nbrOfVotes.toString()
                Log.d("idofCollection : ","${AirshowVotingAdapter.lastElementChecked}")
                airshowParticipantViewModel.updateAirshowParticipant(airshowVotingAdapter.getAirshowParticipants().get(AirshowVotingAdapter.lastElementChecked))
                    .addOnSuccessListener {
                        Toast.makeText(this,"Voted Successfully !" , Toast.LENGTH_LONG).show()
                        LoadingActivity.voterExistence = true
                        var intent = Intent(this,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Log.d("helloerreur : ","${it.message}")
                        Toast.makeText(this,"${it.message.toString()}",Toast.LENGTH_LONG).show()
                    }
                Log.d("newone : ","${airshowVotingAdapter.getAirshowParticipants()}")
            }else{
                Toast.makeText(this,"You should vote first !" , Toast.LENGTH_SHORT).show()
            }
        }
        cancel_button.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

}
