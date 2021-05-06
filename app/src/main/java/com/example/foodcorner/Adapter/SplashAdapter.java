package com.example.foodcorner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcorner.Model.SplashItem;
import com.example.foodcorner.R;

import java.util.ArrayList;

public class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.ViewHolder>{
    private ArrayList<SplashItem> items;
    private Context mContext;

    public SplashAdapter(ArrayList<SplashItem> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(inflater.inflate(R.layout.splash_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.tvFood.setText(items.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.splashImage);
            tvFood = itemView.findViewById(R.id.tvFood);
        }
    }
}
