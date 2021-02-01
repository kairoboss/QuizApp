package com.kairat.quizapp.ui.activities.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.kairat.quizapp.App;
import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Result;
import com.kairat.quizapp.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private int correctAnswersCount;
    private int amount;
    private String difficulty ;
    private String category ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        amount = getIntent().getIntExtra("questionsAmount", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        category = getIntent().getStringExtra("category");
        binding.resultCategory.setText(category);
        binding.resultDifficultyValue.setText(difficulty);
        binding.resultCorrectAnswersValue.setText(String.valueOf(correctAnswersCount));
        binding.questionsAmountResult.setText("/"+ amount);
        binding.resultPercentValue.setText(getResultPercent(correctAnswersCount, amount)+"%");
        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result result = new Result();
                result.setAmount(amount);
                result.setCategory(category);
                result.setCorrectAnswers(correctAnswersCount);
                result.setDifficulty(difficulty);
                result.setCreatedAt(System.currentTimeMillis());
                result.setResult((int) getResultPercent(correctAnswersCount, amount));
                App.getDataBse().quizDao().addResult(result);
            }
        });
    }

    private double getResultPercent(int correctAnswersCount, int amount){
        double result = correctAnswersCount/amount;
        return result*100;
    }
}