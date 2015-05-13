package facete;

import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.UnsupportedEncodingException;

public class Facetas {

    public static final Video getVideo()throws UnsupportedEncodingException {
        Video video= new Video("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencia_da_computacao/DCC116/Aula08/Aula08.xml");

        return video;
    }


}
