package com.spectrogramAnalysis.app.window;

import com.spectrogramAnalysis.app.controller.Display;
import com.spectrogramAnalysis.app.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class Window {

   JFrame window = new JFrame();

                        //bro chill with the abstraction man
   public void openWindow(String[] args)
   {

      Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
      MainController controller = new MainController(r.width , r.height);
      controller.loadAudio(args);

      window.setSize(r.width , r.height);
      window.setExtendedState(JFrame.MAXIMIZED_BOTH);
      window.setLocationRelativeTo(null);
      window.setUndecorated(true);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      System.out.println(r.width);
      window.add(controller.display);
      window.setVisible(true);

      controller.start();
   }
}
