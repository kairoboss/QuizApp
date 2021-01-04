package com.kairat.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> nameData = new MutableLiveData<>();

    public void getName(){
        nameData.setValue("Victory");
    }
}
