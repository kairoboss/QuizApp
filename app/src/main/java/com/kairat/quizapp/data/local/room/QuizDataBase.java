package com.kairat.quizapp.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kairat.quizapp.data.models.Result;

@Database(entities = {Result.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
