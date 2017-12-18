package com.ojiofong.androidsamples.mvvm.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.mvvm.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojiofong on 12/17/17.
 * .
 */

public class MvvmAdapter extends RecyclerView.Adapter<MvvmAdapter.MvvmViewHolder> {

    private Context context;
    private List<Repo> repoList = new ArrayList<>();

    public MvvmAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Repo> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }

    @Override
    public MvvmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MvvmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MvvmViewHolder holder, int position) {
        holder.textView.setText(repoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    static class MvvmViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MvvmViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvHelloWorld);
        }
    }
}
