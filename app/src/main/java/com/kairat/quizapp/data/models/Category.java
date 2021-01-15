
package com.kairat.quizapp.data.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("trivia_categories")
    @Expose
    private List<TriviaCategory> triviaCategories = null;

    public List<TriviaCategory> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategory> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

}
