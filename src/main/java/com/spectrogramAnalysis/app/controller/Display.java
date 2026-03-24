package com.spectrogramAnalysis.app.controller;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements Runnable{

   Thread graphicThread = new Thread(this);
   int width = 0,height = 0;

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
   }

   @Override
   public void run()
   {
      while(graphicThread != null)
      {

//         System.out.println("thread threading");
//         update();
//         repaint();
      }
   }


   private void update()
   {

   }


   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D graphic2D = (Graphics2D) g;
      graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);

   }

}
