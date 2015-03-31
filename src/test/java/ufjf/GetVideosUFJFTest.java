package ufjf;

import Utils.JSONBuilder;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import scala.util.parsing.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GetVideosUFJFTest {

    @Test
    public void testeValores(){


        ArrayList<Triple> triplas = null;
        try {
            triplas = GetVideosUFJF.getAll();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (Triple t: triplas) {

            System.out.println(t.getSujeito());
            System.out.println(t.getPredicado());
            System.out.println(t.getObjeto());

            System.out.println("");


        }

    }
}
