package com.kairat.quizapp.ui.fragments.main;

import android.icu.util.TaiwanCalendar;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kairat.quizapp.data.models.Category;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.data.models.QuizResponse;
import com.kairat.quizapp.data.models.TriviaCategory;
import com.kairat.quizapp.data.network.QuizApiClient;
import com.kairat.quizapp.databinding.MainFragmentBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private Category categoryResponse;
    public MutableLiveData<List<String>> categoriesNames = new MutableLiveData<>();
    public MutableLiveData<List<Integer>> categoriesIds = new MutableLiveData<>();



    public void getCategories(){
        QuizApiClient.getInstance().getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        categoryResponse = response.body();
                        getCategoryList(categoryResponse.getTriviaCategories());
                        Log.e("success", "yaai");
                    }else{
                        Log.e("nullBody", "null_body");
                    }
                }else{
                    Log.e("notSuccess", "noooo");
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage()+ Arrays.toString(t.getStackTrace())+t.getCause()+t.getCause());
            }
        });
    }


    private void getCategoryList(List<TriviaCategory> categories){
        List<String> names = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            names.add(categories.get(i).getName());
        }
        categoriesNames.setValue(names);
    }
}