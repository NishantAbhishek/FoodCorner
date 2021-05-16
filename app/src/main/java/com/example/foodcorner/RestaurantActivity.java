package com.example.foodcorner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.RestaurantList;
import com.example.foodcorner.ViewModel.RestaurantPageModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import java.time.Year;
import java.util.Calendar;

public class RestaurantActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private ImageView restaurantImage;
    private TextView tvRestaurantName,tvRestaurantPrice,eventTitle,eventBody,eventDate,announcementTitle,announcementBody,announcementDate;
    private RatingBar rbRating;
    private MapView mapView;
    private Button btnBook;
    private RestaurantPageModel restaurantPageViewModel;
    private GoogleMap mMap;
    private String dateBooked,timeBooked = null;
    private int RestaurantId;

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
        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(this::onClick);
    }

    LiveData<RestaurantList> listLiveData;
    LiveData<NormalResponse> bookResponse;

    private void setData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(Constant.CATCHE_USER,MODE_PRIVATE);
        int id = sharedPreferences.getInt(Constant.RESTAURANT_SELECTED_ID,-1);
        RestaurantId = id;
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

        setMarker(data.getLat(),data.getLang());

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
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
    }

    private void setMarker(float lat, float lang){
//        LatLng point = new LatLng(lat, lang);
//        mMap.addMarker(new MarkerOptions().position(point).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBook:
                showDialog();
                break;
        }
    }

    private void showDialog(){
        Dialog bookDialog = new Dialog(RestaurantActivity.this);
        bookDialog.setContentView(R.layout.book_restaurant_dialog);
        bookDialog.show();

        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tvChooseDate:
                        dateDialog(bookDialog);
                        break;
                    case R.id.btnSubmit:
                        String date = ((TextView)bookDialog.findViewById(R.id.tvChooseDate)).getText().toString();
                        String time = ((TextView)bookDialog.findViewById(R.id.tvChooseTime)).getText().toString();
                        String seat = ((TextView)bookDialog.findViewById(R.id.edNumberOfTable)).getText().toString();
                        boolean isTunnel = ((RadioButton)bookDialog.findViewById(R.id.rbTunnel)).isChecked();
                        if(!date.equals("Choose Date")&&!time.equals("Choose Time")&&!seat.equals("")){
                            JsonObject details = new JsonObject();
                            details.addProperty("RestaurantId",RestaurantId);
                            details.addProperty("NumberOfTable",Integer.parseInt(seat));
                            details.addProperty("DateBooked",date);
                            details.addProperty("TimeBooked",time);

                            String bookingType;
                            if(isTunnel){
                                bookingType = "Tunnel";
                            }else{
                                bookingType = "TakeAway";
                            }
                            details.addProperty("BookingType",bookingType);
                            SharedPreferences catche = getSharedPreferences(Constant.CATCHE_USER,MODE_PRIVATE);
                            int userId = catche.getInt(Constant.USER_ID,-1);
                            details.addProperty("UserId",userId);
                            if(userId!=-1){
                                bookResponse = restaurantPageViewModel.bookRestaurant(details);
                                bookDialog.findViewById(R.id.btnSubmit).setVisibility(View.GONE);
                                bookDialog.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                                bookResponse.observe(RestaurantActivity.this, new Observer<NormalResponse>() {
                                    @Override
                                    public void onChanged(NormalResponse normalResponse) {
                                        if(normalResponse!=null){
                                            Toast.makeText(getApplicationContext(),normalResponse.getMessage(),Toast.LENGTH_LONG).show();
                                            bookDialog.dismiss();
                                        }else{
                                            bookDialog.dismiss();
                                            Toast.makeText(getApplicationContext(),"Network Problem",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(),"Please Create an account to book",Toast.LENGTH_LONG).show();
                            }
                        }
                        break;
                    case R.id.tvChooseTime:
                        timeDialog(bookDialog);
                        break;
                }
            }
        };

        TextView selectDate = bookDialog.findViewById(R.id.tvChooseDate);
        Button btnSubmit = bookDialog.findViewById(R.id.btnSubmit);
        TextView selectTime = bookDialog.findViewById(R.id.tvChooseTime);
        selectDate.setOnClickListener(btnClick);
        selectTime.setOnClickListener(btnClick);
        btnSubmit.setOnClickListener(btnClick);
    }

    DatePickerDialog.OnDateSetListener myDateListener;


    private void dateDialog(Dialog dialog){
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);

        myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ((TextView)dialog.findViewById(R.id.tvChooseDate)).setText((dayOfMonth+1)+"/"+month+"/"+year);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,myDateListener,year,month,day);
        datePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener;
    private void timeDialog(Dialog dialog){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ((TextView)dialog.findViewById(R.id.tvChooseTime)).setText(hourOfDay+" : "+minute);
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,timeSetListener,hour,minute,false);
        timePickerDialog.show();
    }



}