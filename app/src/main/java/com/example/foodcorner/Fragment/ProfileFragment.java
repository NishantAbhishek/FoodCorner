package com.example.foodcorner.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodcorner.Constant;
import com.example.foodcorner.LoginActivity;
import com.example.foodcorner.Model.NormalResponse;
import com.example.foodcorner.Model.UserProfile;
import com.example.foodcorner.R;
import com.example.foodcorner.SignUp;
import com.example.foodcorner.ViewModel.ProfilePageViewModel;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private ImageView imgEdit;
    private ImageView userImage;
    private TextView tvEmail;
    private TextView tvName;
    private EditText edName;
    private Button btnDone, btnConfigure;
    private ProfilePageViewModel viewModel;
    private LinearLayout body;
    boolean userLoggedIn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewModel = new ViewModelProvider(this).get(ProfilePageViewModel.class);
        initializeViews(view);
        initializeData();
        return view;
    }

    private void initializeViews(View view) {
        imgEdit = view.findViewById(R.id.imgEdit);
        userImage = view.findViewById(R.id.userProfile);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        edName = view.findViewById(R.id.edName);
        btnDone = view.findViewById(R.id.btnDone);
        btnConfigure = view.findViewById(R.id.btnConfigure);
        body = view.findViewById(R.id.body);
        imgEdit.setOnClickListener(this);
    }

    private void initializeData() {
        SharedPreferences cache = getContext().getSharedPreferences(Constant.CATCHE_USER, Context.MODE_PRIVATE);
        int id = cache.getInt(Constant.USER_ID, -1);
        String name = cache.getString(Constant.NAME, "");
        if (id == -1) {
            body.setVisibility(View.GONE);
            userLoggedIn = false;
        } else {
            userLoggedIn = true;
            body.setVisibility(View.VISIBLE);
        }

        if (userLoggedIn){
            btnConfigure.setText("LogOut");
            btnConfigure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cache.edit().clear().commit();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            });
        } else {
            btnConfigure.setText("Create Account");
            btnConfigure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SignUp.class));
                    getActivity().finish();
                }
            });
        }

        viewModel.getUserProfile(name, id).observe(getActivity(), getUserInfo);
    }

    Observer<UserProfile> getUserInfo = new Observer<UserProfile>() {
        @Override
        public void onChanged(UserProfile userProfile) {
            if (userProfile != null && userProfile.getData().size() > 0) {
                String imageUrl = userProfile.getData().get(0).getImageUrl();
                String name = userProfile.getData().get(0).getFirstName();
                String email = userProfile.getData().get(0).getEmail();
                edName.setText(name);
                tvName.setText(name);
                tvEmail.setText(email);
                if(imageUrl!=null){
                    Glide.with(getContext()).load(imageUrl).into(userImage);
                }
            } else {
                Toast.makeText(getContext(), "Some Error Occurred", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgEdit:
                tvName.setVisibility(View.GONE);
                edName.setVisibility(View.VISIBLE);
                break;
        }
    }
}