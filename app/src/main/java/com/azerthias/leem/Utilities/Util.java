package com.azerthias.leem.Utilities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {
    static Activity app;
    public static Resources res;
    public static SharedPreferences pref;
    public static final int packet = 1024;

    public static void start(Activity a){
        app = a;
        res = a.getResources();
        pref = PreferenceManager.getDefaultSharedPreferences(a);
    }

    public static String toString(byte[] b) {
        String s = "";
        for (byte Byte : b) {
            s = s + (char) Byte;
        }
        return s;
    }

    public static byte[] toByte(String s) {
        byte[] tab = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            tab[i] = (byte) s.charAt(i);
        }
        return tab;
    }

    public static byte[] intToByte(int i) {
        byte[] b = { (byte) (i % 256 - 128), (byte) (Math.floor(i / 256) % 256 - 128),
                (byte) (Math.floor(i / 65536) % 256 - 128) };
        return b;
    }

    public static int ByteToInt(byte[] b) {
        int i = b[0] + 128 + (b[1] + 128)* 256 + (b[2] + 128)* 65536;
        return i;

    }

    public static void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) app.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = app.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(app);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int getColor(int id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Vue.a.getColor(id);
        }else{
            return 0;
        }
    }

    public static byte[] image(){
        Routeur r = new Routeur(1,"util-img");
        int l = Util.ByteToInt(r.reponse(3));
        Log.println(Log.ASSERT,"util-img","lenght : " + l);

        int entier = l / packet;
        int reste = l % packet;

        byte[] b = new byte[l];
        int av = 0;


        Routeur r2 = new Routeur(entier * 2 + 1, "util-img2");
        for(int i = 0; i < entier; i++){
            byte[] buf = r2.reponse(packet);
            for(int j = 0; j < packet; j++){
                b[av] = buf[j];
                av++;
            }
            r2.demande(1);

        }


        byte[] buf = r2.reponse(reste);
        for(int j = 0; j < reste; j++){
            b[av] = buf[j];
            av++;
        }
        return b;

    }
}
