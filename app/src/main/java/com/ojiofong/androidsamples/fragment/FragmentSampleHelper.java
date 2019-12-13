package com.ojiofong.androidsamples.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 4/30/17.
 * Just contains Fragments used by FragmentSampleActivity
 */

public class FragmentSampleHelper {

    /**
     * Fragment class
     */
    public static class MFragment extends Fragment {
        public static String TAG = MFragment.class.getSimpleName();
        private ICallBack callBack;

        public interface ICallBack {
            void doSomething();
        }

        public MFragment() {
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            callBack = (ICallBack) getActivity();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_sensor, container, false);
            rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.doSomething();
                }
            });
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
