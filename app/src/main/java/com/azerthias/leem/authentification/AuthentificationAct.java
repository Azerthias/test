package com.azerthias.leem.authentification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.azerthias.leem.R;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Update;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;
import com.azerthias.leem.menu.MenuAct;

import java.io.IOException;
import java.net.Socket;

public class AuthentificationAct extends Activity {

    boolean test = true;
    public static int portIn = 31415;
    public static int portOut = 31416;
    public static String ip;//82.239.85.165 - 192.168.43.119
    Socket sock;
    public static String name;
    public static String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vue.setActivity(this);
        Vue.changeView(0);

        startIn();
        start();


    }

    public void start(){
        if(Version.isGood()){
            /*if(Save.get()){
                startOut();
            }else{*/
                Authentification.work(false);
            //}
        }else{
            Authentification.work(true);
        }
    }

    public void startIn(){
        ip = getString(R.string.ip);
        if (test) {
            portIn = 23107;
            portOut = 23108;
        }
        Util.start(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            sock = new Socket(ip, portIn);
            Routeur.setSock(sock);


        } catch (IOException ex) {
            ex.printStackTrace();
            showDialog(1);

        } catch (Exception ex2){
            showDialog(0);
        }
    }

    public void startOut(){



        Update.connexion(ip, portOut, name);
        Intent secondeActivite = new Intent(AuthentificationAct.this, MenuAct.class);
        startActivity(secondeActivite);
        //Menu.work();
    }




}
