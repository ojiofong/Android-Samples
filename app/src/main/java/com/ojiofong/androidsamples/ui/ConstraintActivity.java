package com.ojiofong.androidsamples.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.ojiofong.androidsamples.R;

public class ConstraintActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        setupWindowAnimationFade();
    }

    private void setupWindowAnimationFade() {
//        Fade fade = new Fade();
//        fade.setDuration(1000);
//        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(300);
        slide.setSlideEdge(Gravity.END);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        //getWindow().setReturnTransition(slide);
        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(slide);
    }

    public void doSomething(View v){
        String url = "http://ojiofong.com/android/arounda/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}
