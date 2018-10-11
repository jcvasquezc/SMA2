package com.sma2.sma2.ExerciseFragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Context;
import android.os.Vibrator;


import com.sma2.sma2.SignalRecording.TappingRecorder;
import com.sma2.sma2.R;
import com.sma2.sma2.ThanksActivity;

import java.util.ArrayList;


public class Tapping1 extends AppCompatActivity implements View.OnClickListener {

    TappingRecorder tappingrecorder;


    private float time2;
    private String timeStr;
    private String [] data= new String[3];
    public String TappingFileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tappingrecorder=TappingRecorder.getInstance(this);

        try {
            tappingrecorder.TapHeaderWriter("Tapping1", 0);
        }catch (Exception e) {
            Log.e("Tapping1HeaderWriter", e.toString());
            //return "ERROR: Could not add the header";
        }

        TappingFileName=tappingrecorder.TappingFileName();

        setContentView(R.layout.activity_capture_tapping_1);

        setListeners();
    }

    private void setListeners() {

        findViewById(R.id.tapButton_1).setOnClickListener(this);


        final TextView mTextField = findViewById(R.id.accgraph_chrono3);


        new CountDownTimer(10000, 1000) {

            // Here, it is computed the difference between the last time in which
            // you push the button and the time you are pushing the button
            float timef = SystemClock.currentThreadTimeMillis(); //Last time

            public void onTick(long millisUntilFinished) {
                mTextField.setText(Long.toString(millisUntilFinished / 1000));
                time2 = SystemClock.currentThreadTimeMillis()-timef; // The difference between times
                timeStr = String.valueOf(time2);


            }

            public void onFinish() {
                mTextField.setText(getApplicationContext().getString(R.string.done)); //

                try {
                    tappingrecorder.CloseTappingDocument();
                }catch (Exception e) {
                    Log.e("Tapping1CloseWriter", e.toString());
                    //return "ERROR: Could not add the header";
                }


                open_exercise();
            }
        }.start();

    }


    @Override
    public void onClick(View view) {

            final ConstraintLayout background = (ConstraintLayout) findViewById(R.id.backgroundTapping1);
            background.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent ev) {
                    ImageButton tap1 = findViewById(R.id.tapButton_1);
                    ConstraintLayout.LayoutParams params_bugDif= (ConstraintLayout.LayoutParams) tap1.getLayoutParams();
                    //Capturing the button position

                    float yTap1=(float) params_bugDif.topMargin;
                    float xTap1=(float) params_bugDif.leftMargin;

                    //Screen position
                    float yScreen=(float) ev.getY();
                    float xScreen=(float) ev.getX();


                     //The euclidean distance between the touch screen position
                    // and the button to measure the error
                    double distanceTouchButton= Math.sqrt(Math.pow((xTap1-xScreen),2)+Math.pow((yTap1-yScreen),2)) ;


                    data[0]="0";
                    data[1]=timeStr;
                    data[2]=Double.toString(distanceTouchButton);



                    tappingrecorder.TapWriter(data);


                    return true;
                }
            });



        if (view.getId()==R.id.tapButton_1){
            ImageButton tap1 = findViewById(R.id.tapButton_1);

            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            vib.vibrate(100);

            data[0]="1";
            data[1]=timeStr;
            data[2]="0";


            tappingrecorder.TapWriter(data);


            change_button_position(tap1);

        }


    }

    public void open_exercise(){
        Intent intent_ex1;
        intent_ex1 =new Intent(this, ThanksActivity.class);
        startActivity(intent_ex1);
    }

    public void change_button_position(ImageButton imageButton){
        ConstraintLayout.LayoutParams params_bug= (ConstraintLayout.LayoutParams)  imageButton.getLayoutParams();
        int y= (int)(Math.random()*((800)));
        int x= (int)(Math.random()*((600)));

        params_bug.topMargin=y;
        params_bug.leftMargin=x;
        params_bug.setMarginStart(x);
        imageButton.setLayoutParams(params_bug);

    }

}
