package com.example.myapplication.RecyclerViews

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Room.Model.Competition

class CompetitionAdapter(competitionListener: OnCompetitionListener) : RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder>() {

    companion object{
        var competitions = ArrayList<Competition>()
        lateinit var onCompetitionListener : OnCompetitionListener
    }

    init {
        onCompetitionListener = competitionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return CompetitionHolder(itemView , onCompetitionListener)
    }

    override fun getItemCount(): Int {
        return competitions.size
    }

    public fun getAllCompetitions() : ArrayList<Competition>{
        return competitions
    }

    override fun onBindViewHolder(holder: CompetitionHolder, position: Int) {
        var  backgroundImage : String
        var currentCompetition = competitions.get(position)
        holder.textViewName.text = currentCompetition.name
        holder.textViewTime.text = "Time : "+currentCompetition.time
        holder.textViewPlace.text = "Place : "+currentCompetition.place
        if (currentCompetition.active == true) {
            holder.textViewResponseIsActive.text = "On"
            holder.textViewResponseIsActive.setTextColor(Color.GREEN)
        } else {
            holder.textViewResponseIsActive.text = "Off"
            holder.textViewResponseIsActive.setTextColor(Color.RED)
        }
        println("thenameis : " + holder.textViewName.text.toString().toUpperCase())

        when(currentCompetition.name.toUpperCase().trim()){
            "AEROCHALLENGE"->holder.backgroundImage.setImageResource(R.drawable.aerochallenges)

            "AEROENTREPRENEUR"->holder.backgroundImage.setImageResource(R.drawable.aeroentrepreneur)

            "AEROMODÉLISME"->holder.backgroundImage.setImageResource(R.drawable.aeromodelisme)

            "AIRSHOW"->holder.backgroundImage.setImageResource(R.drawable.airshow)

            "CAO"->holder.backgroundImage.setImageResource(R.drawable.cao)

            "EXPOSITIONS AERONOTIQUES"->holder.backgroundImage.setImageResource(R.drawable.expo_aeronautiques)

            "EXPOSITIONS AEROSPACES"->holder.backgroundImage.setImageResource(R.drawable.expo_aerospace)

            "NOVICES"-> {
                println("hellonovices")
                holder.backgroundImage.setImageResource(R.drawable.novices)}
            else -> println("laasba" + currentCompetition.name.toUpperCase().trim())
        }
    }

    fun setCompetitions(competition : ArrayList<Competition>){
        competitions = competition
        notifyDataSetChanged()
    }

    fun getCompetitions() : ArrayList<Competition>{
        return competitions
    }

    class CompetitionHolder(itemView : View , competitionListener: OnCompetitionListener) :RecyclerView.ViewHolder(itemView) , View.OnClickListener{
        lateinit var onCompetitionListener : OnCompetitionListener;
        lateinit var textViewResponseIsActive : TextView
        lateinit var textViewName : TextView
        lateinit var textViewPlace : TextView
        lateinit var textViewTime : TextView
        lateinit var backgroundImage : ImageView
        init {
            textViewName = itemView.findViewById(R.id.challenge_name_text_view)
            textViewResponseIsActive = itemView.findViewById(R.id.response_is_active_text_view)
            textViewPlace = itemView.findViewById(R.id.place_name_text_view)
            textViewTime = itemView.findViewById(R.id.time_text_view)
            backgroundImage = itemView.findViewById(R.id.background_image) as ImageView
            onCompetitionListener = competitionListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onCompetitionListener.onCompetitionClick(adapterPosition)
        }

    }

    interface OnCompetitionListener{
        fun onCompetitionClick(position: Int);
    }
}