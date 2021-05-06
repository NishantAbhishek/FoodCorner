package com.example.foodcorner.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.R;
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
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageItem;
        TextView tvName,tvType,tvPrice,tvTime,tvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvTime =itemView.findViewById(R.id.tvTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
