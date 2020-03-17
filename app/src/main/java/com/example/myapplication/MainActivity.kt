package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.Room.Competition.Competition
import com.example.myapplication.Room.Database.Database
import com.example.myapplication.Room.Repository.CompetitionRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.ktx.Firebase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import java.time.Duration
import java.time.LocalDateTime



class MainActivity : AppCompatActivity() {
    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date : LocalDateTime = LocalDateTime.now()
        val aerodayDate : LocalDateTime = LocalDateTime.of(2020,4,12,0,0,0)
        val differenceBetweenTwoDays = Duration.between(date,aerodayDate).toDays()
        countDown_text.text = "J-$differenceBetweenTwoDays. Stay Tuned !"


        //data here

        val repo = CompetitionRepository(
            Database.getInstance(
                application).competitionDao()
            , FirebaseFirestore.getInstance()
        )

        disposable.add(
            repo.getAll().subscribe {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
        )



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

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
