package com.hassanpial.savingtheworld;

import static android.view.animation.Animation.INFINITE;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.hassanpial.savingtheworld.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LottieAnimationView helicopterAnimationView = findViewById(R.id.helicopter);
        helicopterAnimationView.playAnimation();
        helicopterAnimationView.setRepeatCount(INFINITE);

        LottieAnimationView fire1AnimationView = findViewById(R.id.fire1);
        fire1AnimationView.playAnimation();
        fire1AnimationView.setRepeatCount(INFINITE);

        LottieAnimationView fire2AnimationView = findViewById(R.id.fire2);
        fire2AnimationView.playAnimation();
        fire2AnimationView.setRepeatCount(INFINITE);

        LottieAnimationView fire3AnimationView = findViewById(R.id.fire3);
        fire3AnimationView.playAnimation();
        fire3AnimationView.setRepeatCount(INFINITE);

        LottieAnimationView fire4AnimationView = findViewById(R.id.fire4);
        fire4AnimationView.playAnimation();
        fire4AnimationView.setRepeatCount(INFINITE);

        LottieAnimationView fire5AnimationView = findViewById(R.id.fire5);
        fire5AnimationView.playAnimation();
        fire5AnimationView.setRepeatCount(INFINITE);


        LottieAnimationView waterDrop = findViewById(R.id.water);
        waterDrop.setVisibility(View.GONE);

        int color = Color.parseColor("#d4f1f9");
        LottieValueCallback<ColorFilter> colorFilterCallback = new LottieValueCallback<>();
        colorFilterCallback.setValue(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        waterDrop.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, colorFilterCallback);



        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               waterDrop.setVisibility(View.VISIBLE);
               waterDrop.playAnimation();
               waterDrop.setRepeatCount(1);

                Toast.makeText(MainActivity.this, "Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}