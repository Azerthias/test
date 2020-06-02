package com.azerthias.leem.Utilities;



import java.io.IOException;
import java.net.Socket;

public class Update {




    public static void connexion(final String ip, final int portOut, final String name){

        try {
            Routeur r = new Routeur(1,"update");
            byte[] nb = r.reponse(1);
            Socket upd = new Socket(ip,portOut);
            upd.getOutputStream().write(nb, 0, 1);

            Runnable th = new Runnable() {
                @Override
                public void run() {
                    /*while(true){

                        byte[] buf = new byte[1];
                        upd.getInputStream().read(buf);

                        final byte b = buf[0];
                        Vue.a.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                switch (b) {


                                }
                            }
                        });*/




                }; };
            Thread t = new Thread(th);
            t.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
