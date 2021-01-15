package com.kairat.quizapp.ui.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DifficultySpinnerAdapter extends RecyclerView.Adapter<DifficultySpinnerAdapter.DifficultyVH> {

    @NonNull
    @Override
    public DifficultyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DifficultyVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DifficultyVH extends RecyclerView.ViewHolder {
        public DifficultyVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
