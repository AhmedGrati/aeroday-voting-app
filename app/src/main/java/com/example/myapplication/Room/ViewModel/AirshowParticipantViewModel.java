package com.example.myapplication.Room.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.Room.Database.Database;
import com.example.myapplication.Room.Model.AirshowParticipant;
import com.example.myapplication.Room.Model.Competition;
import com.example.myapplication.Room.Repository.AirshowParticipantRepository;
import com.example.myapplication.Room.Repository.CompetitionRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.Flowable;


public class AirshowParticipantViewModel extends AndroidViewModel {
    private AirshowParticipantRepository airshowParticipantRepository;
    private Flowable<List<AirshowParticipant>> allAirshowParticipant;

    public AirshowParticipantViewModel(@NonNull Application application){
        super(application);
        airshowParticipantRepository = new AirshowParticipantRepository(Database.getInstance(application).airshowParticipantDao(), FirebaseFirestore.getInstance());
        allAirshowParticipant = airshowParticipantRepository.getAll();
    }

    public Flowable<List<AirshowParticipant>> getAll(){
        return  allAirshowParticipant;
    }
}
