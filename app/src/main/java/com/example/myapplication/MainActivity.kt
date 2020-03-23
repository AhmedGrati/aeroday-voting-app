package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViews.CompetitionAdapter
import com.example.myapplication.RecyclerViews.CompetitionAdapter.OnCompetitionListener
import com.example.myapplication.Room.Model.Competition
import com.example.myapplication.Room.ViewModel.CompetitionViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import java.time.LocalDateTime



class MainActivity  :  AppCompatActivity() , OnCompetitionListener{
    //val disposable = CompositeDisposable()
    lateinit var competitionViewModel: CompetitionViewModel
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

        competitionViewModel.all.subscribe {
            competitions ->

                competitionAdapter.setCompetitions(competitions as ArrayList<Competition>)
            Log.d("mymessage : ","${competitionAdapter.getCompetitions()}")
        }

    }

    override fun onCompetitionClick(position: Int) {
        val allCompetitions : ArrayList<Competition> = CompetitionAdapter.competitions
        var competition = allCompetitions.get(position)
        Log.d("thecompetitionis : ", "$competition")
        if ((competition.name.toUpperCase().trim() == "AIRSHOW") && (competition.active==true)) {
            var intent = Intent(this, VotingActivity::class.java)
            startActivity(intent)

        }
    }

}
