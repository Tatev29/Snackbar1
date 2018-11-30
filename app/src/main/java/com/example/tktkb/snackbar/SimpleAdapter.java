package com.example.tktkb.snackbar;


import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private List<String> data;

    public SimpleAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.name.setText(data.get(viewHolder.getAdapterPosition()));
        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    final int x=(viewHolder.getAdapterPosition());
                    final String item=(data.get(x));
                    data.remove(viewHolder.getAdapterPosition());
                    notifyItemRemoved(viewHolder.getAdapterPosition());
                    Snackbar snackbar = Snackbar
                            .make(view,"Deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    data.add(x,item);
                                    notifyItemInserted(x);
                                    Snackbar snackbar1 = Snackbar.make(view,"Restore the item", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

                    snackbar.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}