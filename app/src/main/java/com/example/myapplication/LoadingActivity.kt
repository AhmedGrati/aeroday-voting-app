package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Room.Model.AirshowParticipant
import com.example.myapplication.Room.ViewModel.AirshowParticipantViewModel
import com.example.myapplication.Room.ViewModel.VoterViewModel


class LoadingActivity : AppCompatActivity() {
    companion object{
        var voterExistence : Boolean = false;
    }
    var allParticipants=ArrayList<AirshowParticipant>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val SPLASH_TIME :Long = 5000 //This is 3 seconds

        //Code to start timer and take action after the timer ends
        //Code to start timer and take action after the timer ends
        Handler().postDelayed(Runnable {
            //Do any action here. Now we are moving to next page
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish()
        }, SPLASH_TIME)
        val voterViewModel : VoterViewModel by viewModels()
        val airshowParticipantViewModel : AirshowParticipantViewModel by viewModels()
        airshowParticipantViewModel.all.subscribe { airshowParticipants ->
            this.allParticipants = airshowParticipants as ArrayList<AirshowParticipant>
            Log.d("allParticipants : ","${allParticipants}")
            this.allParticipants.forEach {
                voterViewModel.insertAllVoters((it.voters)).subscribe {
                    Log.d("insertedVoter", "insertedVoter")
                }
            }
        }
        var uniqueId: String =
            Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        voterViewModel.findVoterById(uniqueId)
            .subscribe { voter ->
                Log.d("voterexists", "${voter}")
                if (voter.size != 1) {
                    voterExistence = false
                    Log.d("voterexists", "${voter}")
                } else {
                    voterExistence = true
                }
            }
    }
}
