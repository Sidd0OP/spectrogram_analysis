package com.spectrogramAnalysis.app;


import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.loader.AudioLoader;
import com.spectrogramAnalysis.app.window.Window;

public class Main
{
    public static void main( String[] args )
    {
        Window window = new Window();
        window.openWindow(args);
    }
}
