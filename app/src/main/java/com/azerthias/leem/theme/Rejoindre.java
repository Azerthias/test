package com.azerthias.leem.theme;

import android.view.View;
import android.widget.Button;

import com.azerthias.leem.Menu;
import com.azerthias.leem.R;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;

public class Rejoindre {
    public static void work(){
        Vue.changeView(4);

        Vue.sR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Vue.llR.removeAllViews();
                if(Vue.sR.isChecked()){
                    Vue.bRSearch.setText("créer");
                    Vue.tvRResultat.setText("");
                    Vue.tvRResultat.setTextColor(Util.getColor(R.color.error));
                }else{
                    Vue.bRSearch.setText("rechercher");
                    Vue.tvRResultat.setText("résultat");
                    Vue.tvRResultat.setTextColor(Util.getColor(R.color.black));
                }

            }
        });

        Vue.bRSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Vue.llR.removeAllViews();

                String dem = Vue.edRSearch.getText().toString();

                if (dem.length() != 0) {
                    Util.hideKeyboard();
                    byte[] buf = Util.toByte(dem);

                    if(Vue.sR.isChecked()){
                        Routeur r = new Routeur(4, "créer");
                        r.demande(6);
                        r.demande(dem.length());
                        r.envoyer(buf);
                        byte[] rep = r.reponse(1);
                        switch(rep[0]){
                            case 1:
                                Util.hideKeyboard();
                                Image.work(dem);
                                break;
                            case 2:
                                Vue.tvRResultat.setText("Ce groupe éxiste déjà");
                                break;
                        }
                    }else {

                        Routeur r = new Routeur(4, "rejoindre 1");
                        r.demande(4);
                        r.demande(dem.length());
                        r.envoyer(buf);
                        byte[] l = r.reponse(1);

                        Routeur r2 = new Routeur(l[0] * 2, "rejoindre 2");
                        for (int i = 0; i < l[0]; i++) {
                            byte[] lR = r2.reponse(1);
                            byte[] retour = r2.reponse(lR[0]);
                            final String theme = Util.toString(retour);
                            Button b = new Button(Vue.a);
                            b.setText(theme);
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    byte[] buf = Util.toByte(theme);
                                    Routeur r = new Routeur(3, "addTheme");
                                    r.demande(5);
                                    r.demande(theme.length());
                                    r.envoyer(buf);
                                    Menu.work();
                                }
                            });
                            Vue.llR.addView(b);

                        }


                    }
                }
            }
        });

        Vue.bRRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Util.hideKeyboard();
                Menu.work();
            }
        });
    }
}
