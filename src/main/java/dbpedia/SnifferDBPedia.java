package dbpedia;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SnifferDBPedia {

    public static final void getProperties(String tag) throws UnsupportedEncodingException {
        //String requisicaodbpedea = HttpRequest.get("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+%3Fs+%3Fp+%3Fo+{%3Fs+%3Fp+%3Fo}+LIMIT+100&timeout=30000&debug=on")
        //        .accept("application/json").body();

        String textoEncode = "?a ?b ?c {?a ?b ?c}";// fazer consulta sparql para rastrear a propriedade do video

        StringBuilder requisicaoDBPedia = new StringBuilder();

        requisicaoDBPedia.append("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+");

        requisicaoDBPedia.append(URLEncoder.encode(textoEncode, "UTF-8"));

        requisicaoDBPedia.append("+LIMIT+100&timeout=30000&debug=on");

        System.out.println(requisicaoDBPedia.toString());

        String gsonArrayDBPedia = HttpRequest.get(requisicaoDBPedia.toString())
                .accept("application/json").body();

        System.out.println(gsonArrayDBPedia);

    }
}
