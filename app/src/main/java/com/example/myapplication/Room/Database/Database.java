package com.example.myapplication.Room.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Room.Model.Competition;
import com.example.myapplication.Room.Dao.CompetitionDao;

@androidx.room.Database(entities = Competition.class , version = 2 ,exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database insatance;
    public abstract CompetitionDao competitionDao();

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
        PopoulateDbAsyncTask(Database db){
            competitionDao = db.competitionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            competitionDao.insertCompetition(new Competition(false,"Competition 1","Tunis","00:00" , 0));
            competitionDao.insertCompetition(new Competition(false,"Competition 2","Tunis","00:00" ,1));
            competitionDao.insertCompetition(new Competition(false,"Competition 3","Tunis","00:00" , 2));
            return null;
        }
    }

}
