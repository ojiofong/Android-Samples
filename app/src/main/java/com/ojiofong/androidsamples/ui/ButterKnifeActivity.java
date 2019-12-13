package com.ojiofong.androidsamples.ui;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends AppCompatActivity {


    @BindView(R.id.tvHelloWorld) TextView mTextView;
    @BindView(R.id.ivPointer) ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);

        mImageView.setImageResource(R.mipmap.ic_launcher);

        mTextView.setTextSize(40f);
        mTextView.setTextColor(Color.GREEN);
        mTextView.setText("Butter Knife");

//        mTextView.setOnClickListener((View v) -> {
//            // do something here
//        });

    }

    @OnClick(R.id.ivPointer) void onClickImage(){
        Toast.makeText(this, "clicked image", Toast.LENGTH_SHORT).show();
    }



}
