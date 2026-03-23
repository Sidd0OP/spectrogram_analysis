package com.spectrogramAnalysis.app.loader;

import com.spectrogramAnalysis.app.Audio.Buffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AudioLoader {



   public int load(String fileName, Buffer buffer)
   {
      byte[] file;

      try {
         file = Files.readAllBytes(Paths.get(fileName));
      } catch (IOException e) {
         System.out.println("Error loading file: " + fileName);
         return 1;
      }

      process(file, buffer);

      return 0;
   }


   private int process(byte[] file, Buffer buffer)
   {
      String riff = new String(file, 0, 4);if(!riff.equals("RIFF"))return 1;

      String format = new String(file, 8, 4);

      int channels = (file[22] & 0xFF) | ((file[23] & 0xFF) << 8);


      int sampleRate =  (file[24] & 0xFF) |
              ((file[25] & 0xFF) << 8) |
              ((file[26] & 0xFF) << 16) |
              ((file[27] & 0xFF) << 24);

      String data = new String(file, 38, 4);if(!data.equals("data"))return 1;

      int dataSize = (file[42] & 0xFF) |
              ((file[43] & 0xFF) << 8) |
              ((file[44] & 0xFF) << 16) |
              ((file[45] & 0xFF) << 24);


      //16 bit depth = 2 bytes
      int frameSize = channels * 2;
      int bufferSize = dataSize / frameSize;

      int baseIndex = 44;
      int sampleIndex = 0;

      Buffer.initArray(bufferSize);

      //data sample format => L_low, L_high,  R_low, R_high,  L_low, L_high,  R_low, R_high ...
      for(int i = 0; i < dataSize;i+=frameSize)
      {
         int index = baseIndex + i;

         byte byte_low_c1 = file[index];
         byte byte_high_c1 = file[index + 1];

         short c1 = (short)((byte_high_c1 << 8) | (byte_low_c1 & 0xFF));

         //offset 2
         byte byte_low_c2 = file[index + 2];
         byte byte_high_c2 = file[index + 3];

         short c2 = (short)((byte_high_c2 << 8) | (byte_low_c2 & 0xFF));

         Buffer.data[sampleIndex] = (short) ((c1 + c2) / 2);
         sampleIndex++;

      }


      System.out.println(channels + " " + sampleRate + " " + dataSize);
      System.out.println(Buffer.data.length);

      return 0;
   }


}
