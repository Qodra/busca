package dbpedia;


import facete.Facetas;
import scala.collection.generic.BitOperations;
import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) throws Exception {

//        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        //String id = "0";
        //while (true) {
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

            //ArrayList<String> idsVideos = new ArrayList<String>();
                //idsVideos.add("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/fisica/fisica2/cap17/temperaturaecalor.xml");

            for(String id : idsVideos){
                Video v = new Video(id);

               // for (String r: v.getReferences())
               //     System.out.println(id);
               // int teste;
                //System.out.println("Pesquisar Video? [0 1]");

               // Scanner ler = new Scanner(System.in);
               // teste = ler.nextInt();
               // System.out.println("valor lido: "+teste);
               // if (teste == 1) {
               //     System.out.println("Buscando");
                    DBPedia.getResourcesRelated(v);
               // }
                //Facetas.getFacetas(v);

            }

        }

    //}


    public static void getVideoRelaciondado(){

    }

}
