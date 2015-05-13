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

            ArrayList<String> references;
            if (DBPedia.languageIsPt(s)){

                references = DBPedia.getResourceSameAs("<" + s + ">");

                for (String s2:references){
                    System.out.println("SameAs "+s2);
                    references = DBPedia.getResource("<" + s2 + ">", 1);

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
        String str = DBPedia.getProperties("<http://dbpedia.org/resource/Problem_solving>", "dcterms:subject");

        str = str.replaceAll("[\"\']","");

        String[] resources =  str.split("\n");

        for (String s:resources) {
            //if ((DBPedia.languageIsPt(s)) || (DBPedia.languageIsEn(s)))
                System.out.println(s);
        }
    }

    @Test
    public void getResourcesTest() throws UnsupportedEncodingException {
        DBPedia.getResource("<http://dbpedia.org/resource/Heuristic>",10);
    }

    @Test
    public void getResourcesSamenAsTest() throws UnsupportedEncodingException {
        ArrayList<String> sameAs =  DBPedia.getResourceSameAs("<http://pt.dbpedia.org/resource/Categoria:Solução_de_problemas>");

        for (String s:sameAs){
            System.out.println(s);
        }
    }

    @Test
    public void getResourceByCategoryTest() {
        ArrayList<String> resources = DBPedia.getResourcesByCategory("http://dbpedia.org/resource/Category:Problem_solving");

        if (resources != null) {
            for (String s: resources){
                System.out.println(s);
            }
        }
    }
    @Test
    public void getCategoryByResourceTest() {
        ArrayList<String> categories = DBPedia.getCategoryByResource("http://dbpedia.org/resource/Problem_solving");

        if (categories != null) {
            for (String s: categories){
                System.out.println(s);
            }
        }
    }

    @Test
    public void getClassByResourceTest(){
        ArrayList<String> classes = DBPedia.getClassByResource("http://dbpedia.org/resource/Evolutionary_algorithm");

        if (classes != null){
            for (String s: classes){
                System.out.println(s);
            }

        }
    }

    @Test
    public void getResourceByClassTest(){
        ArrayList<String> resources = DBPedia.getResourcesByClass("http://dbpedia.org/class/yago/Abstraction100002137");

        if (resources != null){
            for (String s: resources){
                System.out.println(s);
            }

        }
    }

    @Test
    public void getResourcesRelatedTest() throws UnsupportedEncodingException {
        Video video = Facetas.getVideo();
        for (String s:video.getReferences()){
            System.out.println(s);
        }
        DBPedia.getResourcesRelated(video);
    }
}

