package com.sunzn.mark.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunzn.mark.library.MarkTextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<String> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MarkTextView markTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            markTextView = itemView.findViewById(R.id.mtv);
        }

        public MarkTextView getMarkTextView() {
            return markTextView;
        }

    }

    public CustomAdapter(ArrayList<String> dataSet) {
        this.mDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rows, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i % 3 == 0) {
            viewHolder.getMarkTextView().setStaText(mDataSet.get(i), "标签" + i, R.layout.item_mark);
        } else {
            viewHolder.getMarkTextView().setText(mDataSet.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

}
