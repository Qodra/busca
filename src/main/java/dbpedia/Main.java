package dbpedia;


import activemq.Allegro;
import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static void main(String args[]) throws Exception {

        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        //String id = "0";
        while (true) {
            /*
            try {
                System.out.println("Entre com id do video para fazer uma nova consulta ou digite \"0\" para sair: ");
                id = ler.readLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            if ("0".equals(id)) {
                break;
            }
            else {
                Video video = new Video(id);
            }
            */

            ArrayList<String> idsVideos = GetVideosUFJF.getAllId();

            for(String id : idsVideos){
                ArrayList<String> relaciondos = DBPedia.getResourcesRelated(new Video(id));

                Allegro base = new Allegro();

                StringBuilder nt = new StringBuilder();

                for (String rel:relaciondos){
                    base.addTriple(id, "dcterms:references2", rel);
                }
            }

        }

    }




}
