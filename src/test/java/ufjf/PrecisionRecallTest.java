package ufjf;

import org.junit.Test;
import precisionrecall.Analise;
import precisionrecall.BasePrecisionRecall;

import java.io.FileNotFoundException;

/**
 * Created by jayme on 04/06/15.
 */
public class PrecisionRecallTest {
    @Test
    public void criarObjAnaliseTest() throws FileNotFoundException {
        new BasePrecisionRecall("/home/jayme/Documents/qodra/busca/qodra.txt");

    }

    @Test
    public void analiseTest() throws FileNotFoundException {
        Analise analise = new Analise("/home/jayme/Documents/qodra/busca/qodra.txt");

        analise.calculatePrecision();
    }

}