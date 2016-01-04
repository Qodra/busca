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

            ArrayList<Video> listaVideos = new ArrayList<>();



            //para cada video recupera todas as suas categorias
            for (String idVideo:idVideos){


                ArrayList<String> categories = GetVideosUFJF.getCategories(idVideo);
                if (!categories.isEmpty()) {
                    Video video = new Video(idVideo);
                    //para cada categoria procura todos os videos que pertecem a ela
                    for (String category : categories) {
                       // System.out.println("Categoria: " + category);
                        ArrayList<String> videosRelacionados = GetVideosUFJF.getVideoByCategories(category);

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
