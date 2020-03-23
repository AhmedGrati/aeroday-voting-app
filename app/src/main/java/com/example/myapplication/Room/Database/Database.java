package com.example.myapplication.Room.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Room.Converters.Converter;
import com.example.myapplication.Room.Dao.AirshowParticipantDao;
import com.example.myapplication.Room.Dao.CompetitionDao;
import com.example.myapplication.Room.Dao.VoterDao;
import com.example.myapplication.Room.Model.AirshowParticipant;
import com.example.myapplication.Room.Model.Competition;
import com.example.myapplication.Room.Model.Voter;

import java.util.ArrayList;


@androidx.room.Database(entities = {Competition.class , AirshowParticipant.class , Voter.class}, version = 8, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class Database extends RoomDatabase {

    private static Database insatance;
    public abstract CompetitionDao competitionDao();
    public abstract VoterDao voterDao();
    public abstract AirshowParticipantDao airshowParticipantDao();

    public static synchronized Database getInstance(Context context){
        if(insatance == null){
            insatance = Room.databaseBuilder(context , Database.class , "room_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return insatance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopoulateDbAsyncTask(insatance).execute();
        }
    };

    private static class PopoulateDbAsyncTask extends AsyncTask<Void , Void , Void>{
        private CompetitionDao competitionDao;
        private AirshowParticipantDao airshowParticipantDao;
        private VoterDao voterDao;
        PopoulateDbAsyncTask(Database db){
            competitionDao = db.competitionDao();
            voterDao = db.voterDao();
            airshowParticipantDao = db.airshowParticipantDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            airshowParticipantDao.insertAirshowParticipant(new AirshowParticipant(0,"Avatar 1","detail 1","name 1" , 0 , 0 , new ArrayList<Voter>()));
            airshowParticipantDao.insertAirshowParticipant(new AirshowParticipant(0,"Avatar 2","detail 2","name 2" , 0 , 0 ,new ArrayList<Voter>()));
            airshowParticipantDao.insertAirshowParticipant(new AirshowParticipant(0,"Avatar 3","detail 3","name 3" , 0 , 0 , new ArrayList<Voter>()));
            competitionDao.insertCompetition(new Competition(false,"Competition 1","Tunis","00:00" , 0));
            competitionDao.insertCompetition(new Competition(false,"Competition 2","Tunis","00:00" ,1));
            competitionDao.insertCompetition(new Competition(false,"Competition 3","Tunis","00:00" , 2));
            return null;
        }
    }

}
