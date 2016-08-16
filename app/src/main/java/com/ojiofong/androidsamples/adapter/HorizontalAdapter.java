package com.ojiofong.androidsamples.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
    private static final String TAG = HorizontalAdapter.class.getSimpleName();

    String[] items;
    Context context;

    public HorizontalAdapter(Context context, String[] items){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        return R.layout.item_horizontal;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        switch (holder.getItemViewType()){


            case R.layout.item_horizontal:

                holder.tv1.setText(items[position]);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Toast.makeText(context, "pos " + pos, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }


    @Override
    public int getItemCount() {
        return items.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       public TextView tv1;

        public MyViewHolder(View itemView) {
            super(itemView);
            // item
            tv1 = (TextView) itemView.findViewById(R.id.tvHelloWorldX);

        }
    }

}
