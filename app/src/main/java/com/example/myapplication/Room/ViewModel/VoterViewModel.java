package com.example.myapplication.Room.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.Room.Database.Database;
import com.example.myapplication.Room.Model.AirshowParticipant;
import com.example.myapplication.Room.Model.Voter;
import com.example.myapplication.Room.Repository.VoterRepository;

import java.util.List;
import java.util.Optional;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class VoterViewModel extends AndroidViewModel {
    private VoterRepository voterRepository;
    public VoterViewModel(@NonNull Application application) {
        super(application);
        this.voterRepository = new VoterRepository(Database.getInstance(application).voterDao());
    }

    public Flowable<Optional<Voter>> findVoterById(String id){
        return this.voterRepository.findUserById(id);
    }

    public Completable insertAllVoters(List<Voter> voters){
        return this.voterRepository.insertVoters(voters);
    }

    public Flowable<List<Voter>> getAllVoters(){
        return this.voterRepository.returnAllVoters();
    }
}
