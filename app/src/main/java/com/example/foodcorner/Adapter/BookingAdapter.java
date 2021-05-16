package com.example.foodcorner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    private ArrayList<BookingItem.data> data;
    private Context mContext;

    public BookingAdapter(ArrayList<BookingItem.data> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(inflater.inflate(R.layout.book_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName,tvDateBooked,tvTime,tvType,tvBookingType,tvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDateBooked = itemView.findViewById(R.id.tvDateBooked);
            tvTime= itemView.findViewById(R.id.tvTime);
            tvType= itemView.findViewById(R.id.tvType);
            tvBookingType= itemView.findViewById(R.id.tvBookingType);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
        public void setData(BookingItem.data data){
            tvName.setText(data.getRestaurantName());
            tvDateBooked.setText(data.getDateBooked());
            tvTime.setText(data.getTimeBooked());
            tvType.setText(data.getRestaurantType());
            tvBookingType.setText(data.getBookingType());
            tvRating.setText(Integer.toString(data.getRating()));
        }
    }
}
