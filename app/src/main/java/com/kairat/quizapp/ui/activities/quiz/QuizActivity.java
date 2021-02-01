package com.kairat.quizapp.ui.activities.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.databinding.ActivityQuizBinding;
import com.kairat.quizapp.ui.activities.result.ResultActivity;
import com.kairat.quizapp.ui.adapters.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnClicks {

    private ActivityQuizBinding binding;
    private QuizViewModel viewModel;
    private int amount;
    private int category;
    private String difficulty;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.setViewModel(viewModel);
        amount = getIntent().getIntExtra("amount", 0);
        category = getIntent().getIntExtra("categoryId", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        categoryName = getIntent().getStringExtra("categoryName");
        binding.toolbarTitle.setText(categoryName);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        QuizAdapter adapter = new QuizAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        manager.setSmoothScrollbarEnabled(true);
        binding.quizRecycler.setLayoutManager(manager);
        binding.quizRecycler.setAdapter(adapter);
       viewModel.loadQuestions(amount, category, difficulty.toLowerCase()).observe(this, adapter::addList);
       binding.questionsCount.setText("/"+ amount);
        binding.progressBarValue.setText(String.valueOf(1));
        binding.quizProgressBar.setMin(1);
        binding.quizProgressBar.setMax(amount);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.quizRecycler);
        viewModel.currentQuestionPosition.observe(this, integer -> scrollOnClick(adapter, integer));
        binding.skipBtn.setOnClickListener(v -> scrollOnClick(adapter, manager.findLastCompletelyVisibleItemPosition()));
        binding.quizBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollOnBack(manager.findLastCompletelyVisibleItemPosition());
            }
        });
        viewModel.finishLiveData.observe(this, this::startResultActivity);
    }

    private void startResultActivity(int correctAnswersCount) {
        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("correctAnswersCount",correctAnswersCount);
        i.putExtra("questionsAmount", amount);
        i.putExtra("difficulty", difficulty);
        i.putExtra("category", categoryName);
        startActivity(i);
    }

    private void scrollOnClick(QuizAdapter adapter, int pos) {
            if (pos < (adapter.getItemCount() - 1)) {
                binding.quizProgressBar.setProgress(pos+2);
                binding.quizRecycler.smoothScrollToPosition(pos + 1);
                binding.progressBarValue.setText(String.valueOf(pos + 2));
            }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void scrollOnBack(int pos) {
        if (pos > 0) {
            binding.quizProgressBar.setProgress(pos-2);
            binding.quizRecycler.smoothScrollToPosition(pos - 1);
            binding.progressBarValue.setText(String.valueOf(pos - 2));
        }
    }

    @Override
    public void onVariantClick(int questionPosition, int answerPosition) {
        viewModel.onVariantClick(questionPosition, answerPosition);
    }

}