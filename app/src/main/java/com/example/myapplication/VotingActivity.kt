package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.RecyclerViews.AirshowVotingAdapter
import com.example.myapplication.Room.Model.AirshowParticipant

import com.example.myapplication.Room.ViewModel.AirshowParticipantViewModel
import kotlinx.android.synthetic.main.activity_voting.*

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
            Log.d("mymessagevote : ","${airshowVotingAdapter.getAirshowParticipants()}")
        }
    }
}
