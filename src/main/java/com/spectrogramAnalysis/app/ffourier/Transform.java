package com.spectrogramAnalysis.app.ffourier;

import com.spectrogramAnalysis.app.Audio.Buffer;

public class Transform {

   int windowSize = 1024;
   int hop = 1024;

   int frequencyRange = 22050;

   int left = 0,right = windowSize - 1;

   Buffer frameBuffer;

   public Transform(Buffer frameBuffer)
   {
      this.frameBuffer = frameBuffer;
      this.frequencyRange = frameBuffer.sampleRate / 2;
   }

   public Transform(int windowSize , int hop, Buffer frameBuffer)
   {
      this.windowSize = windowSize;
      this.hop = hop;
      this.frameBuffer = frameBuffer;
      this.frequencyRange = frameBuffer.sampleRate / 2;
   }
}
