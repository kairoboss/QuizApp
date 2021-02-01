package com.kairat.quizapp.ui.fragments.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;

import com.kairat.quizapp.R;
import com.kairat.quizapp.databinding.MainFragmentBinding;
import com.kairat.quizapp.ui.activities.quiz.QuizActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private MainFragmentViewModel mViewModel;
    private MainFragmentBinding binding;
    private int categoryId;
    private String difficulty;
    private String categoryName;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getCategories();
        mViewModel.categoriesNames.observe(this, list -> {
            ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
            binding.categorySpinner.setAdapter(adapter);
        });
        mViewModel.categoriesIds.observe(this, integers -> {
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.questionsAmountSeekBar.setOnSeekBarChangeListener(this);
        binding.questionsAmountValue.setText("0");
        binding.startButton.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), QuizActivity.class);
            i.putExtra("amount", Integer.valueOf(binding.questionsAmountValue.getText().toString()));
            i.putExtra("categoryId", categoryId);
            i.putExtra("difficulty", difficulty);
            i.putExtra("categoryName", categoryName);
            startActivity(i);
        });
        spinners();
    }

    private void spinners() {
        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryId = position + 9;
                categoryName = binding.categorySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (binding.difficultySpinner.getSelectedItem().toString().equals("Any difficulty")) {
                    difficulty = "";
                } else {
                    difficulty = binding.difficultySpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        binding.questionsAmountValue.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}