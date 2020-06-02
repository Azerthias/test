package com.azerthias.leem.Utilities;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class Routeur {

    static Socket sock;
    static int nbInstance = 0;
    static int autInstance = 0;
    static int nbEnvoi = 0;
    static String sHelp;

    int demande;
    int instance;
    static int time = 0;
    String help;

    public Routeur(int demande, String help) {
        if(demande > 0) {
            this.demande = demande;
            this.help = help;
            nbInstance++;
            instance = nbInstance;

            Log.wtf("Routeur", "nouvelle instance : " + help);
        }

    }

    public void stop(){
        nbEnvoi = 0;
    }


    public boolean authorized() {
        if(time >= 100){
            time = 0;
            autInstance++;
        }
        if((nbEnvoi <= 0 && autInstance + 1 == instance) || autInstance  == instance) {
            nbEnvoi = demande;
            autInstance = instance;
            sHelp = help;

            Log.wtf("Routeur","Authorisation pour : " + autInstance  + " alias : "  + sHelp + " pour envoyer/recevoir : " + nbEnvoi);
            return true;
        }else {
            time++;
            return false;
        }


    }



    public static void setSock(Socket s) {
        sock = s;
    }

    public byte[] reponse(int l){
        if(autInstance != instance) {
            while(!authorized()) {
                Log.wtf("Routeur","En attente d'authorisation pour : " + instance + ", encore " + nbEnvoi + "envoi pour l'instance authorisé (" + autInstance + ")");
            }
        }

        byte[] b = new byte[l];
        try {
            sock.getInputStream().read(b);
        }catch(IOException ex) {
            b = null;
            ex.printStackTrace();
        }
        nbEnvoi--;
        return b;

    }

    public void envoyer(byte[] buf) {
        if(autInstance != instance) {
            while(!authorized()) {
                Log.wtf("Routeur","En attente d'authorisation pour : " + instance + ", encore " + nbEnvoi + "envoi pour l'instance authorisé (" + autInstance + ")");
            }
        }
        try {
            sock.getOutputStream().write(buf, 0, buf.length);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        nbEnvoi--;
    }

    public void demande(int dem) {
        if(autInstance != instance) {
            while(!authorized()) {
                Log.wtf("Routeur","En attente d'authorisation pour : " + instance + ", encore " + nbEnvoi + "envoi pour l'instance authorisé (" + autInstance + ")");
            }
        }
        try {
            byte[] buf = {(byte)dem};
            sock.getOutputStream().write(buf, 0, 1);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        nbEnvoi--;
    }
}
