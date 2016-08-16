package com.ojiofong.androidsamples.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = AnimationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setupWindowAnimationFade();
    }

    public void onClickSlideIn(View view){
        Toast.makeText(this, "onClickSlideIn", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ConstraintActivity.class));

    }


    public void onClickSlideOut(View view){
        Toast.makeText(this, "onClickSlideOut", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ConstraintActivity.class));
    }

    private void setupWindowAnimationFade() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
}
