package com.azerthias.leem.File;

import android.content.SharedPreferences;
import android.util.Log;

import com.azerthias.leem.authentification.AuthentificationAct;
import com.azerthias.leem.Utilities.Routeur;
import com.azerthias.leem.Utilities.Util;
import com.azerthias.leem.Utilities.Vue;


public class Save {

    public static void work(){

        if(Vue.cbSouvenir.isChecked()){

        SharedPreferences.Editor editor = Util.pref.edit();
        editor.putString("name", AuthentificationAct.name);
        editor.putString("mdp", AuthentificationAct.mdp);
        editor.commit();
        }
    }

    public static boolean canRestart(){
        String name = Util.pref.getString("name", null);
        String mdp = Util.pref.getString("mdp", null);
        return name != null && mdp != null;
    }

    public static boolean get(){

        String name = Util.pref.getString("name", null);
        String mdp = Util.pref.getString("mdp", null);
        Log.wtf("get",name);
        Log.wtf("get",mdp);

        if(name != null && mdp != null){
            Routeur r = new Routeur(6,"reconnexion");
            r.demande(0);
            r.demande(0);
            r.demande(name.length());
            r.envoyer(Util.toByte(name));
            r.demande(mdp.length());
            r.envoyer(Util.toByte(mdp));

            if(r.reponse(1)[0] == 1){
                AuthentificationAct.name = name;
                AuthentificationAct.mdp = mdp;
                return true;
            }else{
                return false;
            }
        }else{

            return false;
        }
    }
}
