package com.kairat.quizapp.ui.activities.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.databinding.ActivityQuizBinding;
import com.kairat.quizapp.ui.activities.result.ResultActivity;
import com.kairat.quizapp.ui.adapters.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private QuizViewModel viewModel;
    private int amount;
    private int category;
    private String difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.quizBackArrow.setOnClickListener(v -> finish());
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.setViewModel(viewModel);
        Intent intent = getIntent();
        amount = getIntent().getIntExtra("amount", 0);
        category = getIntent().getIntExtra("categoryId", 0);
        difficulty = getIntent().getStringExtra("difficulty");
        viewModel.getQuestions(amount, category, difficulty.toLowerCase());
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        QuizAdapter adapter = new QuizAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.quizRecycler.setLayoutManager(manager);
        binding.quizRecycler.setAdapter(adapter);
        viewModel.questionsLiveData.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questionsData) {
                adapter.addList(questionsData);

            }
        });
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.quizRecycler);
        binding.skipBtn.setOnClickListener(v -> {
            if (manager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                binding.quizRecycler.smoothScrollToPosition(manager.findLastCompletelyVisibleItemPosition() + 1);
            }
        });

    }

}