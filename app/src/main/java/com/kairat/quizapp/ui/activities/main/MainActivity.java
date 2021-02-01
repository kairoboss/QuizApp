package com.kairat.quizapp.ui.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kairat.quizapp.R;
import com.kairat.quizapp.databinding.ActivityMainBinding;
import com.kairat.quizapp.ui.adapters.MainPagerAdapter;
import com.kairat.quizapp.ui.viewpager.MainViewPager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        viewModel = new MainViewModel();
        binding.bottomNavView.setOnNavigationItemSelectedListener(this);
        binding.mainPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        binding.mainPager.setOffscreenPageLimit(3);
        binding.mainPager.setPagingEnabled(false);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_nav:
                binding.mainPager.setCurrentItem(0);
                binding.toolBar.setTitle(R.string.quiz);
                break;
            case R.id.history_nav:
                binding.mainPager.setCurrentItem(1);
                binding.toolBar.setTitle(R.string.history);
                break;
            case R.id.settings_nav:
                binding.mainPager.setCurrentItem(2);
                binding.toolBar.setTitle(R.string.settings);
                break;
        }
        return true;
    }

}