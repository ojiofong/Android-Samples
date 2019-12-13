package com.ojiofong.androidsamples.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;

public class AnimationActivity extends AppCompatActivity {
    private static final String TAG = AnimationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setupWindowAnimation();
    }


    public void onClickSlideOut(View view) {
       // Toast.makeText(this, "onClickSlideOut", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ConstraintActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void onClickSlideIn(View view) {
       // Toast.makeText(this, "onClickSlideIn", Toast.LENGTH_SHORT).show();
         //startActivity(new Intent(this, ConstraintActivity.class));
        startActivitySharedStatusNav();
    }


    private void setupWindowAnimation() {
        Slide slide = new Slide();
        slide.setDuration(200);
        slide.setSlideEdge(Gravity.START);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
       // getWindow().setExitTransition(slide);
       // getWindow().setEnterTransition(slide);
       // getWindow().setReenterTransition(slide);



        Fade fade = new Fade();
        fade.setDuration(300);
        // getWindow().setExitTransition(fade);
        // getWindow().setEnterTransition(fade);
         getWindow().setReenterTransition(fade);
    }

    private void startActivitySharedStatusNav(){

        Intent intent = new Intent(this, ConstraintActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            View imageView = findViewById(R.id.iv);
//            Resources resources = getResources();
//            imageView.setTransitionName(resources.getString(R.string.transition_image_thumbnail));

//            Pair<View, String> p1 = Pair.create(imageView, resources.getString(R.string.transition_image_thumbnail));

//            Window window = getWindow();

            View navigationBar = findViewById(R.id.toolbar);
            View statusBar = findViewById(android.R.id.statusBarBackground);

            Pair<View, String> p2 = Pair.create(statusBar, "statusbar");
            Pair<View, String> p3 = Pair.create(navigationBar, "navbar");

            if (statusBar==null){
                Toast.makeText(this, "statusBar is null", Toast.LENGTH_SHORT).show();
                return;
            }else if (navigationBar==null){
                Toast.makeText(this, "navigationBar is null", Toast.LENGTH_SHORT).show();
                return;
            }

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p2, p3);

            ActivityCompat.startActivity(this, intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}
