package com.azerthias.leem;

import android.view.View;
import android.widget.Button;

import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;
import com.azerthias.leem.theme.Image;
import com.azerthias.leem.theme.Rejoindre;

public class Menu {
    public static void work(){
        Vue.changeView(2);
        Routeur r = new Routeur(2,"menu");
        r.demande(1);
        byte nb = r.reponse(1)[0];
        Routeur r2 = new Routeur(nb * 2,"menu2");
        for(int i = 0; i < nb; i++){
            byte size = r2.reponse(1)[0];
            Button b = new Button(Vue.a);
            b.setText(Util.toString(r2.reponse(size)));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Image.work(((Button)v).getText().toString());

                }
            });
            Vue.llM.addView(b);
        }

        Vue.bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rejoindre.work();
            }
        });
    }
}
