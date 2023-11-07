package com.example.recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HexFormat;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final String[] data;
    private final Activity host;

    private rowItemClickListener observer;

    public MyAdapter(String[] data, Activity host, rowItemClickListener ricl) {
        this.data = data;
        this.host = host;
        this.observer = ricl;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout;
        if (viewType == 0) {
            layout = (LinearLayout) LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.row_item, parent, false);
        }
        else {
            layout = (LinearLayout) LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.row_item1, parent, false);
        }
        layout.setOnClickListener(v -> {
            if (observer != null) {
                TextView name = layout.findViewById(R.id.name);
                observer.rowItemWasClicked(name.getText().toString());
            }
        });

        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(data[position]);
        holder.letter.setText(position + "");
    }

    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        public final TextView text, letter;

        public MyViewHolder(LinearLayout layout) {
            super(layout);
            this.layout = layout;
            this.text = layout.findViewById(R.id.name);
            this.letter = layout.findViewById(R.id.other);
        }
    }

    public interface rowItemClickListener {

        public void rowItemWasClicked(String text);
    }
}
