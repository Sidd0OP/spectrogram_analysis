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
      display = new Display(width , height);
   }

   public void load(String[] args)
   {
      audioLoader = new AudioLoader();
      player = new Player();

      player.load(args[0]);
      buffer = audioLoader.load(args[0]);
      bucket = new FrequencyBucket(1024);

      fourierProcessor = new FourierTransformProcessor(buffer , bucket);

      display.frameBuffer = buffer;
      display.freqBucket = bucket;

   }


   public void start()
   {
      fourierProcessor.start();
      display.start();
      player.play();
   }


}
