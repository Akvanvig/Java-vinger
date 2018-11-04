package com.example.oving_6_sockets_klient;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlientThread extends Thread {
    private static String ipTjener;
    private static int portTjener;
    private String tag = "Klient_Thread";
    private KlientActivity klient;

    public KlientThread(KlientActivity k, String ip, int port) {
        ipTjener = ip;
        portTjener = port;
        klient = k;
    }

    @Override
    public void run() {
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;

        String tall1 = "";
        String tall2 = "";
        String svar = "";

        String[] tallene = klient.hentTall();

        tall1 = tallene[0];
        tall2 = tallene[1];

        Log.i(tag, "Tall 1: " + tall1 + ", Tall 2: " + tall2);

        try {
            //Oppretter tilkobling til Tjener
            s = new Socket(ipTjener, portTjener);
            Log.v(tag, "Tilkoblet tjener...");
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //Venter på velkomstmelding
            String res = in.readLine();                     //0
            Log.i(tag, "Melding mottat: " + res);

            //Sender tall
            String mld = tall1 + "," + tall2;
            Log.i(tag, "Sender: " + mld);
            out.println(mld);                       //1
            Log.v(tag, "Melding sendt, venter på svar");

            //Mottar svar fra tjener
            svar = in.readLine();                   //2
            Log.i(tag, "Svar mottat: " + svar);

            //Skriver til gui
            String respons = tall1 + " + " + tall2 + " = " + svar;
            klient.skrivTilLabel(respons);
            Log.v(tag, "Skrevet til gui");

        } catch (IOException ioe) {
            Log.e(tag, ioe.toString());
        } catch (Exception e){
            Log.e(tag, "Tilkobling: " + e.toString());
        } finally {
            try {
                out.close();
                in.close();
                s.close();
            } catch (Exception e) {
                Log.e(tag, "Lukking: " + e.toString());
            }
        }

    }

}
