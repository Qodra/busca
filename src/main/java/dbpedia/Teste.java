package dbpedia;


import com.github.kevinsawicki.http.HttpRequest;
import com.sun.deploy.net.URLEncoder;

import java.net.URI;


public class Teste {
    public static void main(String args[]){
        String ENCODING = "UTF-8";


        String requisicaoQodra = HttpRequest.get("http://200.131.219.214:10035/repositories/qodra?query=select%20%3Fs%20%3Fp%20%3Fo%20%7B%3Fs%20%3Fp%20%3Fo%7D&queryLn=SPARQL&infer=false&uuid=p3d1s1xuh7y95mazl3orh&returnQueryMetadata=true")
                .accept("application/json").body();

        System.out.println(requisicaoQodra);

        //FaturaFiltro filtro = JSONBuilder.getGson().fromJson(json, FaturaFiltro.class);

       // String requisicaoDBpedea = HttpRequest.get("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+distinct+%3FConcept+where+%7B%5B%5D+a+%3FConcept%7D+LIMIT+100&format=text%2Fhtml&timeout=30000&debug=on")
       //         .accept("application/json").body();

      //  System.out.println(requisicaoDBpedea);


    }




}
