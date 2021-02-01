package com.kairat.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.kairat.quizapp.data.local.room.QuizDataBase;

public class App extends Application {
    private static App instance;
    private static QuizDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(getApplicationContext(),
                QuizDataBase.class, "database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }
    public static QuizDataBase getDataBse() {
        return dataBase;
    }
}
