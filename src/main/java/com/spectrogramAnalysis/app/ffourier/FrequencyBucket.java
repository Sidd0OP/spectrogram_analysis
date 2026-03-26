package com.spectrogramAnalysis.app.ffourier;

public class FrequencyBucket {

   public int windowSize = 0;
   public int[] bucket;

   public FrequencyBucket(int windowSize)
   {
      this.windowSize = windowSize;
      this.bucket = new int[windowSize];
   }
}
