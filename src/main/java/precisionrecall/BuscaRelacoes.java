package precisionrecall;

import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BuscaRelacoes {

    public static ArrayList<Video> getVideosRelacionados(){
        try {
            //Recupera todos os ids de videos
            ArrayList<String> idVideos = GetVideosUFJF.getAllId();
            //ArrayList<String> idVideos = new ArrayList<>();
            //idVideos.add("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencia_da_computacao/DCC116/Aula05/Aula05.xml");
            ArrayList<Video> listaVideos = new ArrayList<>();

            for (String idVideo:idVideos){
                //para cada video recupera todas as suas categorias
                ArrayList<String> categories = GetVideosUFJF.getCategories(idVideo);

                Video video = new Video(idVideo);

                if (!categories.isEmpty()) {

                    //para cada categoria procura todos os videos que pertecem a ela
                    for (String category : categories) {
                       // System.out.println("Categoria: " + category);
                        ArrayList<String> videosRelacionados = GetVideosUFJF.getVideosByCategories(category);

                        for (String videoRelacionado:videosRelacionados){

                            video.addRelacionado(videoRelacionado);
                        }
                    }
                    listaVideos.add(video);
                }


            }

            return listaVideos;

        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }
}
