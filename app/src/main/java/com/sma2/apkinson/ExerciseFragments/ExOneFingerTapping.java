package com.sma2.apkinson.ExerciseFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.sma2.apkinson.DataAccess.FeatureDataService;
import com.sma2.apkinson.FeatureExtraction.Tapping.FeatureTapping;
import com.sma2.apkinson.R;
import com.sma2.apkinson.SignalRecording.TappingRecorder;

import java.io.File;
import java.util.Date;


public class ExOneFingerTapping extends ExerciseFragment implements View.OnClickListener {
    private TappingRecorder tappingrecorder;
    private TextView chronoText;
    private ConstraintLayout background;
    private ImageButton tapButton;
    private int screenHeight, screenWidth;
    private long lastTime;
    private CountDownTimer timer;
    private static final long TOTAL_TIME =  10000;
    SharedPreferences sharedPref;
    FeatureDataService FeatureDataService;
    FeatureTapping featureTapping;
    final String PATH = Environment.getExternalStorageDirectory() + "/Apkinson/MOVEMENT/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex_one_finger_tapping, container, false);
        tappingrecorder = TappingRecorder.getInstance(getContext());
        try {
            tappingrecorder.TapHeaderWriter(mExercise.getName(), 0);
        }catch (Exception e) {
            Log.e("Tapping1HeaderWriter", e.toString());
        }
        sharedPref =PreferenceManager.getDefaultSharedPreferences(getActivity());
        filePath = tappingrecorder.TappingFileName();
        chronoText = view.findViewById(R.id.chronoTapOne);
        chronoText.setText(String.valueOf((float) TOTAL_TIME / 1000));
        background = view.findViewById(R.id.backgroundExTapOne);
        view.findViewById(R.id.bugTapOne).setOnClickListener(this);
        tapButton = view.findViewById(R.id.bugTapOne);
        tapButton.setOnClickListener(this);
        getDisplayDimensions();
        setListener();
        timer = setTimer();
        change_button_position();
        FeatureDataService=new FeatureDataService(getActivity().getApplicationContext());
        featureTapping=new FeatureTapping(getActivity().getApplicationContext());

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        background.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    ConstraintLayout.LayoutParams params_bugDif = (ConstraintLayout.LayoutParams) tapButton.getLayoutParams();
                    //Capturing the button position
                    String[] data = new String[3];
                    float yTap1 = (float) params_bugDif.topMargin;
                    float xTap1 = (float) params_bugDif.leftMargin;

                    //Screen position
                    float yScreen = ev.getY();
                    float xScreen = ev.getX();


                    //The euclidean distance between the touch screen position
                    // and the button to measure the error
                    double distanceTouchButton = Math.sqrt(Math.pow((xTap1 - xScreen), 2) + Math.pow((yTap1 - yScreen), 2));

                    data[0] = "0";
                    data[1] = String.valueOf(System.currentTimeMillis() - lastTime);
                    data[2] = Double.toString(distanceTouchButton);
                    lastTime = System.currentTimeMillis(); //To update the initial time


                    tappingrecorder.TapWriter(data);
                    return true;
                }
                return true;
            }
        });
    }

    private CountDownTimer setTimer() {
        return new CountDownTimer(TOTAL_TIME, 100) {

            // Here, it is computed the difference between the last time in which
            // you push the button and the time you are pushing the button

            public void onTick(long millisUntilFinished) {
                double newTime = (double) Math.round(millisUntilFinished / 100) / 10;
                chronoText.setText(String.valueOf(newTime));

            }
            public void onFinish() {
                chronoText.setText(getActivity().getApplicationContext().getString(R.string.done));
                try {
                    tappingrecorder.CloseTappingDocument();
                }catch (Exception e) {
                    Log.e("Tapping1CloseWriter", e.toString());
                }

                try {
                    EvaluateFeatures();
                }
                catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.failed),Toast.LENGTH_SHORT).show();
                }
                mListener.onExerciseFinished(filePath);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("New Area Tapping", true);
                editor.apply();


            }
        };
    }

    private void getDisplayDimensions() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        timer.cancel();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View view) {
        if (!recording){
            recording = true;
            lastTime = System.currentTimeMillis();
            timer.start();
            Vibrator vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(200);
            change_button_position();
        } else {
            String[] data = new String[3];
            Vibrator vib = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(200);
            data[0] = "1";
            data[1] = String.valueOf(System.currentTimeMillis() - lastTime);
            data[2] = "0";
            lastTime = System.currentTimeMillis();
            tappingrecorder.TapWriter(data);
            change_button_position();
        }
    }

    public void change_button_position(){
        ConstraintLayout.LayoutParams params_bug = (ConstraintLayout.LayoutParams)  tapButton.getLayoutParams();
        int bugHeight = tapButton.getHeight();
        int bugWidth = tapButton.getWidth();
        int x, y;
        if(bugHeight == 0 && bugWidth == 0){
            x = (int)(Math.random()*((screenWidth * 0.8)));
            y = (int)(Math.random()*((screenHeight * 0.8)));
        } else {
            x = (int) (Math.random() * ((screenWidth - bugWidth)));
            y = (int) (Math.random() * ((screenHeight - bugHeight)));
        }
        params_bug.topMargin=y;
        params_bug.leftMargin=x;
        params_bug.setMarginStart(x);
        tapButton.setLayoutParams(params_bug);
    }


    private void EvaluateFeatures() {
        File file = new File(PATH+filePath);
        Date lastModDate = new Date(file.lastModified());

        float[] features=featureTapping.feat_tapping_one(PATH+filePath);
        FeatureDataService.save_feature(FeatureDataService.perc_tapping1_name, lastModDate, features[0]);
        FeatureDataService.save_feature(FeatureDataService.veloc_tapping1_name, lastModDate, features[1]);
        FeatureDataService.save_feature(FeatureDataService.precision_tapping1_name, lastModDate, features[2]);

    }


}
