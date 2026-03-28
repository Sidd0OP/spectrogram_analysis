package com.spectrogramAnalysis.app.controller;

import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.Audio.Player;
import com.spectrogramAnalysis.app.controller.threaded.Display;
import com.spectrogramAnalysis.app.controller.threaded.FourierTransformProcessor;
import com.spectrogramAnalysis.app.ffourier.FrequencyBucket;
import com.spectrogramAnalysis.app.loader.AudioLoader;

public class MainController {

   //Audio
   AudioLoader audioLoader;
   Buffer buffer;
   Player player;

   //Graphics
   public FrequencyBucket bucket;
   public Display display;
   int width,height = 0;

   //Transformation
   FourierTransformProcessor fourierProcessor;

   public MainController(int width , int height)
   {
      this.width = width;
      this.height = height;
   }

   public void load(String[] args) throws Exception
   {
      audioLoader = new AudioLoader();
      player = new Player();

      player.load(args[0]);
      buffer = audioLoader.load(args[0]);

      if(buffer.data == null)
      {
         throw new Exception("Error reading file at" + args[0]);
      }

      bucket = new FrequencyBucket(1024);
      fourierProcessor = new FourierTransformProcessor(buffer , bucket);

      System.out.println(buffer.data.length);

      display = new Display(width , height, buffer , bucket);
      display.freqBucket = bucket;
      display.frameBuffer = buffer;

   }


   public void start()
   {
      fourierProcessor.start();
      display.start();
      player.play();
   }


}
