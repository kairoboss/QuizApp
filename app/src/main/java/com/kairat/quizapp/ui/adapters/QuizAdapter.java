package com.kairat.quizapp.ui.adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.databinding.QuizItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Question> questions = new ArrayList<>();

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.quiz_item, parent, false);
        return new QuizViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.bind(questions.get(position));
    }

    public void addList(List<Question> questions){
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class QuizViewHolder extends RecyclerView.ViewHolder{
        private QuizItemBinding binding;
        public QuizViewHolder(@NonNull QuizItemBinding itemViewBinding){
            super(itemViewBinding.getRoot());
            this.binding = itemViewBinding;
        }
        private void bind(Question q){
            binding.setQuestion(q);
            if (q.getType().equals("multiple")){
                binding.linearTrueFalse.setVisibility(View.GONE);
                binding.linearMultiple.setVisibility(View.VISIBLE);
            }else{
                binding.linearMultiple.setVisibility(View.GONE);
                binding.linearTrueFalse.setVisibility(View.VISIBLE);
            }
        }
    }


}
