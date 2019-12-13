package com.ojiofong.androidsamples.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ojiofong.androidsamples.R;

public class VideoActivity extends AppCompatActivity {
    private static final String TAG = VideoActivity.class.getSimpleName();

    VideoView videoView;

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null) {
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null) {
            //videoView.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setTitle(TAG);
        setUpVideoView();
    }

    private void setUpVideoView() {
        videoView = (VideoView) findViewById(R.id.videoView);
        if (videoView != null) {
           // String url = "https://youtu.be/sU7LmmyQHRc";
            String url = "http://techslides.com/demos/sample-videos/small.mp4";

            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(Uri.parse(url));
            videoView.seekTo(100);
            videoView.start();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("key", videoView.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore video position in orientation change
        if (savedInstanceState!=null){
            int pos = savedInstanceState.getInt("key");
            videoView.seekTo(pos);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
