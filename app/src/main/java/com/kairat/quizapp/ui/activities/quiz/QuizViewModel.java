package com.kairat.quizapp.ui.activities.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.data.models.QuizResponse;
import com.kairat.quizapp.data.network.QuizApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizViewModel extends ViewModel {

    private QuizResponse quizResponse;
    public MutableLiveData<List<Question>> questionsLiveData = new MutableLiveData<>();

    public void getQuestions(int id, int category, String difficulty){

        QuizApiClient.getInstance().getQuestions(id, category, difficulty).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e("Success", call.request().url().toString());
                        questionsLiveData.setValue(response.body().getQuestions());

                    }
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.e("tag", "onFailure" + t.getMessage()+t.getLocalizedMessage());
            }
        });
    }

}
