package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Redirection TO Voting Activity
        voting_button.setOnClickListener {
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
        }


    }
}
