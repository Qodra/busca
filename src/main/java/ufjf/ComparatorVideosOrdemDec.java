package ufjf;

import java.util.Comparator;

public class ComparatorVideosOrdemDec implements Comparator<Video> {
    //Ordem decrescente
    public int compare(Video v1, Video v2) {

        if (v1.getTotalCategoriaRelacionadas() > v2.getTotalCategoriaRelacionadas()) return -1;

        else if (v1.getTotalCategoriaRelacionadas() < v2.getTotalCategoriaRelacionadas()) return +1;

        else return 0;

    }
}
