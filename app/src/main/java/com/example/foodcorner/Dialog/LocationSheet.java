package com.example.foodcorner.Dialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodcorner.Adapter.LocationAdapter;
import com.example.foodcorner.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LocationSheet extends BottomSheetDialogFragment {
    private EditText edPlace;
    private RecyclerView recyclerView;
    private LocationAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_bottom_sheet,container);
        inititlaizeRecycler(view);
        return view;
    }

    private void inititlaizeRecycler(View view){
        adapter = new LocationAdapter(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        edPlace = view.findViewById(R.id.edSearch);
        edPlace.addTextChangedListener(filterTextWatcher);
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")){
                adapter.getFilter().filter(s.toString());
                if(recyclerView.getVisibility()==View.GONE){
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }else{
                if(recyclerView.getVisibility()==View.VISIBLE){
                    recyclerView.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.LocationDialog);
    }
}