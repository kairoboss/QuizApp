package com.kairat.quizapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Result;
import com.kairat.quizapp.databinding.HistoryItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<Result> results = new ArrayList<>();
    private OnHistoryClick onHistoryClick;

    public HistoryAdapter(OnHistoryClick onHistoryClick) {
        this.onHistoryClick = onHistoryClick;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.history_item, parent, false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void addList(List<Result> results){
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public Result getItem(int pos) {
        return results.get(pos);
    }

    public void deleteItem(int pos) {
        results.remove(pos);
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        HistoryItemBinding binding;
        public HistoryViewHolder(@NonNull HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void bind(Result result){
            binding.correctAnswersValue.setText(result.getCorrectAnswers()+"/"+result.getAmount());
            binding.cardCategoryName.setText(result.getCategory());
            binding.cardDifficultyValue.setText(result.getDifficulty());
            Date date = new Date(result.getCreatedAt());
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:ss");
            binding.createdAt.setText(df.format(date));
            binding.deleteItem.setOnClickListener(v -> onHistoryClick.onDeleteClick(getAdapterPosition()));
        }
    }

    public interface OnHistoryClick{
        void onDeleteClick(int pos);
    }
}
