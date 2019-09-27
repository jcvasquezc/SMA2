package com.sma2.sma2.ExerciseFragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sma2.sma2.FeatureExtraction.Movement.CSVFileReader;
import com.sma2.sma2.FeatureExtraction.Movement.MovementProcessing;
import com.sma2.sma2.R;
import com.sma2.sma2.SignalRecording.CSVFileWriter;
import com.sma2.sma2.SignalRecording.MovementRecorder;


public class Ex_Circling_Rec extends ExerciseFragment implements ButtonFragment.OnButtonInteractionListener {
    private MovementRecorder recorder;
    private static long START_COUNTDOWN = 3;
    private static long EXERCISE_TIME = 30;
    private final int SAMPLING_FREQUENCY = 10000;
    private String countdown_finished_txt;
    private long countdownStart;
    private CountDownTimer timer;
    private TextView countdownTextView;
    private boolean countdownIsRunning = false;

    private CSVFileReader FileReader;
    private MovementProcessing MovementProcessor = new MovementProcessing();
    public Ex_Circling_Rec() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileReader = new CSVFileReader(getActivity().getApplicationContext());
        try {
            recorder = new MovementRecorder(this.getContext(), SAMPLING_FREQUENCY, mExercise.getName());
            recorder.registerListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ex_circling, container, false);
        ButtonFragment buttonFragment = new ButtonFragment();
        buttonFragment.setmListener(this);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        transaction.replace(R.id.frameExSV, buttonFragment);
        transaction.commit();
        countdown_finished_txt = getResources().getString(R.string.start2);
        countdownTextView = view.findViewById(R.id.countdownTimerTextView);
        countdownTextView.setText(String.valueOf(START_COUNTDOWN));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            recorder.stop();
            recorder = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onButtonInteraction(boolean start) {
        if (start) {
            recorder.startLogging();
            startInitialCountdownTimer();
        } else {
            if(countdownIsRunning) {
                countdownIsRunning = false;
                timer.cancel();
                countdownTextView.setText(String.valueOf(START_COUNTDOWN));
            } else {
                try {
                    recorder.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                EvaluateFeatures();
                mListener.onExerciseFinished(recorder.getFileName());
            }
        }
    }

    private void EvaluateFeatures() {
        String Route= CSVFileWriter.getpath();
        String final_route = Route + recorder.getFileName();

        if(mExercise.getId() == 23){
            float UppRegularity = MovementProcessor.UppRegularity(FileReader,final_route);
            try {
                MovementProcessor.export_movement_feature(final_route,UppRegularity,"Regularity_Circles_Right");
            }catch (Exception e) {
                Toast.makeText(getActivity(),"Regularity of circles right hand failed",Toast.LENGTH_SHORT).show();
            }
        }

        else if(mExercise.getId() == 24){
            float UppRegularity = MovementProcessor.UppRegularity(FileReader,final_route);
            try {
                MovementProcessor.export_movement_feature(final_route,UppRegularity,"Regularity_Circles_Left");
            }catch (Exception e) {
                Toast.makeText(getActivity(),"Regularity of circles left hand failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startInitialCountdownTimer() {
        countdownIsRunning = true;
        countdownStart = System.currentTimeMillis();
        timer = new CountDownTimer(START_COUNTDOWN * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int newTime = Math.round(millisUntilFinished / 1000);
                countdownTextView.setText(String.valueOf(newTime));
            }
            public void onFinish() {
                countdownIsRunning = false;
                this.cancel();
                countdownTextView.setText(countdown_finished_txt);
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.bell);
                mp.start();
                Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(1000);

            }
        }.start();

    }
}
