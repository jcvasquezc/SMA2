package com.sma2.sma2.FeatureExtraction.Speech.features;

import com.sma2.sma2.FeatureExtraction.Speech.tools.WAVfileReader;
import com.sma2.sma2.FeatureExtraction.Speech.tools.array_manipulation;
import com.sma2.sma2.FeatureExtraction.Speech.tools.f0detector;
import com.sma2.sma2.FeatureExtraction.Speech.tools.sigproc;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class RadarFeatures {

    private static array_manipulation ArrM = new array_manipulation();

    public static float jitter(String AudioFile) {

        WAVfileReader wavFileReader=new WAVfileReader();
        f0detector F0Detector =new f0detector();

        int[]  InfoSig= wavFileReader.getdatainfo(AudioFile);
        float[] SignalAh=wavFileReader.readWAV(InfoSig[0]);
        //Normalize signal
        sigproc SigProc = new sigproc();
        SignalAh = SigProc.normsig(SignalAh);
        float[] f0= F0Detector.sig_f0(SignalAh, InfoSig[1]);

        //Ensure nonzero values in f0 contour
        List lf0 = ArrM.find(f0,0f,2);
        //Convert list to float array
        //List to array
        float[] newF0 = new float[lf0.size()];
        for(int i=0;i<lf0.size();i++)
        {
            newF0[i] = f0[(int)lf0.get(i)];
        }
        f0 = newF0;

        //Length of the f0 contour
        int N = f0.length;
        if (N==0){
            return 25.0f;
        }

        float value_mean = sigproc.calculatemean(f0);
        float value_std = sigproc.calculateSD(f0);

        for(int i=0;i<f0.length;i++)
        {
            if (f0[i] > value_mean+(3*value_std))
                f0[i]= value_mean;
        }

        //Find Max
        float[] temp = Arrays.copyOfRange(f0,0, f0.length);
        Arrays.sort(temp);
        float Mp = temp[temp.length - 1];//Maximum pitch
        //Array with variations between elements of array
        float jitt = 0f;
        for (int i = 0; i < N; i=i+3)//Jitter is computed every 3 f0 periods
        {
            jitt+= abs(f0[i] - Mp);
        }
        jitt = (100*jitt)/(N*Mp);
        return jitt;
    }


    public float voiceRate(String AudioFile) {
        WAVfileReader wavFileReader=new WAVfileReader();
        f0detector F0Detector =new f0detector();

        int[] InfoSig= wavFileReader.getdatainfo(AudioFile);
        float[] Signal=wavFileReader.readWAV(InfoSig[0]);
        float datalen= InfoSig[0];
        sigproc SigProc = new sigproc();
        Signal = SigProc.normsig(Signal);
        float[] F0= F0Detector.sig_f0(Signal, InfoSig[1]);
        List VoicedSeg = F0Detector.voiced(F0, Signal);
        float vRate=0;
        if(VoicedSeg.size()==0){
            vRate=0;}
        else{
            vRate=InfoSig[1]*VoicedSeg.size()/datalen;}

        return vRate;
    }



}