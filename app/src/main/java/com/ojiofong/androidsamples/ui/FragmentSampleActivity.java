package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class FragmentSampleActivity extends AppCompatActivity {

    private static final String TAG = FragmentSampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentsample);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_fragment) {
            setupFragment();
        } else if (view.getId() == R.id.button_dialog_fragment) {
            setupDialogFragment();
        }
    }

    private void setupFragment() {
        MFragment frag = new MFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, MFragment.TAG).commit();
    }

    private void setupDialogFragment() {
        MDialogFragment dialogFrag = new MDialogFragment();
        dialogFrag.show(getSupportFragmentManager(), MDialogFragment.TAG);
    }

    /**
     * Fragment class
     */
    public static class MFragment extends Fragment {
        public static String TAG = MFragment.class.getSimpleName();

        public MFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_sensor, container, false);
            rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            return rootView;
        }
    }

    public static class MDialogFragment extends DialogFragment {
        public static String TAG = MDialogFragment.class.getSimpleName();

        public MDialogFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_sensor, container, false);
            return rootView;
        }
    }
}
