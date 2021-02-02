package com.kairat.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.kairat.quizapp.data.local.room.QuizDataBase;

public class App extends Application {
    private static QuizDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
            dataBase = Room.databaseBuilder(getApplicationContext(),
                    QuizDataBase.class, "database").allowMainThreadQueries().build();
    }

    public static QuizDataBase getDataBase() {
        return dataBase;
    }
}
