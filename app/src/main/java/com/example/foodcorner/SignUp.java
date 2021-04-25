package com.example.foodcorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodcorner.Model.SignResponse;
import com.example.foodcorner.ViewModel.SignInViewModel;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText edFirstName,edLastName,edPassword,edEmail;
    private Button btnSignIn;
    private SignInViewModel signInViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        instantiateViews();
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
    }

    private void instantiateViews(){
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edPassword = findViewById(R.id.edPassword);
        edEmail = findViewById(R.id.edEmail);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                signInViewModel.signInObserver().observe(this, new Observer<SignResponse>() {
                    @Override
                    public void onChanged(SignResponse signResponse) {
                        if(signResponse!=null){
                            Toast.makeText(getApplicationContext(),signResponse.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("FirstName",edFirstName.getText().toString());
                jsonObject.addProperty("LastName",edLastName.getText().toString());
                jsonObject.addProperty("Email",edEmail.getText().toString());
                jsonObject.addProperty("Verified",0);
                jsonObject.addProperty("Password",edPassword.getText().toString());
                jsonObject.addProperty("Subscription","Basic");
                signInViewModel.signApiCall(jsonObject);
                break;
        }
    }
}