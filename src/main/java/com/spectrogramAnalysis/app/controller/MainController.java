package com.spectrogramAnalysis.app.controller;

import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.Main;
import com.spectrogramAnalysis.app.loader.AudioLoader;

public class MainController {

   //Audio
   Buffer buffer;

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
      AudioLoader loader = new AudioLoader();
      buffer = loader.load(args[0]);
   }
}
