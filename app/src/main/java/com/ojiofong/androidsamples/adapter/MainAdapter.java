package com.ojiofong.androidsamples.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.bottomsheet.ui.BottomSheetActivity;
import com.ojiofong.androidsamples.dagger.ui.DaggerActivity;
import com.ojiofong.androidsamples.koin.ui.KoinMainFragment;
import com.ojiofong.androidsamples.mvvm.ui.MVVMActivity;
import com.ojiofong.androidsamples.navigation.NavigationActivity;
import com.ojiofong.androidsamples.notification.NotificationFragment;
import com.ojiofong.androidsamples.paging.ui.PagingListActivity;
import com.ojiofong.androidsamples.room.ui.RoomActivity;
import com.ojiofong.androidsamples.threadpool.ThreadPoolActivity;
import com.ojiofong.androidsamples.threadpool.ThreadPoolActivityFuture;
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
import com.ojiofong.androidsamples.ui.VideoActivity;
import com.ojiofong.androidsamples.ui.ViewPagerActivity;
import com.ojiofong.androidsamples.ui.WebRTCActivity;
import com.ojiofong.androidsamples.workmanager.ui.WorkManagerActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private static final String TAG = MainAdapter.class.getSimpleName();

    private String[] items;
    private AppCompatActivity activity;

    public MainAdapter(AppCompatActivity activity, String[] items) {
        this.items = items;
        this.activity = activity;
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

        if (title.equals(this.activity.getString(R.string.sensor))) {
            this.activity.startActivity(new Intent(this.activity, SensorActivity.class));
        } else if (title.equals(this.activity.getString(R.string.rx_java))) {
            this.activity.startActivity(new Intent(this.activity, RxJavaActivity.class));
        } else if (title.equals(this.activity.getString(R.string.video))) {
            this.activity.startActivity(new Intent(this.activity, VideoActivity.class));
        } else if (title.equals(this.activity.getString(R.string.constraint_layout))) {
            this.activity.startActivity(new Intent(this.activity, ConstraintActivity.class));
        } else if (title.equals(this.activity.getString(R.string.recycler_play))) {
            this.activity.startActivity(new Intent(this.activity, RecyclerPlayActivity.class));
        } else if (title.equals(this.activity.getString(R.string.animation))) {
            this.activity.startActivity(new Intent(this.activity, AnimationActivity.class));
        } else if (title.equals(this.activity.getString(R.string.dagger))) {
            this.activity.startActivity(new Intent(this.activity, DaggerActivity.class));
        } else if (title.equals(this.activity.getString(R.string.butter_knife))) {
            this.activity.startActivity(new Intent(this.activity, ButterKnifeActivity.class));
        } else if (title.equals(this.activity.getString(R.string.bound_service))) {
            this.activity.startActivity(new Intent(this.activity, BoundServiceActivity.class));
        } else if (title.equals(this.activity.getString(R.string.bluetooth))) {
            this.activity.startActivity(new Intent(this.activity, BluetoothActivity.class));
        } else if (title.equals(this.activity.getString(R.string.web_rtc))) {
            this.activity.startActivity(new Intent(this.activity, WebRTCActivity.class));
        } else if (title.equals(this.activity.getString(R.string.thread_pool_executor))) {
            this.activity.startActivity(new Intent(this.activity, ThreadPoolActivity.class));
        } else if (title.equals(this.activity.getString(R.string.async_task_loader))) {
            this.activity.startActivity(new Intent(this.activity, AsyncTaskLoaderActivity.class));
        } else if (title.equals(this.activity.getString(R.string.input_detection))) {
            this.activity.startActivity(new Intent(this.activity, InputDetectionActivity.class));
        } else if (title.equals(this.activity.getString(R.string.retain_async_task))) {
            this.activity.startActivity(new Intent(this.activity, RetainAsyncTaskActivity.class));
        } else if (title.equals(this.activity.getString(R.string.view_pager))) {
            this.activity.startActivity(new Intent(this.activity, ViewPagerActivity.class));
        } else if (title.equals(this.activity.getString(R.string.mvp))) {
            this.activity.startActivity(new Intent(this.activity, MVPActivity.class));
        } else if (title.equals(this.activity.getString(R.string.fragment_dialog_fragment))) {
            this.activity.startActivity(new Intent(this.activity, FragmentSampleActivity.class));
        } else if (title.equals(this.activity.getString(R.string.mvvm_view_model_live_data))) {
            this.activity.startActivity(new Intent(this.activity, MVVMActivity.class));
        } else if (title.equals(this.activity.getString(R.string.mvvm_room_live_data))) {
            this.activity.startActivity(new Intent(this.activity, RoomActivity.class));
        } else if (title.equals(this.activity.getString(R.string.thread_pool_executor_future))) {
            this.activity.startActivity(new Intent(this.activity, ThreadPoolActivityFuture.class));
        } else if (title.equals(this.activity.getString(R.string.bottom_sheet_fragment))) {
            this.activity.startActivity(new Intent(this.activity, BottomSheetActivity.class));
        } else if (title.equals(this.activity.getString(R.string.work_manager))) {
            this.activity.startActivity(new Intent(this.activity, WorkManagerActivity.class));
        } else if (title.equals(this.activity.getString(R.string.paging_list))) {
            this.activity.startActivity(new Intent(this.activity, PagingListActivity.class));
        } else if (title.equals(this.activity.getString(R.string.koin))) {
            KoinMainFragment.Companion.launch(activity);
        } else if (title.equals(this.activity.getString(R.string.navigation_component))) {
            this.activity.startActivity(new Intent(this.activity, NavigationActivity.class));
        }else if (title.equals(this.activity.getString(R.string.notification))) {
            NotificationFragment.launch(activity.getSupportFragmentManager());
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
