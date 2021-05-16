package com.example.foodcorner.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodcorner.Adapter.RestaurantAdapter;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.R;
import com.example.foodcorner.ViewModel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText edSearch;
    private static final String TAG = "SearchFragment";
    private SearchViewModel viewModel;
    private RestaurantAdapter restaurantAdapter;
    private ArrayList<RestaurantList.data> list = new ArrayList<>();
    private LiveData<List<RestaurantList.data>> restaurantLiveData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_search, container, false);
        viewModel =new  ViewModelProvider(this).get(SearchViewModel.class);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        edSearch = view.findViewById(R.id.edSearch);
        initializeRecycle();

        restaurantLiveData = viewModel.getLiveList();
        restaurantLiveData.observe(getActivity(),observer);

        TextWatcher textWatcher = new TextWatcher() {
            private Timer timer = new Timer();
            private long DELAY =600;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.e(TAG,s.toString());
                        viewModel.searchRestaurant(s.toString());
                    }
                },DELAY);
            }
        };
        edSearch.addTextChangedListener(textWatcher);
    }

    private void initializeRecycle(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        restaurantAdapter = new RestaurantAdapter(list,getContext());
        recyclerView.setAdapter(restaurantAdapter);
    }

    Observer<List<RestaurantList.data>> observer = new Observer<List<RestaurantList.data>>() {
        @Override
        public void onChanged(List<RestaurantList.data> data) {
            if(data!=null){
                list.clear();
                list.addAll(data);
                restaurantAdapter.notifyDataSetChanged();
            }
        }
    };

}