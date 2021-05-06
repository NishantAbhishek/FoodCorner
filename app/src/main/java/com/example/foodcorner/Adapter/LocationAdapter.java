package com.example.foodcorner.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodcorner.R;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> implements Filterable {
    private static final String TAG = LocationAdapter.class.toString();

    private ArrayList<PlaceAutocomplete> mResultList = new ArrayList<>();

    private Context mContext;
    private CharacterStyle STYLE_BOLD;
    private CharacterStyle STYLE_NORMAL;
    private final PlacesClient placesClient;
    private RectangularBounds mBounds;

    public LocationAdapter(Context context) {
        mContext = context;
        STYLE_BOLD = new StyleSpan(Typeface.BOLD);
        STYLE_NORMAL = new StyleSpan(Typeface.NORMAL);
        placesClient = com.google.android.libraries.places.api.Places.createClient(context);
    }


    private ArrayList<PlaceAutocomplete> getPrediction(CharSequence constraint){
        final ArrayList<PlaceAutocomplete> resultList = new ArrayList<>();
        Log.e(TAG,"Constarint "+constraint);
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder().
                setLocationBias(mBounds).
                setSessionToken(token).
                setQuery(constraint.toString()).
                build();


        Task<FindAutocompletePredictionsResponse> autoCompletePrediction = placesClient. findAutocompletePredictions(request);
        try {
            Tasks.await(autoCompletePrediction, 60, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if(autoCompletePrediction.isSuccessful()){
            Log.e(TAG,"Success  ");
            FindAutocompletePredictionsResponse findAutocompletePredictionsResponse = autoCompletePrediction.getResult();

            if(findAutocompletePredictionsResponse!=null){
                for(AutocompletePrediction prediction:findAutocompletePredictionsResponse.getAutocompletePredictions()){
                    resultList.add(new PlaceAutocomplete(prediction.getPlaceId(), prediction.getPrimaryText(STYLE_NORMAL).toString(), prediction.getFullText(STYLE_BOLD).toString()));
                }

            }
            return resultList;

        }else{
            Log.e(TAG,"Failure  ");
            return resultList;
        }
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.location_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        holder.title.setText(mResultList.get(position).getArea());
        holder.subTitle.setText(mResultList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint!=null){
                    mResultList = getPrediction(constraint);
                    if(mResultList!=null){
                        results.values = mResultList;
                        results.count = mResultList.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count>0){
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
        }
    }


    public class PlaceAutocomplete {
        public CharSequence placeId;
        public CharSequence address, area;
        PlaceAutocomplete(CharSequence placeId, CharSequence area, CharSequence address) {
            this.placeId = placeId;
            this.area = area;
            this.address = address;
        }
        @Override
        public String toString() {
            return area.toString();
        }

        public CharSequence getAddress() {
            return address;
        }

        public void setAddress(CharSequence address) {
            this.address = address;
        }

        public CharSequence getArea() {
            return area;
        }

        public void setArea(CharSequence area) {
            this.area = area;
        }

        public CharSequence getPlaceId() {
            return placeId;
        }

        public void setPlaceId(CharSequence placeId) {
            this.placeId = placeId;
        }
    }
}
