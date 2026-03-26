package com.spectrogramAnalysis.app.controller.threaded;

import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.ffourier.Dft;
import com.spectrogramAnalysis.app.ffourier.FrequencyBucket;

public class FourierTransformProcessor implements Runnable{

   //frequency and window data
   public int windowSize = 1024;
   int hop = 1024;

   public int frequencyRange = 22050;
   int left = 0,right = windowSize - 1;


   //Buffer and Fourier
   Buffer frameBuffer;
   FrequencyBucket freqBucket;
   Dft dftMatrixOperator;

   //Threading
   Thread thread = new Thread(this);
   double FftRate = 0;

   public FourierTransformProcessor(Buffer frameBuffer , FrequencyBucket bucket)
   {
      this.frameBuffer = frameBuffer;
      this.frequencyRange = frameBuffer.sampleRate / 2;
      this.freqBucket = bucket;
   }

   public FourierTransformProcessor(int windowSize , int hop , Buffer frameBuffer , FrequencyBucket bucket)
   {
      this.windowSize = windowSize;
      this.hop = hop;
      this.frameBuffer = frameBuffer;
      this.frequencyRange = frameBuffer.sampleRate / 2;
      this.freqBucket = bucket;
   }


   public void start()
   {
      dftMatrixOperator = new Dft(windowSize);
      FftRate = (double)frameBuffer.sampleRate/hop;

      thread.start();
   }

   @Override
   public void run()
   {

      while (thread != null)
      {
         //do this till n no of operations required for an entire sample
         dftMatrixOperator.dftMultiplication(freqBucket.bucket , frameBuffer.data, left);
         left += hop;

      }

   }



}
