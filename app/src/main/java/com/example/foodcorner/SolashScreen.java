package com.example.foodcorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.foodcorner.Adapter.SplashAdapter;
import com.example.foodcorner.Model.SplashItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SolashScreen extends AppCompatActivity {
    private ViewPager2 viewPager2;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solash_screen);

        TextView signIn = findViewById(R.id.tvSignIn);
        TextView logIn = findViewById(R.id.tvLogin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SolashScreen.this,SignUp.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SolashScreen.this,LoginActivity.class));
            }
        });

        findViewById(R.id.btnExplore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SolashScreen.this,MainActivity.class));
            }
        });

        String login = "<font color=#635E5E>Already have an account? </font><font color=#EA6B0F> Login</font>";
        String signUp = "<font color=#635E5E>Don't have an account? </font><font color=#EA6B0F> SignUp</font>";

        signIn.setText(Html.fromHtml(signUp));
        logIn.setText(Html.fromHtml(login));



        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        ArrayList<SplashItem> itemList = new ArrayList<>();
        itemList.add(new SplashItem(R.drawable.starter_image_a,"Enjoy wide range of Fast Food"));
        itemList.add(new SplashItem(R.drawable.starter_image_b,"Organic Fresh Food, for everyone"));
        itemList.add(new SplashItem(R.drawable.starter_image_c,"Fresh and Healthy, for your family"));
        SplashAdapter adapter = new SplashAdapter(itemList,getApplicationContext());
        viewPager2.setAdapter(adapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f+ r * 0.15f);
            }
        });


        viewPager2.setPageTransformer(compositePageTransformer);
        int num_page = itemList.size();
        final Handler handler = new Handler();
        final Runnable Update= new Runnable() {
            @Override
            public void run() {
                if(currentPage==num_page){
                    currentPage = 0;
                }
                viewPager2.setCurrentItem(currentPage++,true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },500,3000);
    }
}