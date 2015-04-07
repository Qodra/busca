package ufjf;

import Utils.JSONBuilder;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import scala.util.parsing.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class GetVideosUFJFTest {

    @Test
    public void getReferencesTest(){

        ArrayList<String> references = null;
        try {
            references = GetVideosUFJF.getRefences("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (String s:references) {

            System.out.println(s);

        }

    }

    @Test
    public void getTitleTest(){
        String s = null;
        try {
            s = GetVideosUFJF.getTitle("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getCourseTest(){
        String s = null;
        try {
            s = GetVideosUFJF.getCourse("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getKeywordsTest(){
        ArrayList<String> s = null;
        try {
            s = GetVideosUFJF.getKeywords("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getDateTest(){
        Date d;
        try {
            d = GetVideosUFJF.getDate("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
