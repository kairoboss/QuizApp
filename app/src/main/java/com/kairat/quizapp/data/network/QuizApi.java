package com.kairat.quizapp.data.network;

import com.kairat.quizapp.data.models.Category;
import com.kairat.quizapp.data.models.QuizResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {

    @GET("api.php")
    Call<QuizResponse> getQuestions(
        @Query("amount") int amount,
        @Query("category") int category,
        @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<Category> getCategories();
}
