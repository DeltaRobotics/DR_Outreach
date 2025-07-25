package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class SmoothScaler {

    private  int sampleRate;
    //in Milliseconds
    private  int sampleTimeDelta;
    private  int sampleCount;

    private int sampleIndex;

    private ElapsedTime elapsedTime;

    private double[] scalerSamples;

    public void resetTime (){

        elapsedTime.reset();

    }
    public SmoothScaler( int sampleRate, int sampleCount){

        this.sampleRate = sampleRate;
        this.sampleCount = sampleCount;
        this.sampleTimeDelta = (int)(1 / (double) sampleRate * 1000);

        sampleIndex = 0;
        elapsedTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        scalerSamples = new double[sampleCount];

        for(int i = 0; i < scalerSamples.length; i++){

            scalerSamples[i] = 0;

        }
    }
    public double smoothScaler (double input){

        if(elapsedTime.time() >= sampleTimeDelta){

            scalerSamples[sampleIndex] = input;

            elapsedTime.reset();

            if(sampleIndex == (sampleCount - 1)){

                sampleIndex = 0;
            }
            else{

                sampleIndex ++;
            }
        }

        double sampleSum = 0;

        for(double sample : scalerSamples){

            sampleSum += sample;
        }

        return sampleSum / sampleCount;
    }
}
