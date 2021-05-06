package com.example.foodcorner.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodcorner.Adapter.RestaurantAdapter;
import com.example.foodcorner.Constant;
import com.example.foodcorner.Dialog.LocationSheet;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.Model.RestaurantViewModel;
import com.example.foodcorner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    LinearLayout headerLoc;
    private FragmentActivity fragmentActivity;
    private ArrayList<RestaurantList.data> restaurantLists = new ArrayList<>();
    private RestaurantAdapter adapter;
    private RestaurantViewModel restaurantViewModel;
    LiveData<RestaurantList> highPriceLiveData,lowPriceLiveData,bestRatedLiveData;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        this.view = view;
        initializeViews(view);
        initializeRecycler(view);
        initializeLiveData();
        return view;
    }

    private void initializeLiveData(){
        highPriceLiveData = restaurantViewModel.getHPriceyResObserver(0,0);
        lowPriceLiveData =  restaurantViewModel.getLPriceyResObserver(0,0);
        bestRatedLiveData = restaurantViewModel.getHRatedResObserver(0,0);
        highPriceLiveData.observe(getActivity(),highPriceObserver);
        lowPriceLiveData.observe(getActivity(),lowPriceObserver);
        bestRatedLiveData.observe(getActivity(),highRatedObserver);
    }

    private Observer highRatedObserver = new Observer<RestaurantList>(){
        @Override
        public void onChanged(RestaurantList restaurantList) {
            if(restaurantList==null){

            }else{
                Log.e("ERR","Error");
                restaurantLists.clear();
                restaurantLists.addAll(restaurantList.getData());
                adapter.notifyDataSetChanged();
            }
        }
    };

    private Observer lowPriceObserver = new Observer<RestaurantList>(){
        @Override
        public void onChanged(RestaurantList restaurantList) {
            if(restaurantList==null){

            }else{
                Log.e("ERR","Error");
                restaurantLists.clear();
                restaurantLists.addAll(restaurantList.getData());
                adapter.notifyDataSetChanged();
            }
        }
    };

    private Observer highPriceObserver = new Observer<RestaurantList>(){
        @Override
        public void onChanged(RestaurantList restaurantList) {
            if(restaurantList==null){

            }else{
                Log.e("ERR","Error");
                restaurantLists.clear();
                restaurantLists.addAll(restaurantList.getData());
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void initializeRecycler(View view){
        RecyclerView recyclerView  = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RestaurantAdapter(restaurantLists,getContext());
        recyclerView.setAdapter(adapter);
        restaurantViewModel = new RestaurantViewModel();
        LiveData<RestaurantList> restaurantListLiveData = restaurantViewModel.getRestaurantObserver(0,10);

        restaurantListLiveData.observe(getActivity(), new Observer<RestaurantList>() {
            @Override
            public void onChanged(RestaurantList restaurantList) {
                if(restaurantList==null){
                }else{
                    Log.e("ERR","Error");
                    restaurantLists.clear();
                    restaurantLists.addAll(restaurantList.getData());
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }

    private void initializeViews(View view){
        headerLoc = view.findViewById(R.id.header);
        headerLoc.setOnClickListener(this);
        view.findViewById(R.id.liBestRated).setOnClickListener(this);
        view.findViewById(R.id.liLowPrice).setOnClickListener(this::onClick);
        view.findViewById(R.id.liHighPrice).setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        fragmentActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header:
                LocationSheet locationSheet = new LocationSheet();
                locationSheet.show(fragmentActivity.getSupportFragmentManager(),"LocationBottomSheet");
                break;
            case R.id.liHighPrice:
                restaurantViewModel.getHPriceyResObserver(0,10);
                refreshView();
                view.findViewById(R.id.lineHighPrice).setVisibility(View.VISIBLE);
                break;
            case R.id.liLowPrice:
                restaurantViewModel.getLPriceyResObserver(0,10);
                refreshView();
                view.findViewById(R.id.lineLessPrice).setVisibility(View.VISIBLE);
                break;
            case R.id.liBestRated:
                restaurantViewModel.getHRatedResObserver(0,10);
                refreshView();
                view.findViewById(R.id.lineBestRated).setVisibility(View.VISIBLE);
                break;
        }
    }

    public void refreshView(){
        view.findViewById(R.id.lineBestRated).setVisibility(View.GONE);
        view.findViewById(R.id.lineHighPrice).setVisibility(View.GONE);
        view.findViewById(R.id.lineLessPrice).setVisibility(View.GONE);
        view.findViewById(R.id.lineRecently).setVisibility(View.GONE);
    }
    //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7IlVzZXJJZCI6NSwiRmlyc3ROYW1lIjoiU2hpdmFtIiwiTGFzdE5hbWUiOiJLdW1hciIsIkVtYWlsIjoibmlzaGFudDI1NDhAZ21haWwuY29tIiwiVmVyaWZpZWQiOnRydWUsIlN1YnNjcmlwdGlvbiI6IkJhc2ljIiwiUGFzc3dvcmQiOiJtYWxhbWFuaSIsIlZlcmlmaWNhdGlvbkNvZGUiOiI1MTE5IiwiSW1hZ2VVcmwiOm51bGx9LCJpYXQiOjE2MjAyODAxMzJ9.tcXEgVXw9ApOnHUOBdKWAAMfo9labVlPBuCKyGV5Z1o
    //http://10.0.2.2:47474/restaurant/getRestaurant?page=0&limit=10
}