package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.RecyclerViews.CompetitionAdapter
import com.example.myapplication.Room.Model.Competition
import com.example.myapplication.Room.ViewModel.CompetitionViewModel

import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import java.time.LocalDateTime



class MainActivity : AppCompatActivity() {
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

        val competitionAdapter : CompetitionAdapter = CompetitionAdapter()
        challenges_recycler_view.adapter = competitionAdapter





        val competitionViewModel : CompetitionViewModel by viewModels()

        competitionViewModel.all.subscribe {
            competitions ->

                competitionAdapter.setCompetitions(competitions as ArrayList<Competition>)
            Log.d("mymessage : ","${competitionAdapter.getCompetitions()}")
        }

        //data here

        /*val repo = CompetitionRepository(
            Database.getInstance(
                application).competitionDao()
            , FirebaseFirestore.getInstance()
        )

        disposable.add(
            repo.getAll().subscribe {
                Toast.makeText(this, "geeeeee", Toast.LENGTH_SHORT).show()
            }
        )*/



       /*var reference = FirebaseDatabase.getInstance().getReference().child("Competition").child("0")

        reference.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot) {
                    println("hello snapshot $p0")
                }

                override fun onCancelled(p0: DatabaseError) {
                    println("bye $p0")
                }
            }

        )*/


        //Redirection TO Voting Activity
        /*voting_button.setOnClickListener {
            intent = Intent(this,VotingActivity::class.java);
            startActivity(intent);
        }

        //Redirection TO AerodayProgram Activity
        program_aeroday_button.setOnClickListener {
            intent = Intent(this,VotingActivity::class.java);
            startActivity(intent);
        }

        //Redirection TO AirshowProgram Activity
        program_airshow_button.setOnClickListener {
            intent = Intent(this,VotingActivity::class.java);
            startActivity(intent);
        }*/



    }

    /*override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }*/
}
