package com.example.task_91p_lost_and_found_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertViewHolder> {

    private Context context;
    private ArrayList<Advert> advertList;

    public AdvertAdapter(Context context, ArrayList<Advert> advertList) {
        this.context = context;
        this.advertList = advertList;
    }

    @Override
    public AdvertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_advert, parent, false);
        return new AdvertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdvertViewHolder holder, int position) {
        Advert advert = advertList.get(position);

        holder.itemName.setText(advert.getName());
        holder.itemType.setText("Type: " + advert.getPostType());
        holder.itemLocation.setText("Location: " + advert.getLocation());
        holder.itemDate.setText("Date: " + advert.getDate());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("advertId", advert.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return advertList.size();
    }

    public static class AdvertViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemType, itemLocation, itemDate;

        public AdvertViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemType = itemView.findViewById(R.id.itemType);
            itemLocation = itemView.findViewById(R.id.itemLocation);
            itemDate = itemView.findViewById(R.id.itemDate);
        }
    }
}