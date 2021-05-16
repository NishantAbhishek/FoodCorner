package com.example.foodcorner.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodcorner.Adapter.BookingAdapter;
import com.example.foodcorner.Constant;
import com.example.foodcorner.Model.BookingItem;
import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.R;
import com.example.foodcorner.ViewModel.BookedRestroViewModel;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private ArrayList<BookingItem.data> bookingList = new ArrayList<>();
    private BookedRestroViewModel viewModel;
    private TextView tvItems;
    private BookingAdapter bookingAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        viewModel = new ViewModelProvider(this).get(BookedRestroViewModel.class);
        initializeViews(view);
        return  view;
    }

    public void initializeViews(View view){
        tvItems = view.findViewById(R.id.tvItems);
        recyclerView = view.findViewById(R.id.myBooking);
        SharedPreferences cache = getActivity().getSharedPreferences(Constant.CATCHE_USER, Context.MODE_PRIVATE);
        int userId = cache.getInt(Constant.USER_ID,-1);
        Log.e("UserId","-"+userId);
        if(userId==-1){
            ((TextView)view.findViewById(R.id.tvTitle)).setText("Please create an Account");
        }

        LiveData<BookingItem> viewModelLiveData = viewModel.getBooked(userId);
        initializeAdapter();

        viewModelLiveData.observe(getActivity(),bookingObserver);
    }

    private void initializeAdapter(){
        bookingAdapter = new BookingAdapter(bookingList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookingAdapter);

        ItemTouchHelper.SimpleCallback recyclerCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                itemRemoval(viewHolder);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void itemRemoval(RecyclerView.ViewHolder viewHolder){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.confirm_removal);
        dialog.show();
        dialog.setCancelable(false);
        Button btnYes  = dialog.findViewById(R.id.yes);
        Button btnNo = dialog.findViewById(R.id.no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = bookingList.get(viewHolder.getAdapterPosition()).getUserid();
                int bookingId = bookingList.get(viewHolder.getAdapterPosition()).getBookingId();
                viewModel.removeBooking(userId,bookingId).observe(getActivity(), new Observer<NormalResponse>() {
                    @Override
                    public void onChanged(NormalResponse normalResponse) {
                        if(normalResponse!=null){
                            bookingList.remove(viewHolder.getAdapterPosition());
                            tvItems.setText(bookingList.size()+" Items");
                            bookingAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                            dialog.dismiss();
                        }else{
                            dialog.dismiss();
                            Toast.makeText(getActivity(),"Some Problem Occurred",Toast.LENGTH_LONG).show();
                        }
                        bookingAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                bookingAdapter.notifyDataSetChanged();
            }
        });

    }

    private static final String TAG = "CartFragment";
    private Observer bookingObserver = new Observer<BookingItem>(){
        @Override
        public void onChanged(BookingItem bookingItem) {
            if(bookingItem!=null){
                bookingList.clear();
                bookingList.addAll(bookingItem.getData());
                bookingAdapter.notifyDataSetChanged();
                Log.e(TAG,"Notifying Adapter");
                tvItems.setVisibility(View.VISIBLE);
                tvItems.setText(bookingList.size()+" Items");
            }
        }
    };

}