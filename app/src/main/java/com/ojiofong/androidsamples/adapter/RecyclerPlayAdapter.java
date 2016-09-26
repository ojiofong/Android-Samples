package com.ojiofong.androidsamples.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.ui.RecyclerPlayActivity;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerPlayAdapter extends SelectableAdapter<RecyclerPlayAdapter.MyViewHolder> {
    private static final String TAG = RecyclerPlayAdapter.class.getSimpleName();

    String[] testItems = { "horizontal 0", "horizontal 1", "horizontal 2", "horizontal 3", "horizontal 4"};
    private List<String> items = new ArrayList<>();
    private Context context;
    private RecyclerPlayActivity recyclerPlayActivity;
    private HorizontalAdapter horizontalAdapter;


    public RecyclerPlayAdapter(RecyclerPlayActivity recyclerPlayActivity, List<String> items) {
        this.items = items;
        this.recyclerPlayActivity = recyclerPlayActivity;
        this.context = recyclerPlayActivity;
        horizontalAdapter = new HorizontalAdapter(recyclerPlayActivity, testItems);
    }

    @Override
    public int getItemViewType(int position) {
        // return super.getItemViewType(position);
        if (position == 3) return R.layout.item_recyclerview_x;

        return R.layout.item;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerPlayActivity.onClickItemView(view);
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                recyclerPlayActivity.onLongClickItemView(view);
                return true;
            }
        });

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {

            case R.layout.item_recyclerview_x:
                // set up recyclerView
                //holder.mRecyclerView.setAdapter(horizontalAdapter);
                horizontalAdapter.notifyDataSetChanged();
                break;

            case R.layout.item:

                holder.tv1.setText(items.get(position));

                holder.itemView.setBackgroundColor(isSelected(position) ? Color.GRAY : Color.TRANSPARENT);

                break;
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItemAtPosition(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void insertItemAtPosition(int position) {
        items.add(position, "new item " + position);
        notifyItemInserted(position);
    }

    public void changeItemAtPosition(int position) {
        items.set(position, "changed item " + position);
        notifyItemChanged(position);
    }

    public void deleteSelectedPositions() {
        List<Integer> selected = getSelectedItems();
        for (int i = selected.size() - 1; i >= 0; i--) {
            String str = items.get(selected.get(i));
            items.remove(str);
            notifyItemRemoved(selected.get(i));
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        RecyclerView mRecyclerView;

        MyViewHolder(View itemView) {
            super(itemView);
            // item
            tv1 = (TextView) itemView.findViewById(R.id.tvHelloWorld);

            // item Horizontal
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerViewX);
            if (mRecyclerView != null) {
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.addItemDecoration(
                        new VerticalDividerItemDecoration.Builder(context)
                                .color(Color.GRAY)
                                .sizeResId(R.dimen.activity_vertical_margin)
                                .marginResId(R.dimen.activity_vertical_margin, R.dimen.activity_vertical_margin)
                                .build());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(horizontalAdapter);
            }

        }
    }


}
