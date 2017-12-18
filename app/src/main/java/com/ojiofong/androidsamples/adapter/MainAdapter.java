package com.ojiofong.androidsamples.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.dagger.ui.DaggerActivity;
import com.ojiofong.androidsamples.mvvm.ui.MVVMActivity;
import com.ojiofong.androidsamples.ui.AnimationActivity;
import com.ojiofong.androidsamples.ui.AsyncTaskLoaderActivity;
import com.ojiofong.androidsamples.ui.BluetoothActivity;
import com.ojiofong.androidsamples.ui.BoundServiceActivity;
import com.ojiofong.androidsamples.ui.ButterKnifeActivity;
import com.ojiofong.androidsamples.ui.ConstraintActivity;
import com.ojiofong.androidsamples.ui.FragmentSampleActivity;
import com.ojiofong.androidsamples.ui.InputDetectionActivity;
import com.ojiofong.androidsamples.ui.MVPActivity;
import com.ojiofong.androidsamples.ui.RecyclerPlayActivity;
import com.ojiofong.androidsamples.ui.RetainAsyncTaskActivity;
import com.ojiofong.androidsamples.ui.RxJavaActivity;
import com.ojiofong.androidsamples.ui.SensorActivity;
import com.ojiofong.androidsamples.ui.ThreadPoolActivity;
import com.ojiofong.androidsamples.ui.VideoActivity;
import com.ojiofong.androidsamples.ui.ViewPagerActivity;
import com.ojiofong.androidsamples.ui.WebRTCActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private static final String TAG = MainAdapter.class.getSimpleName();

    private String[] items;
    private Context context;

    public MainAdapter(Context context, String[] items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv1.setText(items[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                launchActivity(items[pos]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    private void launchActivity(String title) {

        if (title.equals(context.getString(R.string.sensor))) {
            context.startActivity(new Intent(context, SensorActivity.class));
        } else if (title.equals(context.getString(R.string.rx_java))) {
            context.startActivity(new Intent(context, RxJavaActivity.class));
        } else if (title.equals(context.getString(R.string.video))) {
            context.startActivity(new Intent(context, VideoActivity.class));
        } else if (title.equals(context.getString(R.string.constraint_layout))) {
            context.startActivity(new Intent(context, ConstraintActivity.class));
        } else if (title.equals(context.getString(R.string.recycler_play))) {
            context.startActivity(new Intent(context, RecyclerPlayActivity.class));
        } else if (title.equals(context.getString(R.string.animation))) {
            context.startActivity(new Intent(context, AnimationActivity.class));
        } else if (title.equals(context.getString(R.string.dagger))) {
            context.startActivity(new Intent(context, DaggerActivity.class));
        } else if (title.equals(context.getString(R.string.butter_knife))) {
            context.startActivity(new Intent(context, ButterKnifeActivity.class));
        } else if (title.equals(context.getString(R.string.bound_service))) {
            context.startActivity(new Intent(context, BoundServiceActivity.class));
        } else if (title.equals(context.getString(R.string.bluetooth))) {
            context.startActivity(new Intent(context, BluetoothActivity.class));
        } else if (title.equals(context.getString(R.string.web_rtc))) {
            context.startActivity(new Intent(context, WebRTCActivity.class));
        } else if (title.equals(context.getString(R.string.thread_pool_executor))) {
            context.startActivity(new Intent(context, ThreadPoolActivity.class));
        } else if (title.equals(context.getString(R.string.async_task_loader))) {
            context.startActivity(new Intent(context, AsyncTaskLoaderActivity.class));
        } else if (title.equals(context.getString(R.string.input_detection))) {
            context.startActivity(new Intent(context, InputDetectionActivity.class));
        } else if (title.equals(context.getString(R.string.retain_async_task))) {
            context.startActivity(new Intent(context, RetainAsyncTaskActivity.class));
        } else if (title.equals(context.getString(R.string.view_pager))) {
            context.startActivity(new Intent(context, ViewPagerActivity.class));
        } else if (title.equals(context.getString(R.string.mvp))) {
            context.startActivity(new Intent(context, MVPActivity.class));
        } else if (title.equals(context.getString(R.string.fragment_dialog_fragment))) {
            context.startActivity(new Intent(context, FragmentSampleActivity.class));
        } else if (title.equals(context.getString(R.string.mvvm_view_model_live_data))) {
            context.startActivity(new Intent(context, MVVMActivity.class));
        }

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;

        MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tvHelloWorld);
        }
    }
}
