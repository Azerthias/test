package com.azerthias.leem.theme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.azerthias.leem.Menu;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;

public class Image {

    private static int n;

    public static void work(String name){
        Vue.changeView(3);
        n = 0;
        Routeur r = new Routeur(2,"img");
        r.demande(2);
        r.demande(name.length());
        r.envoyer(Util.toByte(name));
        final byte nb = r.reponse(1)[0];

        afficher(n);
        Vue.bSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n++;
                if(n >= nb){
                    n = 0;
                }
                afficher(n);
            }
        });

        Vue.bRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu.work();
            }
        });
    }

    private static void afficher(int n){
        Routeur r = new Routeur(2,"img-afficher");
        r.demande(3);
        r.demande(n);
        byte[] buf = Util.image();
        Bitmap bmp = BitmapFactory.decodeByteArray(buf,0,buf.length);
        Vue.img.setImageBitmap(bmp);
    }

}
