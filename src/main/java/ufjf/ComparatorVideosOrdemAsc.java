package ufjf;

import java.util.Comparator;

/**
 * Created by jayme on 06/02/16.
 */
public class ComparatorVideosOrdemAsc implements Comparator<Video> {

    public int compare(Video v1, Video v2){
        if (v1.getTotalCategoriaRelacionadas() < v2.getTotalCategoriaRelacionadas()) return -1;

        else if (v1.getTotalCategoriaRelacionadas() > v2.getTotalCategoriaRelacionadas()) return +1;

        else return 0;
    }
}
