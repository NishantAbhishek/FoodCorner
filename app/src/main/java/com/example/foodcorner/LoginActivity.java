package com.example.foodcorner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodcorner.Model.LoginResponse;
import com.example.foodcorner.ViewModel.LoginViewModel;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSignUp;
    private EditText edEmail;
    private EditText edPassword;
    private Button btnLogin;
    private LoginViewModel loginViewModel;
    private static final String TAG = LoginActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }
    
    private void initialize(){
        String login = "<font color=#635E5E>Already have an account? </font><font color=#EA6B0F> Login</font>";
        String signUp = "<font color=#635E5E>Don't have an account? </font><font color=#EA6B0F> SignUp</font>";

        tvSignUp = findViewById(R.id.tvSignIn);
        tvSignUp.setText(Html.fromHtml(signUp));
        btnLogin = findViewById(R.id.btnLogin);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);

        btnLogin.setOnClickListener(this::onClick);
        tvSignUp.setOnClickListener(this::onClick);
        loginViewModel = new LoginViewModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSignIn:
                startActivity(new Intent(LoginActivity.this,SignUp.class));
                break;
            case R.id.btnLogin:
                String email = edEmail.getText().toString();
                String passwd = edPassword.getText().toString();
                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.wait_layout);
                ((TextView)dialog.findViewById(R.id.message)).setText("Wait Checking...");
                dialog.show();
                if(!email.equals("")&&email!=null&&!passwd.equals("")&&passwd!=null){
                    loginViewModel.loginObserver().observe(LoginActivity.this, new Observer<LoginResponse>() {
                        @Override
                        public void onChanged(LoginResponse loginResponse) {
                            if(loginResponse.getMessage().equals("Login Success")){
                                Log.e(TAG,loginResponse.getAuth());
                                dialog.dismiss();
                                SharedPreferences credential = getApplicationContext().getSharedPreferences(Constant.CATCHE_USER,MODE_PRIVATE);
                                SharedPreferences.Editor editor = credential.edit();
                                editor.putString(Constant.AUTH,loginResponse.getAuth());
                                editor.putString(Constant.EMAIL,email);
                                editor.putString(Constant.NAME,loginResponse.getName());
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                finish();
                            }else{
//                                Toast.makeText(getApplicationContext(),"Check Credential",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    });
                    JsonObject auth = new JsonObject();
                    auth.addProperty("Email",email);
                    auth.addProperty("Password",passwd);
                    loginViewModel.loginApiCall(auth);
                }
                break;
        }

    }
}