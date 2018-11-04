package com.example.oving_6_sockets_tjener;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandlerThread extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String tag = "Tjener_Thread_Handler";

    public ClientHandlerThread(Socket s) {
        socket = s;
        out = null;
        in = null;
    }

    public void run() {
        Log.v(tag, "Klient tilkoblet...");
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Velkommen Klient....");                    //0
            Log.i(tag, "Velkomstmelding sendt, venter på data");

            //Henter tall fra bruker
            String res = in.readLine();                             //1
            Log.i(tag, "Mottat tall: " + res);
            String tallene[] = res.split(",");
            Log.v(tag, "Gjort mottat info til array");

            //Caster til int, summerer og returnerer
            int svar = Integer.parseInt(tallene[0].toString()) + Integer.parseInt(tallene[1].toString());
            Log.i(tag, "Sum = " + svar);
            String svarTekst = "" + svar;
            out.println(svarTekst);                                 //2
            Log.i(tag, "Sum returnert til klient: " + svar);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            Log.e(tag, "IO: " + ioe.getMessage());
        } finally {
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                Log.e(tag, "IOE: " + ioe.getMessage());
            }
        }

    }
}
