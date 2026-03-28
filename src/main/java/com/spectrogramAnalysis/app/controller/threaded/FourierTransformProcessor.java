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

   private long nano = 1000000000;

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
      long actualRunTime = (long) (nano / FftRate);
      long finishTime = System.nanoTime();

      while (thread != null)
      {
         long startTime = System.nanoTime();
//         long deltaTime = startTime - finishTime;
//         finishTime = startTime;

         //do this till n no of operations required for an entire sample
         dftMatrixOperator.dftMultiplication(freqBucket.bucket , frameBuffer.data, left);
         left += hop;

         long processingTime = System.nanoTime() - startTime;
         long sleepTime = (actualRunTime - processingTime) / 1_000_000;

         if (sleepTime > 0)
         {
            try {
               Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }

      }

   }



}
