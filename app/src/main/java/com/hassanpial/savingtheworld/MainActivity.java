package com.hassanpial.savingtheworld;
import static android.view.animation.Animation.INFINITE;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText tvConvertedText;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private boolean isListening = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeLottieFiles();
        tvConvertedText = findViewById(R.id.editText);
        Button btnSpeechToText = findViewById(R.id.btn);



        // Request RECORD_AUDIO permission if not granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {}

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    String convertedText = matches.get(0);
                    tvConvertedText.setText(convertedText);

                    if(convertedText.equals("drop water")){
                       dropWaterWithTimer();

                    }else{
                        speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }else{

                }


            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });



        btnSpeechToText.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                View makeitgone = findViewById(R.id.makeItGone);
                makeitgone.setVisibility(View.GONE);

                btnSpeechToText.setVisibility(View.GONE);
                if (!isListening) {
                    startListening();
                } else {
                    stopListening();
                }
            }

            private void startListening() {
                speechRecognizer.startListening(speechRecognizerIntent);
                isListening = true;

            }

            private void stopListening() {
                speechRecognizer.stopListening();
                isListening = false;

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }



private  void initializeLottieFiles(){
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
        FrameLayout waterArea = findViewById(R.id.waterArea);

        waterArea.setVisibility(View.GONE);
        int color = Color.parseColor("#d4f1f9");
        LottieValueCallback<ColorFilter> colorFilterCallback = new LottieValueCallback<>();
        colorFilterCallback.setValue(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        waterDrop.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, colorFilterCallback);


    }
    private void dropWaterWithTimer(){

    FrameLayout waterArea = findViewById(R.id.waterArea);
    waterArea.setVisibility(View.VISIBLE);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) waterArea.getLayoutParams();


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//
//               if(params.topMargin < (480)){
//                   params.setMargins(params.leftMargin, 200, params.rightMargin, params.bottomMargin);
//                   waterArea.setLayoutParams(params);
//               }


            }
        };

        Timer time = new Timer();

        time.scheduleAtFixedRate(timerTask, 5, 5);
    }

}