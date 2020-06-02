package com.azerthias.leem.authentification;


import android.view.View;


import com.azerthias.leem.File.Save;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;

import java.io.UnsupportedEncodingException;

public class Authentification{

    public static void version(){
        Routeur r = new Routeur(3,"msg version");
        r.demande(1);
        int l = Util.ByteToInt(r.reponse(3));

        byte[] buf = r.reponse(l);

        for(byte b : buf){
            try {
                Vue.tvVersion.append(new String(new byte[]{b}, "ISO-8859-15"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void work(boolean version){
        Vue.changeView(1);
        if(version){
            Version.bouton();
        }
        version();
        Vue.boutonConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Vue.user.getText().toString().length() != 0 && Vue.password.getText().toString().length() != 0) {
                    Util.hideKeyboard();

                    Routeur r = new Routeur(6, "Connexion");

                    byte[] dem = {(byte)(Vue.cbCreation.isChecked()?1:0)};
                    r.envoyer(dem);

                    r.demande(Vue.user.length());
                    r.envoyer(Util.toByte(Vue.user.getText().toString()));

                    r.demande(Vue.password.length());
                    r.envoyer(Util.toByte(Vue.password.getText().toString()));

                    switch (r.reponse(1)[0]) {
                        case 1:
                            AuthentificationAct.name = Vue.user.getText().toString();
                            AuthentificationAct.mdp = Vue.password.getText().toString();
                            Save.work();
                            Vue.a.startOut();
                            //}
                            break;
                        case 2:
                            Vue.tvConnectionErreur.setText("Mot de passe faux");
                            break;
                        case 3:
                            Vue.tvConnectionErreur.setText("Ce compte n'existe pas");
                            break;
                        case 4:
                            Vue.tvConnectionErreur.setText("Ce compte éxiste déja");
                            break;
                        case 5:
                            Vue.tvConnectionErreur.setText("Le nom d'utilisateur et le mot de passe ne peuvent pas être vide");
                            break;
                    }
                }
            }
        });
    }

}