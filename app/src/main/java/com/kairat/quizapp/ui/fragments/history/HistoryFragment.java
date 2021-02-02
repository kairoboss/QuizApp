package com.kairat.quizapp.ui.fragments.history;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kairat.quizapp.App;
import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Result;
import com.kairat.quizapp.databinding.HistoryFragmentBinding;
import com.kairat.quizapp.ui.adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private HistoryFragmentBinding binding;
    HistoryAdapter historyAdapter = new HistoryAdapter();


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.historyRecycler.setAdapter(historyAdapter);
        historyAdapter.addList(App.getDataBase().quizDao().getHistoryList());
    }

    @Override
    public void onResume() {
        super.onResume();
        historyAdapter.notifyDataSetChanged();
    }
}