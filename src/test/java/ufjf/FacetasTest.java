package ufjf;

import dbpedia.DBPediaPT;
import org.junit.Test;

import java.util.ArrayList;

public class FacetasTest {
    @Test
    public void getFacetasTest(){
        ArrayList<String> facetas  =
        DBPediaPT.getLabelPT("http://pt.dbpedia.org/resource/Algoritmo");

        for (String str: facetas){
            System.out.println(str);
        }
    }
}
