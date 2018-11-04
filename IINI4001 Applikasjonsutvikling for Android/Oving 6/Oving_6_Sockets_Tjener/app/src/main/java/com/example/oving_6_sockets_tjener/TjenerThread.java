package com.example.oving_6_sockets_tjener;

import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TjenerThread extends Thread {
    private final static int port = 12345;
    private final static String tag = "Tjener_Thread";

    @Override
    public void run() {
        ServerSocket ss = null;

        try {
            Log.i(tag, "Start server...");
            ss = new ServerSocket(port);
            Log.i(tag, "ServerSocket opprettet, venter på klient...");
            while (!isInterrupted()) {
                Socket s = ss.accept();
                new ClientHandlerThread(s).start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}