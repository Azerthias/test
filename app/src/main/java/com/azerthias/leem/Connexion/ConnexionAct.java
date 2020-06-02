package com.azerthias.leem.Connexion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.azerthias.leem.File.Save;
import com.azerthias.leem.R;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.authentification.Authentification;
import com.azerthias.leem.authentification.Version;

import java.io.IOException;
import java.net.Socket;

public class ConnexionAct extends Activity {

    boolean test = true;
    public static int portIn = 31415;
    public static int portOut = 31416;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ip = getString(R.string.ip);
        if (test) {
            portIn = 23107;
            portOut = 23108;
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Socket sock = new Socket(ip, portIn);
            Routeur.setSock(sock);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex2){

        }

        if(Version.isGood()){
            if(Save.get()){
                startOut();
            }else{
            Authentification.work(false);
            }
        }else{
            Authentification.work(true);
        }
    }


}
