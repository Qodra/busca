package dbpedia;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DBPediaPT {

    public static final ArrayList<String> dbpediaGet(String query){
        StringBuilder textoEncode = new StringBuilder();

        textoEncode.append(query);

        StringBuilder requisicaoDBPedia = new StringBuilder();

        requisicaoDBPedia.append("http://pt.dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fpt.dbpedia.org&should-sponge=&query=select+");
        try {
            requisicaoDBPedia.append(URLEncoder.encode(textoEncode.toString(), "UTF-8"));
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        requisicaoDBPedia.append("+limit+10&debug=on&timeout=");


        System.out.println("query\n" + requisicaoDBPedia.toString()+"\n\n");

        String resultado = HttpRequest.get(requisicaoDBPedia.toString())
                .accept("text/csv").body();

        System.out.println("resultado\n" + resultado+"\n\n");
        resultado = resultado.substring(4).replaceAll("[\"\']", "");

        String[] resources = resultado.split("\n");

        ArrayList<String> related = new ArrayList<String>();
        for (String s : resources) {
            if (s.trim().isEmpty()) continue;
            try {
                s = URLDecoder.decode(s.trim(), "UTF-8");
            }
            catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            related.add(s);
        }
        return related;
    }


    public static final ArrayList<String> getLabelPT(String resource) {

        return dbpediaGet("?label where {<" + resource + "> rdfs:label ?label}");

    }



}
