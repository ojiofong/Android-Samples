package com.ojiofong.androidsamples.bottomsheet.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 3/9/18.
 */

public class MyBottomSheetFragment extends BottomSheetDialogFragment {
    private static String TAG = MyBottomSheetFragment.class.getSimpleName();

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            Log.d(TAG, "BottomSheet newState-> " + newState);
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
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
        BottomSheetBehavior behavior = BottomSheetBehavior.from((View) rootView.getParent());
        if (behavior != null) {
            int heightInPixels = getActivity().getResources().getDimensionPixelSize(R.dimen.bottom_sheet_peek_height);
            behavior.setPeekHeight(heightInPixels);
            behavior.setBottomSheetCallback(bottomSheetCallback);
        }
    }
}
