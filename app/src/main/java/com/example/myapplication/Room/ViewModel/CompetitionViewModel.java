package com.example.myapplication.Room.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.Room.Competition.Competition;
import com.example.myapplication.Room.Database.Database;
import com.example.myapplication.Room.Repository.CompetitionRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.Flowable;
import kotlinx.coroutines.flow.Flow;

public class CompetitionViewModel extends AndroidViewModel {

    private CompetitionRepository competitionRepository;
    private Flowable<List<Competition>> allCompetitions;
    public CompetitionViewModel(@NonNull Application application){
        super(application);
        competitionRepository = new CompetitionRepository(Database.getInstance(application).competitionDao(), FirebaseFirestore.getInstance());
        allCompetitions = competitionRepository.getAll();
    }

    public Flowable<List<Competition>> getAll(){
        return  allCompetitions;
    }

}
