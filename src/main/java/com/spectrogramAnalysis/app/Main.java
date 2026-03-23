package com.spectrogramAnalysis.app;


import com.spectrogramAnalysis.app.Audio.Buffer;
import com.spectrogramAnalysis.app.loader.AudioLoader;

public class Main
{
    public static void main( String[] args )
    {
        AudioLoader loader = new AudioLoader();
        Buffer buffer = new Buffer();

        System.out.println(args[0]);
        loader.load(args[0] , buffer);

    }
}
