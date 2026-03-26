package com.spectrogramAnalysis.app.controller;

import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.Audio.Player;
import com.spectrogramAnalysis.app.Main;
import com.spectrogramAnalysis.app.loader.AudioLoader;

public class MainController {

   //Audio
   AudioLoader audioLoader;
   Buffer buffer;
   Player player;

   //Graphics
   public Display display;
   int width,height = 0;

   public MainController(int width , int height)
   {
      this.width = width;
      this.height = height;
      display = new Display(width , height);
   }

   public void loadAudio(String[] args)
   {
      audioLoader = new AudioLoader();
      player = new Player();

      player.load(args[0]);
      buffer = audioLoader.load(args[0]);

      display.frameBuffer = buffer;
   }


   public void start()
   {
      display.start();
      player.play();
   }


}
