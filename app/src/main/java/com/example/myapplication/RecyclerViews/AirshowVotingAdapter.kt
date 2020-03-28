package com.example.myapplication.RecyclerViews

import android.content.Context
import android.graphics.BitmapFactory
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Room.Model.AirshowParticipant
import com.example.myapplication.Room.Model.Competition
import java.net.URL
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class AirshowVotingAdapter : RecyclerView.Adapter<AirshowVotingAdapter.AirshowVotingHolder>(){


    private var airshowParticipants = ArrayList<AirshowParticipant>()
    companion object {
        var lastElementChecked = -1
        var radioButtons = ArrayList<RadioButton>()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirshowVotingHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.voting_item , parent , false)
        return AirshowVotingHolder(itemView)
    }

    override fun getItemCount(): Int {
        return airshowParticipants.size
    }

    override fun onBindViewHolder(holder: AirshowVotingHolder, position: Int) {

        var currentAirshowParticipant = airshowParticipants.get(position)
        Log.d("currentPosition : ","$currentAirshowParticipant")
        holder.airshowParticipantName.text = currentAirshowParticipant.name
        Log.d("lastposition : ","$lastElementChecked")
        radioButtons.add(holder.radioButton)
        /*if(currentAirshowParticipant.avatar!=""){
            var url = URL(currentAirshowParticipant.avatar)
            var bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            holder.airshowParticipantAvatar.setImageBitmap(bitmap)
        }*/
    }

    fun setAirshowParticipants(airshowParticipant : ArrayList<AirshowParticipant>){
        airshowParticipants = airshowParticipant
        radioButtons = ArrayList<RadioButton>()
        notifyDataSetChanged()

    }


    fun getAirshowParticipants() : ArrayList<AirshowParticipant>{
        return airshowParticipants
    }

    class AirshowVotingHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        lateinit var airshowParticipantAvatar : ImageView
        lateinit var airshowParticipantName : TextView
        lateinit var radioButton: RadioButton

        init {
            airshowParticipantAvatar = itemView.findViewById(R.id.airshow_participant_avata_voting) as ImageView
            airshowParticipantName = itemView.findViewById(R.id.airshow_participant_name_text_view)
            radioButton = itemView.findViewById(R.id.radio_button) as RadioButton

            radioButton.setOnClickListener {
                lastElementChecked = adapterPosition
                for(i in 0..radioButtons.size-1){
                    Log.d("mycounter : ","$i")
                    Log.d("adapeter : ","$adapterPosition")
                    if(i!=adapterPosition){
                        radioButtons.get(i).setChecked(false)
                    }else{
                        radioButtons.get(i).setChecked(true)
                    }
                }
            }
        }

    }
}