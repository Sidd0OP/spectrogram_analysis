package com.spectrogramAnalysis.app.controller;

import com.spectrogramAnalysis.app.Audio.Buffer;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements Runnable{

   Thread graphicThread = new Thread(this);
   Buffer frameBuffer;

   int width = 0,height = 0;

   //fPS controlling
   private long deltaTime = 0l;
   private long timeStart = 0l;
   private long timeEnd = 0l;

   private long nano = 1000000000;
   private long timeLeftToWait = 0;

   /* |---------- FPS ---------------| */
   private int FPS =  120; //30 , 60 , 120 FPS


   //display
   double positionIndicatorXPos = 0;
   double time;
   double speed;


   public Display(int width , int height)
   {
      this.width = width;
      this.height = height;
      this.setBackground(Color.GRAY);
      this.setPreferredSize(new Dimension(width , height));
   }

   public void start()
   {
      graphicThread.start();
      time = frameBuffer.duration;
      speed = (double) width/time;
   }

   @Override
   public void run()
   {
      long actualRunTime = nano / FPS;
      long finishTime = System.nanoTime();

      while (graphicThread != null)
      {
         long startTime = System.nanoTime();

         deltaTime = startTime - finishTime;
         finishTime = startTime;

         update();
         repaint();

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


   private void update()
   {
      double deltaSeconds = deltaTime / 1_000_000_000.0;
      positionIndicatorXPos += (speed * deltaSeconds);
   }


   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D graphic2D = (Graphics2D) g;
      graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

      showAudioBuffer(graphic2D);
      showPositionIndicator(graphic2D);

   }

   private void showPositionIndicator(Graphics2D g2d)
   {

      g2d.setColor(Color.RED);
      g2d.drawLine((int)positionIndicatorXPos,0,(int)positionIndicatorXPos,100);

      g2d.drawLine(10,0,10,100);



   }


   private void showAudioBuffer(Graphics2D g2d)
   {
      g2d.setColor(Color.BLACK);
      g2d.fillRect(0,0 , width , 100);

      short[] data = frameBuffer.data;

      int pointOffset = data.length/width;
      int x = 1;

      g2d.setColor(Color.WHITE);
      for(int i = 0; i < data.length;i+=pointOffset)
      {
         int height = (data[i] / 2)/100;
         g2d.drawLine(x , 50 - height , x , 50 + height);
         x++;
      }
   }

}
