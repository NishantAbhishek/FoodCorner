package com.example.foodcorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
                String firstName = edFirstName.getText().toString();
                String lastName = edLastName.getText().toString();
                String email = edEmail.getText().toString();
                int verified = 0;
                String password = edPassword.getText().toString();
                String subscription = "Basic";
                if(!firstName.equals("")&&firstName!=null&&!lastName.equals("")&&
                        lastName!=null&&!email.equals("")&&email!=null&&
                        !password.equals("")&&password!=null){
                    Dialog snedVerif = new Dialog(SignUp.this);
                    snedVerif.setContentView(R.layout.wait_layout);
                    snedVerif.setCancelable(false);
                    snedVerif.show();
                    signInViewModel.signInObserver().observe(this, new Observer<SignResponse>() {
                        @Override
                        public void onChanged(SignResponse signResponse) {
                            snedVerif.dismiss();
                            if(signResponse.getMessage().equals("Verification Mail Sent")){
                                Dialog verify = new Dialog(SignUp.this);
                                verify.setContentView(R.layout.verification_dialog);
                                verify.setCancelable(false);
                                verify.show();
                                verify.findViewById(R.id.btnVerify).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String code =((EditText)verify.findViewById(R.id.edCode)).getText().toString();
                                        if(code.length()==4){
                                            verify.findViewById(R.id.liLayoutInput).setVisibility(View.GONE);
                                            verify.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                                            signInViewModel.verificationObserver().observe(SignUp.this, new Observer<SignResponse>() {
                                                @Override
                                                public void onChanged(SignResponse signResponse) {
                                                    Toast.makeText(SignUp.this,signResponse.getMessage(),Toast.LENGTH_LONG).show();
                                                    if(signResponse.getMessage().equals("Authentication Confirmed")){
                                                        verify.findViewById(R.id.progressBar).setVisibility(View.GONE);
                                                        ((TextView)verify.findViewById(R.id.title)).setText("Verification Complete");
                                                        verify.findViewById(R.id.imgVerified).setVisibility(View.VISIBLE);
                                                        verify.setCancelable(true);
                                                    }else{
                                                        verify.findViewById(R.id.progressBar).setVisibility(View.GONE);
                                                        verify.findViewById(R.id.liLayoutInput).setVisibility(View.VISIBLE);
                                                    }
                                                }
                                            });
                                            JsonObject verifyEmail = new JsonObject();
                                            verifyEmail.addProperty("Email",edEmail.getText().toString());
                                            verifyEmail.addProperty("Key",code);
                                            signInViewModel.verifyEmail(verifyEmail);
                                        }

                                    }
                                });

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
                }
                break;
        }
    }
}