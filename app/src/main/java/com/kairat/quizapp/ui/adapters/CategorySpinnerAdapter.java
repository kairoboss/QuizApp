package com.kairat.quizapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.TriviaCategory;
import com.kairat.quizapp.databinding.SpinnerItemBinding;

import java.util.List;

public class CategorySpinnerAdapter extends RecyclerView.Adapter<CategorySpinnerAdapter.CategoryVH> {

    private List<TriviaCategory> categoriesList;

    public CategorySpinnerAdapter(List<TriviaCategory> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpinnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.spinner_item, parent, false);
        return new CategoryVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        holder.bind(categoriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class CategoryVH extends RecyclerView.ViewHolder {
        SpinnerItemBinding binding;
        public CategoryVH(@NonNull SpinnerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(TriviaCategory category){
            binding.setCategory(category);
        }
    }
}
