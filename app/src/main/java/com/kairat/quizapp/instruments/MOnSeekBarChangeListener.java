package com.kairat.quizapp.instruments;

import android.widget.SeekBar;

public abstract interface MOnSeekBarChangeListener extends SeekBar.OnSeekBarChangeListener {

    @Override
    void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

    @Override
    void onStartTrackingTouch(SeekBar seekBar);

    @Override
    void onStopTrackingTouch(SeekBar seekBar);
}
