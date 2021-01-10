package com.kairat.quizapp.ui.activities.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;

import com.kairat.quizapp.R;
import com.kairat.quizapp.data.models.Question;
import com.kairat.quizapp.databinding.ActivityQuizBinding;
import com.kairat.quizapp.ui.adapters.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        setUpRecyclerView();
        binding.quizBackArrow.setOnClickListener(v -> finish());
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.quizRecycler.setLayoutManager(manager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.quizRecycler);
        List<Question> questions = new ArrayList<>();
        List<String> incorrectAnswers = createIncorrectAnswers("Mahachkala", "Vashington", "Nur-Sultan");
        questions.add(new Question("multiple", "What is the largest city\n and commercial capital of\n Sri Lanka?", "Colombo", incorrectAnswers));
        questions.add(new Question("trueFalse", "Does Idina Menzel sing\n 'let it go' 20 times in \n'Let It Go' from Frozen?", "Yes", "No"));
        incorrectAnswers = createIncorrectAnswers("Madison" ,"Romanov", "Swift");
        questions.add(new Question("multiple", "What is Queen\n Elizabeth II's \n surname?", "Windor", incorrectAnswers));
        questions.add(new Question("trueFalse", "According to Greek \n Mythology, Zeus can \n control lightning.", "Yes", "No"));
        incorrectAnswers = createIncorrectAnswers("Elephant" ,"Tiger", "Shark");
        questions.add(new Question("multiple", "What's the \n biggest animal\n in the world?", "The blue whale", incorrectAnswers));
        questions.add(new Question("trueFalse", "Is The Great Wall of China \n longer than the distance between \n London and Beijing?", "Yes", "No"));
        incorrectAnswers = createIncorrectAnswers("China" ,"India", "Belgium");
        questions.add(new Question("multiple", "Which country\n is brie cheese\n originally from?", "France", incorrectAnswers));
        questions.add(new Question("trueFalse", "Did Alexander Fleming \n discover penicillin?", "Yes", "No"));
        incorrectAnswers = createIncorrectAnswers("Picasso" ,"Aivazovsky", "Donatello");
        questions.add(new Question("multiple", "Who painted \n the Mona Lisa?", "Da Vinci", incorrectAnswers));
        questions.add(new Question("trueFalse", "Is The river Seine in Paris \n longer than the river Thames\n in London?", "Yes", "No"));
        QuizAdapter adapter = new QuizAdapter(questions);
        binding.quizRecycler.setAdapter(adapter);
        binding.skipBtn.setOnClickListener(v -> {
            if (manager.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                binding.quizRecycler.smoothScrollToPosition(manager.findLastCompletelyVisibleItemPosition() + 1);
            }
        });
        ;
    }

    private List<String> createIncorrectAnswers(String var1, String var2, String var3){
        List<String> vars = new ArrayList<>();
        vars.add(var1);
        vars.add(var2);
        vars.add(var3);
        return vars;
    }
}