package facete;

import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.UnsupportedEncodingException;

public class Facetas {

    public static final Video getVideo()throws UnsupportedEncodingException {
        Video video= new Video("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");

        return video;
    }


}
