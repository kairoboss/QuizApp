package com.kairat.quizapp.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.databinding.QuizItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Question> questions = new ArrayList<>();
    private OnClicks onClicks;

    public QuizAdapter(OnClicks onClicks) {
        this.onClicks = onClicks;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.quiz_item, parent, false);
        return new QuizViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.setDefaultViewHolderState(questions.get(position));
        holder.bind(questions.get(position));
    }

    public void addList(List<Question> questions) {
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    class QuizViewHolder extends RecyclerView.ViewHolder {
        private QuizItemBinding binding;

        public QuizViewHolder(@NonNull QuizItemBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.binding = itemViewBinding;
        }

        private void bind(Question q) {
            binding.setQuestion(q);
            if (q.getType().equals("multiple")) {
                binding.linearTrueFalse.setVisibility(View.GONE);
                binding.linearMultiple.setVisibility(View.VISIBLE);
            } else {
                binding.linearMultiple.setVisibility(View.GONE);
                binding.linearTrueFalse.setVisibility(View.VISIBLE);
            }

            binding.firstVar.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.firstVar, binding.getRoot().getContext(), 0);
                binding.secondVar.setEnabled(false);
                binding.thirdVar.setEnabled(false);
                binding.fourthVar.setEnabled(false);
                binding.btnTrue.setEnabled(false);
                binding.btnFalse.setEnabled(false);
            });
            binding.secondVar.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.secondVar, binding.getRoot().getContext(), 1);
                binding.firstVar.setEnabled(false);
                binding.thirdVar.setEnabled(false);
                binding.fourthVar.setEnabled(false);
                binding.btnTrue.setEnabled(false);
                binding.btnFalse.setEnabled(false);
            });
            binding.thirdVar.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.thirdVar, binding.getRoot().getContext(), 2);
                binding.firstVar.setEnabled(false);
                binding.secondVar.setEnabled(false);
                binding.fourthVar.setEnabled(false);
                binding.btnTrue.setEnabled(false);
                binding.btnFalse.setEnabled(false);
            });
            binding.fourthVar.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.fourthVar, binding.getRoot().getContext(), 3);
                binding.firstVar.setEnabled(false);
                binding.secondVar.setEnabled(false);
                binding.thirdVar.setEnabled(false);
                binding.btnTrue.setEnabled(false);
                binding.btnFalse.setEnabled(false);
            });
            binding.btnTrue.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.btnTrue, binding.getRoot().getContext(), 0);
                binding.btnFalse.setEnabled(false);
            });
            binding.btnFalse.setOnClickListener(v -> {
                compareCorrectAnswer(q, binding.btnFalse, binding.getRoot().getContext(), 1);
                binding.btnTrue.setEnabled(false);
            });
        }

        public void compareCorrectAnswer(Question question, Button button, Context context, int answerPosition) {
            if (button.getText().equals(question.getCorrectAnswer())) {
                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue)));
                    }

                    public void onFinish() {
                        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                        onClicks.onVariantClick(getAdapterPosition(), answerPosition);
                    }

                }.start();
            } else {
                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue)));
                    }

                    public void onFinish() {
                        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                        onClicks.onVariantClick(getAdapterPosition(), answerPosition);
                    }

                }.start();
            }
        }

        public void setDefaultViewHolderState(Question question) {
            if (question.isAnswerClicked()) {
                checkClickedAnswer(question, binding.getRoot().getContext());
            } else {
                binding.firstVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.firstVar.setEnabled(true);
                binding.secondVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.secondVar.setEnabled(true);
                binding.thirdVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.thirdVar.setEnabled(true);
                binding.fourthVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.fourthVar.setEnabled(true);
                binding.btnTrue.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.btnTrue.setEnabled(true);
                binding.btnFalse.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white)));
                binding.btnFalse.setEnabled(true);
            }
        }

        private void checkClickedAnswer(Question question, Context context) {
            switch (question.getSelectedPosition()) {
                case (0):
                    if (question.getCorrectAnswer().equals(binding.firstVar.getText())) {
                        binding.firstVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                        binding.btnTrue.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                    } else{
                        binding.firstVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                        binding.btnTrue.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                    }
                    binding.firstVar.setEnabled(false);
                    binding.btnTrue.setEnabled(false);
                    break;
                case (1):
                    if (question.getCorrectAnswer().equals(binding.secondVar.getText())) {
                        binding.secondVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                        binding.btnFalse.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                    } else{
                        binding.secondVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                        binding.btnFalse.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));

                    }
                    binding.secondVar.setEnabled(false);
                    binding.btnFalse.setEnabled(false);
                    break;
                case (2):
                    if (question.getCorrectAnswer().equals(binding.thirdVar.getText())) {
                        binding.thirdVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                    } else {
                        binding.thirdVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                    }
                    binding.thirdVar.setEnabled(false);
                    break;
                case (3):
                    if (question.getCorrectAnswer().equals(binding.fourthVar.getText())) {
                        binding.fourthVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green)));
                    } else {
                        binding.fourthVar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red)));
                    }
                    binding.fourthVar.setEnabled(false);
                    break;


            }

        }


    }

    public interface OnClicks {
        void onVariantClick(int questionPosition, int answerPosition);
    }

}
