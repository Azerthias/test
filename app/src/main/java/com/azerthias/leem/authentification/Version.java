package com.azerthias.leem.authentification;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.azerthias.leem.R;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;

public class Version {

    public static boolean isGood(){
        Routeur r = new Routeur(3,"version");
        byte[] v = Util.toByte(Vue.a.getString(R.string.version));
        r.demande(v.length);
        r.envoyer(v);
        return r.reponse(1)[0] == 0;
    }

    public static void bouton(){
        Routeur r = new Routeur(2,"bouton");
        final String url = Util.toString(r.reponse(r.reponse(1)[0]));
        Vue.bVersion.setVisibility(View.VISIBLE);
        Vue.bVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(Vue.a.getPackageManager()) != null) {
                    Vue.a.startActivity(intent);
                }
                System.exit(0);
            }
        });
    }


}
