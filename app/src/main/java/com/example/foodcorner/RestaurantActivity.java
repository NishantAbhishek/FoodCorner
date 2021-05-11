package com.example.foodcorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.ViewModel.RestaurantPageModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ImageView restaurantImage;
    private TextView tvRestaurantName,tvRestaurantPrice,eventTitle,eventBody,eventDate,announcementTitle,announcementBody,announcementDate;
    private RatingBar rbRating;
    private MapView mapView;
    private Button btnBook;
    private RestaurantPageModel restaurantPageViewModel;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        instantiateViews();
        setData();
    }

    private void instantiateViews(){
        restaurantPageViewModel = new ViewModelProvider(this).get(RestaurantPageModel.class);
        restaurantImage = findViewById(R.id.restaurantImage);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvRestaurantPrice = findViewById(R.id.tvRestaurantPrice);
        eventTitle = findViewById(R.id.eventTitle);
        eventBody = findViewById(R.id.eventBody);;
        eventDate = findViewById(R.id.eventDate);
        announcementTitle = findViewById(R.id.announcementTitle);
        announcementBody = findViewById(R.id.announcementBody);
        announcementDate = findViewById(R.id.announcementDate);
        rbRating = findViewById(R.id.rbRating);
    }

    LiveData<RestaurantList> listLiveData;

    private void setData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(Constant.CATCHE_USER,MODE_PRIVATE);
        int id = sharedPreferences.getInt(Constant.RESTAURANT_SELECTED_ID,-1);
        if(id!=-1){
            listLiveData = restaurantPageViewModel.getRestaurantById(id);
            listLiveData.observe(this,restaurantListObserver);
        }
    }

     Observer<RestaurantList> restaurantListObserver = new Observer<RestaurantList>() {
        @Override
        public void onChanged(RestaurantList restaurantList) {
            if(restaurantList!=null){
                if(restaurantList.getData().size()!=0){
                    RestaurantList.data data = restaurantList.getData().get(0);
                    setDataToViews(data);
                }

            }
        }
    };

    private void setDataToViews(RestaurantList.data data){
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        findViewById(R.id.scrollView).setVisibility(View.VISIBLE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvRestaurantName.setText(data.getName());
        tvRestaurantPrice.setText(data.getPricy()+" Pricey");

        String event = data.getUpcomingEvent();
        if(event!=null){
            eventBody.setText(event);
        }else{
            eventBody.setText("No upcoming Event");
        }

        String event_Date = data.getEventDate();
        if(eventDate!=null){
            eventDate.setText(event_Date);
        }else{
            eventDate.setVisibility(View.GONE);
        }

        String annoucement = data.getAnnoucement();
        if(annoucement!=null){
            announcementBody.setText(annoucement);
        }else{
            announcementBody.setText("No Announcement for now");
        }

        String announcement_Date = data.getAnnoucementDate();
        if(announcement_Date!=null){
            announcementDate.setText(announcement_Date);
        }else{
            announcementDate.setVisibility(View.GONE);
        }

        rbRating.setRating(data.getRating());
    }

    @Override
    protected void onPause() {
        super.onPause();
        listLiveData.removeObserver(restaurantListObserver);
        restaurantPageViewModel.setNull();
        finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}