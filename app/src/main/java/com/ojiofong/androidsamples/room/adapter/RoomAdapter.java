package com.ojiofong.androidsamples.room.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.room.model.DbModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MvvmViewHolder> {

    private Context context;
    private List<DbModel> data = new ArrayList<>();

    public RoomAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DbModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public MvvmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MvvmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MvvmViewHolder holder, int position) {
        DbModel dbModel = data.get(position);
        String s = dbModel.getName() + "\n" + dbModel.getDescription() + "\n" + dbModel.getId();
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MvvmViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MvvmViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvHelloWorld);
        }
    }
}
