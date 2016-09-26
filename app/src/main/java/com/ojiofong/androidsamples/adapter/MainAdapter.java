package com.ojiofong.androidsamples.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.ui.AnimationActivity;
import com.ojiofong.androidsamples.ui.AsyncTaskLoaderActivity;
import com.ojiofong.androidsamples.ui.BluetoothActivity;
import com.ojiofong.androidsamples.ui.BoundServiceActivity;
import com.ojiofong.androidsamples.ui.ButterKnifeActivity;
import com.ojiofong.androidsamples.ui.ConstraintActivity;
import com.ojiofong.androidsamples.ui.DaggerActivity;
import com.ojiofong.androidsamples.ui.InputDetectionActivity;
import com.ojiofong.androidsamples.ui.RecyclerPlayActivity;
import com.ojiofong.androidsamples.ui.RxJavaActivity;
import com.ojiofong.androidsamples.ui.SensorActivity;
import com.ojiofong.androidsamples.ui.ThreadPoolActivity;
import com.ojiofong.androidsamples.ui.VideoActivity;
import com.ojiofong.androidsamples.ui.WebRTCActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private static final String TAG = MainAdapter.class.getSimpleName();

    String[] items;
    Context context;

    public MainAdapter(Context context, String[] items){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
       // return super.getItemViewType(position);
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
                launchActivity(pos);
                //Toast.makeText(context, "pos " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       public TextView tv1;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tvHelloWorld);
        }
    }


    private void launchActivity(int pos){
        switch (pos){
            case 0:
                context.startActivity(new Intent(context, SensorActivity.class));
                break;
            case 1:
                context.startActivity(new Intent(context, RxJavaActivity.class));
                break;
            case 2:
                context.startActivity(new Intent(context, VideoActivity.class));
                break;
            case 3:
                context.startActivity(new Intent(context, ConstraintActivity.class));
                break;
            case 4:
                context.startActivity(new Intent(context, RecyclerPlayActivity.class));
                break;
            case 5:
                context.startActivity(new Intent(context, AnimationActivity.class));
                break;
            case 6:
                context.startActivity(new Intent(context, DaggerActivity.class));
                break;
            case 7:
                context.startActivity(new Intent(context, ButterKnifeActivity.class));
                break;
            case 8:
                context.startActivity(new Intent(context, BoundServiceActivity.class));
                break;
            case 9:
                context.startActivity(new Intent(context, BluetoothActivity.class));
                break;
            case 10:
                context.startActivity(new Intent(context, WebRTCActivity.class));
                break;
            case 11:
                context.startActivity(new Intent(context, ThreadPoolActivity.class));
                break;
            case 12:
                context.startActivity(new Intent(context, AsyncTaskLoaderActivity.class));
                break;
            case 13:
                context.startActivity(new Intent(context, InputDetectionActivity.class));
                break;
        }
    }
}
