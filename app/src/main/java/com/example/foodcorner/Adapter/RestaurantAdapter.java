package com.example.foodcorner.Adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcorner.Constant;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.R;
import com.example.foodcorner.RestaurantActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{
    private ArrayList<RestaurantList.data> restaurants;
    private Context mContext;

    public RestaurantAdapter(ArrayList<RestaurantList.data> restaurants, Context mContext) {
        this.restaurants = restaurants;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.restaurant_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRating.setText(Integer.toString(restaurants.get(position).getRating()));
        holder.tvName.setText(restaurants.get(position).getName());
        holder.tvType.setText(restaurants.get(position).getRestaurantType());
        holder.tvPrice.setText(restaurants.get(position).getPricy());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences catche = mContext.getSharedPreferences(Constant.CATCHE_USER,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = catche.edit();
                editor.putInt(Constant.RESTAURANT_SELECTED_ID,restaurants.get(position).getRestaurantId());
                editor.apply();
                Intent intent = new Intent(mContext, RestaurantActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageItem;
        CardView cardView;
        TextView tvName,tvType,tvPrice,tvTime,tvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageItem = itemView.findViewById(R.id.imageItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvTime =itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);


        }
    }
}
