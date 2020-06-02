package com.azerthias.leem.Utilities;

import android.os.Build;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.azerthias.leem.authentification.AuthentificationAct;
import com.azerthias.leem.R;

public class Vue {

    public static AuthentificationAct a;
    public static int vue = 0;

    public static TextView tvConnectionErreur, tvVersion, tvRResultat;
    public static EditText user, password, edRSearch;
    public static Button boutonConnection, bVersion, bAdd, bSuivant, bRetour, bRRetour, bRSearch;
    public static CheckBox cbCreation, cbSouvenir;
    public static LinearLayout llM, llR;
    public static ImageView img;
    public static Switch sR;

    public static void setActivity(AuthentificationAct activity){
        a = activity;
    }

    public static void changeView(int view){
        if(vue != view) {

            vue = view;

            switch (view) {
                case 1://connexion
                    a.setContentView(R.layout.authentification);
                    user = a.findViewById(R.id.user);
                    password = a.findViewById(R.id.password);
                    tvConnectionErreur = a.findViewById(R.id.tvConnectionErreur);
                    cbCreation = a.findViewById(R.id.cbCreation);
                    boutonConnection = a.findViewById(R.id.boutonConnection);
                    cbSouvenir = a.findViewById(R.id.cbSouvenir);
                    tvVersion = a.findViewById(R.id.tvVersion);
                    bVersion = a.findViewById(R.id.bVersion);
                    break;
                case 2://connexion
                    a.setContentView(R.layout.menu);
                    bAdd = a.findViewById(R.id.bAdd);
                    llM = a.findViewById(R.id.llM);
                    break;
                case 3:
                    a.setContentView(R.layout.theme);
                    img = a.findViewById(R.id.img);
                    bSuivant = a.findViewById(R.id.bSuivant);
                    bRetour = a.findViewById(R.id.bRetour);
                    break;
                case 4:
                    a.setContentView(R.layout.rejoindre);
                    bRRetour = a.findViewById(R.id.bRRetour);
                    edRSearch = a.findViewById(R.id.edRSearch);
                    bRSearch = a.findViewById(R.id.bRSearch);
                    llR = a.findViewById(R.id.llR);
                    tvRResultat = a.findViewById(R.id.tvRResultat);
                    sR = a.findViewById(R.id.sR);
                    break;


            }
        }
    }


    public static int getColor(int id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return a.getColor(id);
        }else{
            return 0;
        }
    }
}
