package precisionrecall;

import activemq.ActiveMQ;
import ufjf.Video;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class BasePrecisionRecall {

    private HashMap<String, Video> videos;

    int totalVideos;
    
    public BasePrecisionRecall(String path) throws FileNotFoundException {
            videos = new HashMap<>();
            Scanner scanner = new Scanner(new FileReader(path))
                    .useDelimiter("\\n");
            while (scanner.hasNext()) {
                String linha = scanner.next();
                String[] base = linha.split("}");

                for (String b:base){
                    String[] relacoesVideo = b.split(";");
                    if (relacoesVideo[0] == null) continue;

                    Video v =createVideo(relacoesVideo[0].replaceAll(" ", "").substring(1));

                    if (v == null) continue;

           //         System.out.println("video Criado:" + v.getId());

                    if (relacoesVideo.length > 1) {
                        relacoesVideo = relacoesVideo[1].split(",");
                        for (String r : relacoesVideo) {
                            v.addRelacionado(r.replaceAll(" ",""));
             //               System.out.println("related: " + r.replaceAll(" ",""));

                           /* ///salva os vídeos Relacionados no allegrograph
                            StringBuilder buffer = new StringBuilder();
                            buffer.append("<").append(v.getId()).append(">");
                            buffer.append("<dcterms:relatedto>");
                            buffer.append("<").append(r.replaceAll(" ","")).append(">");

                            new ActiveMQ().sendMessagetoRdfStore(buffer.toString());
                            */
                        }
                    }
                    addVideo(v);
                }

            }
    }

    private Video createVideo(String id){
        if (id.startsWith("http")){
            try {
                Video v = new Video(id.replaceAll(" ",""));
                addVideo(v);
                return v;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void addVideo(Video v){
        Video existente = videos.get(v.getId());

        if (existente == null){
            videos.put(v.getId(),v);
            totalVideos++;
        }
    }

    public void calcule(int descricao, Video v){
        //verificar se o video está na base
        Video ref = videos.get(v.getId());

        if (ref == null) {
           // System.out.println("Não é possivel responder pois não existe referencias para este vídeo");
            return;
        }

        if (ref.getVideosRelacionados().size() == 0) return;

        if (totalVideos == 0) return;

        int truePositeve = 0, falsePositive = 0 , FN = 0;

        ArrayList<String> relacionados = v.getVideosRelacionados();

        for (String idVideoRetornado: relacionados){

            if (ref.getVideosRelacionados().contains(idVideoRetornado)){
               truePositeve ++;
            }
            else{
               falsePositive ++;
            }

        }

        //System.out.println("Video: "+v.getId());
        //System.out.println("Total referencias: "+ref.getVideosRelacionados().size());
        //System.out.println("Total Retornados: "+truePositeve + falsePositive);


        int trueNegative = totalVideos - (ref.getVideosRelacionados().size() + falsePositive);

        float precision = 0f;

        if (truePositeve + falsePositive != 0f ) ;
            precision = (float) truePositeve / (truePositeve + falsePositive);

        float recall =  (float) truePositeve / ref.getVideosRelacionados().size();

        float acuracy = (float) (truePositeve + trueNegative) / totalVideos;

        //Impressão dos resultados

        System.out.println("Vídeo " + descricao + "  &  " + truePositeve +" & " + ""+falsePositive +" & " + ""+precision + " & " + ""+recall + " & " + ""+acuracy+"\\\\");

        //System.out.println(rpad(""+truePositeve) +"\t" +rpad(""+falsePositive) +"\t" + rpad(""+precision) + "\t" + rpad(""+recall) + "\t" + rpad(""+acuracy)+"\t"+v.getId());


    }


    private String rpad(String valor){
        /*String espacos10 = "          ";
        valor += espacos10;
        return valor.substring (0, 10);*/
        return valor;
    }

    public int count(){
        return videos.size();
    }
}
