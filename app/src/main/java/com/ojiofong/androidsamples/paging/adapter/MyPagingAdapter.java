package com.ojiofong.androidsamples.paging.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.paging.repository.db.RepoDbModel;

/**
 * Created by ojiofong on 6/5/18.
 */

public class MyPagingAdapter extends ListAdapter<RepoDbModel, MyPagingAdapter.PagingViewHolder> {

    public MyPagingAdapter(@NonNull DiffUtil.ItemCallback<RepoDbModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PagingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_view_item, parent, false);
        return new PagingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PagingViewHolder holder, int position) {
        RepoDbModel repoItem = getItem(position);
        holder.tvName.setText(repoItem.getName());
        holder.tvDesc.setText(repoItem.getDescription());
        holder.tvStars.setText("" + repoItem.getStars());
    }

    public static class PagingViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc, tvStars;

        public PagingViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.repo_name);
            tvDesc = (TextView) itemView.findViewById(R.id.repo_description);
            tvStars = (TextView) itemView.findViewById(R.id.repo_stars);
        }
    }
}
