package ufjf;

import dbpedia.DBPedia;
import facete.Facetas;
import org.junit.Test;
import scala.util.parsing.combinator.testing.Str;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DBPediaTest {
    @Test
    public void dbPediaTest() throws UnsupportedEncodingException {

        Video video = Facetas.getVideo();

        ArrayList<String> correlatos = new ArrayList<String>();

        for (String s:video.getReferences()) {
            ArrayList<String> references = DBPedia.getResource("<" + s + ">", 2);

            for (String str:references){
                correlatos.add(str);
            }

            if (DBPedia.languageIsPt(s)){
                references = DBPedia.getResourceSameAs("<" + s + ">");

                for (String s2:references){
                    references = DBPedia.getResource("<" + s2 + ">", 2);

                    for (String str:references){
                        correlatos.add(str);
                    }
                }
            }
        }

        /*for (String novoAssunto :correlatos){
            video.addReference(novoAssunto);
        }
        /*for (String s: video.getReferences()){
            System.out.println(s);
        }*/

    }

    @Test
    public void getPropertiesTest() throws UnsupportedEncodingException {
        String str = DBPedia.getProperties("<http://dbpedia.org/resource/Category:Problem_solving>", "dcterms:subject");

        str = str.replaceAll("[\"\']","");

        String[] resources =  str.split("\n");

        for (String s:resources) {
            //if ((DBPedia.languageIsPt(s)) || (DBPedia.languageIsEn(s)))
                System.out.println(s);
        }
    }

    @Test
    public void getResourcesTest() throws UnsupportedEncodingException {
        DBPedia.getResource("<http://dbpedia.org/resource/Heuristic>",2);
    }

    @Test
    public void getResourcesSamenAsTest() throws UnsupportedEncodingException {
        ArrayList<String> sameAs =  DBPedia.getResourceSameAs("<http://pt.dbpedia.org/resource/Categoria:Solução_de_problemas>");

        for (String s:sameAs){
            System.out.println(s);
        }
    }

}

