package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;

import com.ojiofong.androidsamples.R;

public class ConstraintActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        setupWindowAnimationFade();
    }

    private void setupWindowAnimationFade() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);


        Slide slide = new Slide();
        fade.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }

}
