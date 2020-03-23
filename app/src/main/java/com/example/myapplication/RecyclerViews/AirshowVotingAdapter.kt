package com.example.myapplication.RecyclerViews

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Room.Model.AirshowParticipant
import com.example.myapplication.Room.Model.Competition
import java.net.URL
import java.util.zip.Inflater

class AirshowVotingAdapter : RecyclerView.Adapter<AirshowVotingAdapter.AirshowVotingHolder>(){

    companion object {
        var airshowParticipants = ArrayList<AirshowParticipant>()
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
        holder.airshowParticipantName.text = currentAirshowParticipant.name
        if(currentAirshowParticipant.avatar!=""){
            var url = URL(currentAirshowParticipant.avatar)
            var bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            holder.airshowParticipantAvatar.setImageBitmap(bitmap)
        }
    }

    fun setAirshowParticipants(airshowParticipant : ArrayList<AirshowParticipant>){
        airshowParticipants = airshowParticipant
    }

    fun getAirshowParticipants() : ArrayList<AirshowParticipant>{
        return airshowParticipants
    }

    class AirshowVotingHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        lateinit var airshowParticipantAvatar : ImageView
        lateinit var airshowParticipantName : TextView
        init {
            airshowParticipantAvatar = itemView.findViewById(R.id.airshow_participant_avata_voting) as ImageView
            airshowParticipantName = itemView.findViewById(R.id.airshow_participant_name_text_view)
        }

    }
}