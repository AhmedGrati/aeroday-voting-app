package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViews.CompetitionAdapter
import com.example.myapplication.RecyclerViews.CompetitionAdapter.OnCompetitionListener
import com.example.myapplication.Room.Model.AirshowParticipant
import com.example.myapplication.Room.Model.Competition
import com.example.myapplication.Room.ViewModel.AirshowParticipantViewModel
import com.example.myapplication.Room.ViewModel.CompetitionViewModel
import com.example.myapplication.Room.ViewModel.VoterViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class MainActivity  :  AppCompatActivity() , OnCompetitionListener{
    //val disposable = CompositeDisposable()
    lateinit var competitionViewModel: CompetitionViewModel
    var allParticipants=ArrayList<AirshowParticipant>()
    val voterViewModel : VoterViewModel by viewModels()
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date : LocalDateTime = LocalDateTime.now()
        val aerodayDate : LocalDateTime = LocalDateTime.of(2020,4,12,0,0,0)
        val differenceBetweenTwoDays = Duration.between(date,aerodayDate).toDays()
        countDown_text.text = "J-$differenceBetweenTwoDays. Stay Tuned !"

        //recyclerview settings
        challenges_recycler_view.layoutManager = LinearLayoutManager(this)
        challenges_recycler_view.setHasFixedSize(true)

        val competitionAdapter : CompetitionAdapter = CompetitionAdapter(this)
        challenges_recycler_view.adapter = competitionAdapter

        val competitionViewModel : CompetitionViewModel by viewModels()
        val airshowParticipantViewModel : AirshowParticipantViewModel by viewModels()

        competitionViewModel.all.subscribe {
            competitions ->

                competitionAdapter.setCompetitions(competitions as ArrayList<Competition>)
            Log.d("mymessage : ","${competitionAdapter.getCompetitions()}")
        }

        airshowParticipantViewModel.all.subscribe { airshowParticipants ->
            this.allParticipants = airshowParticipants as ArrayList<AirshowParticipant>
        }
        for(i in 0..this.allParticipants.size-1){
            this.voterViewModel.insertAllVoters((this.allParticipants.get(i).voters)).subscribe {
                Log.d("insertedVoter","insertedVoter")
            }
        }
        voterViewModel.allVoters.subscribe {
            voters ->
                Log.d("allVoters : ","$voters")
        }

    }

    @SuppressLint("CheckResult")
    override fun onCompetitionClick(position: Int) {
        val allCompetitions : ArrayList<Competition> = CompetitionAdapter.competitions
        var competition = allCompetitions.get(position)
        Log.d("thecompetitionis : ", "$competition")
        if ((competition.name.toUpperCase().trim() == "AIRSHOW") && (competition.active==true)) {
            var uniqueId : String = Settings.Secure.getString(this.contentResolver , Settings.Secure.ANDROID_ID)
            Log.d("hani hna","hani hna")
            this.voterViewModel.findVoterById(uniqueId).subscribe {
                voter ->
                    println("${voter.id}")
                    /*Log.d("voterahmedgrati","$voter")
                    if(voter != null){
                        Toast.makeText(this,"You already Voted Sorry !!" , Toast.LENGTH_SHORT).show()
                    }else{
                        var intent = Intent(this, VotingActivity::class.java)
                        startActivity(intent)
                    }*/
            }

        }
    }

}
