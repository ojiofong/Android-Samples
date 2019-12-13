package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.fragment.FragmentSampleHelper;

/**
 *
 */
public class FragmentSampleActivity extends AppCompatActivity implements FragmentSampleHelper.MFragment.ICallBack {

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
        FragmentSampleHelper.MFragment frag = new FragmentSampleHelper.MFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, FragmentSampleHelper.MFragment.TAG).commit();
    }

    private void setupDialogFragment() {
        FragmentSampleHelper.MDialogFragment dialogFrag = new FragmentSampleHelper.MDialogFragment();
        dialogFrag.show(getSupportFragmentManager(), FragmentSampleHelper.MDialogFragment.TAG);
    }

    @Override
    public void doSomething() {
        Toast.makeText(this, "doSomething", Toast.LENGTH_SHORT).show();
    }
}
