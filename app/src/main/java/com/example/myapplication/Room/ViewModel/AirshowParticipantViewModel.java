package com.example.myapplication.Room.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.Room.Database.Database;
import com.example.myapplication.Room.Model.AirshowParticipant;
import com.example.myapplication.Room.Model.Competition;
import com.example.myapplication.Room.Repository.AirshowParticipantRepository;
import com.example.myapplication.Room.Repository.CompetitionRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Optional;

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
    public Task<Void> updateAirshowParticipant(AirshowParticipant airshowParticipant){
       return airshowParticipantRepository.updateAirshowParticipant(airshowParticipant);
    }


}
