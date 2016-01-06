package precisionrecall;


import ufjf.Video;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Analise {

    private ArrayList<Video> videosRelacionados;

    private BasePrecisionRecall pr;

    public Analise(String arquivo) throws FileNotFoundException {
        pr = new BasePrecisionRecall(arquivo);

        videosRelacionados = BuscaRelacoes.getVideosRelacionados();
    }

    public void calculatePrecision(){
        //System.out.println("TP        \tFP        \tPrecision \tRecall    \tAcuracy   \tId Video");
        System.out.println("\\begin{table}[h]");
        System.out.println("\\centering");
        System.out.println("\\caption{Abordagem Top N}");
        System.out.println("\\vspace{0.5cm}");
        System.out.println("\\begin{tabular}{|c|c|c|c|c|c|c|}");
        //System.out.println("		 & \\multicolumn{2}{|c|}{$\\alpha = 0.1$}    & \\multicolumn{2}{|c|}{$\\alpha = 0.2$} & \\multicolumn{2}{|c|}{$\\alpha = 0.3$} & \\\\");
        System.out.println("\\hline");
        System.out.println("Video	 &  Esperados & Retornados Certos & Total Retornados & Precisão	& Recall & Top N	\\\\");
        //System.out.println("Video	 &  TP	& FP & Precisão	& Recall & Acurácia	\\\\");
        System.out.println("\\hline");

        int i = 1;
        for (Video v: videosRelacionados){

            pr.calcule(i,v, true);
            i++;

        }
        System.out.println("\\hline");
        System.out.println("\\end{tabular}");
        System.out.println("\\end{table}");

    }
}
