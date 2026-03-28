package com.spectrogramAnalysis.app.Audio;

public class Buffer {

   public short[] data;
   public int sampleRate;
   public int duration;

   public void initArray(int size , int sampleRate)
   {
      this.data = new short[size];
      this.sampleRate = sampleRate;
      this.duration = data.length/sampleRate;
   }

}
