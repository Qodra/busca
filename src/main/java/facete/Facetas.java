package facete;

import activemq.ActiveMQ;
import dbpedia.DBPedia;
import dbpedia.DBPediaPT;
import ufjf.Video;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Facetas {

    public ArrayList<String> building(Video v){
        ArrayList<String> sameAS;
        String same;
        /*for (String s:v.getVideosRelacionados()){
            sameAS = DBPedia.getResourceSameAsPT(s);
            same = sameAS.get(0);
        }*/
        return null;
    }

    public static final ArrayList<String> getFacetas(Video video){

        ArrayList<String> facetas = new ArrayList<>();
        ArrayList<String> references = video.getReferences();

        for (String ref:references){
            if (!DBPedia.languageIsEn(ref)){
                ArrayList<String> sameAs = DBPedia.getResourceSameAs(ref);

                if (!sameAs.isEmpty()){
                    ref = sameAs.get(0);
                }
            }

            //System.out.println(ref);
            facetas.addAll(DBPedia.getLabelpt(ref));

        }

        for (String faceta:facetas){
            StringBuilder buffer = new StringBuilder();
            buffer.append("<").append(video.getId()).append(">");
            buffer.append("<dcterms:facet>");
            buffer.append("<").append(faceta).append(">");
            //System.out.println(buffer.toString());
            new ActiveMQ().sendMessagetoRdfStore(buffer.toString());

        }
        return facetas;
    }

}
