package com.ojiofong.androidsamples.bottomsheet.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 3/9/18.
 */

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    private static String TAG = MyBottomSheetFragment.class.getSimpleName();
    private BottomSheetBehavior behavior;

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            Log.d(TAG, "BottomSheet newState-> " + newState);
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                setLayout(R.layout.fragment_bottomsheet_expanded, newState);
            } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                setLayout(R.layout.fragment_bottomsheet, newState);
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };


    public static void showWith(AppCompatActivity activity) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        new MyBottomSheetFragment().show(transaction, TAG);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View rootView = View.inflate(getContext(), R.layout.fragment_bottomsheet, null);
        dialog.setContentView(rootView);
        behavior = BottomSheetBehavior.from((View) rootView.getParent());
        if (behavior != null) {
            int heightInPixels = getActivity().getResources().getDimensionPixelSize(R.dimen.bottom_sheet_peek_height);
            behavior.setPeekHeight(heightInPixels);
            behavior.setBottomSheetCallback(bottomSheetCallback);
        }
    }

    private void setLayout(int layoutResource, int state) {
        View rootView = View.inflate(getContext(), layoutResource, null);
        getDialog().setContentView(rootView);
        behavior = BottomSheetBehavior.from((View) rootView.getParent());
        if (behavior != null) {
            int heightInPixels = getActivity().getResources().getDimensionPixelSize(R.dimen.bottom_sheet_peek_height);
            behavior.setPeekHeight(heightInPixels);
            behavior.setBottomSheetCallback(bottomSheetCallback);
            behavior.setState(state);
        }
    }
}
