package com.spectrogramAnalysis.app.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

   AudioInputStream audioStream;
   Clip clip;


   public void load(String path)
   {
      File file = new File(path);
      try {
         audioStream = AudioSystem.getAudioInputStream(file);
         clip = AudioSystem.getClip();
         clip.open(audioStream);

      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public void play()
   {
      clip.start();
      try {
         Thread.sleep(clip.getMicrosecondLength()/1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
