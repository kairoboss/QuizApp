package com.kairat.quizapp.ui.activities.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.data.models.QuizResponse;
import com.kairat.quizapp.data.network.QuizApiClient;
import com.kairat.quizapp.repository.Repository;
import com.kairat.quizapp.ui.activities.main.MainActivity;
import com.kairat.quizapp.ui.activities.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<List<Question>> questions;
    private List<Question> questionList = new ArrayList<>();
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> finishLiveData = new MutableLiveData<>();
    private int correctAnswersCount = 0;

    public QuizViewModel() {
        this.repository = Repository.getInstance();
    }

    public MutableLiveData<List<Question>> loadQuestions(int id, int category, String difficulty){
        questions = repository.loadQuestions(id, category, difficulty);
        if (questions.getValue() != null) questionList.addAll(questions.getValue());
        return questions;
    }


    /**/

    private void moveToPositionOrFinish(int position){
        if (position == questionList.size() - 1){
            finishQuiz();
        }else
            currentQuestionPosition.setValue(position);
    }

    private void finishQuiz() {
        finishLiveData.setValue(correctAnswersCount);
    }

    public void onBackClick(){
        currentQuestionPosition.setValue(currentQuestionPosition.getValue()-1);
    }


    public void onVariantClick(int questionPosition, int answerPosition){
        if (questions == null){
            return;
        }

        questionList = questions.getValue();
        Question question = questionList.get(questionPosition);
        question.setSelectedPosition(answerPosition);
        question.setAnswerClicked(true);
        questionList.set(questionPosition, question);
        if (question.getCorrectAnswer().equals(question.getIncorrectAnswers().get(answerPosition))){
            correctAnswersCount = correctAnswersCount+1;
        }
        questions.setValue(questionList);
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                moveToPositionOrFinish(questionPosition);
            }
        }.start();
    }

    public void onSkipClick(int questionPosition) {
        moveToPositionOrFinish(questionPosition);
    }
}
