package facete;

import dbpedia.DBPedia;
import dbpedia.DBPediaPT;
import ufjf.Video;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Facetas {

    public static final Video getVideo()throws UnsupportedEncodingException {
        Video video= new Video("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencia_da_computacao/DCC116/Aula08/Aula08.xml");

        return video;
    }

    public ArrayList<String> building(Video v){
        ArrayList<String> sameAS;
        String same;
        for (String s:v.getVideosRelacionados()){
            sameAS = DBPedia.getResourceSameAsPT(s);
            same = sameAS.get(0);
        }
        return null;
    }

    public static final ArrayList<String> getFacetas(Video video){

        ArrayList<String> facetas = new ArrayList<>();
        ArrayList<String> relacionados = video.getReferences();

        for (String rel: relacionados){

            facetas.addAll(DBPediaPT.getLabelPT(DBPedia.getResourceSameAsPT(rel).get(0)));

        }

        return facetas;
    }
}
