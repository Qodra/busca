package dbpedia;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DBPedia {

    public static final Boolean languageIsPt(String url){
        if (url == null) return false;

        return url.startsWith("http://pt");
    }

    public static final Boolean languageIsEs(String url){
        if (url == null) return false;

        return url.startsWith("http://es");
    }
    public static final Boolean languageIsEn(String url){
        if (url == null) return false;

        return (url.startsWith("http://dbpedia") || url.startsWith("http://en"));
    }

    public static final String getProperties(String resource, String property) throws UnsupportedEncodingException {

        StringBuilder textoEncode = new StringBuilder();

        textoEncode.append("distinct  ?x where {").append(resource).append(property).append("?x}");

        StringBuilder requisicaoDBPedia = new StringBuilder();

        requisicaoDBPedia.append("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+");

        requisicaoDBPedia.append(URLEncoder.encode(textoEncode.toString(), "UTF-8"));

        requisicaoDBPedia.append("+LIMIT+100&timeout=30000&debug=on");

        return HttpRequest.get(requisicaoDBPedia.toString())
                .accept("text/csv").body();
    }


    public static final ArrayList<String> getResource(String resource, int maxSubChamdas) throws UnsupportedEncodingException{
        final String[] PROPERTIES = {"dcterms:subject", "rdf:type", "dbpedia-owl:wikiPageWikiLink"};

        String str = null;

        ArrayList<String> assuntosCorrelatos = new ArrayList<String>();

        for (String property:PROPERTIES){
            str = getProperties(resource, property);

            str = str.substring(4).replaceAll("[\"\']","");

            String[] resources =  str.split("\n");

            for (String s:resources) {
                if (s.trim().isEmpty()) continue;
                s = URLDecoder.decode(s.trim(),"UTF-8");
                assuntosCorrelatos.add(s);

                System.out.println("url: "+s);

                if (maxSubChamdas > 0) {
                    ArrayList<String> assuntosCorrelatosRec = getResource("<"+s+">", --maxSubChamdas);

                    for (String s2 : assuntosCorrelatosRec) {
                        assuntosCorrelatos.add(s2);
                    }
                }
            }
        }

        return assuntosCorrelatos;
    }

    public static final ArrayList<String> getResourceSameAs(String resource) throws UnsupportedEncodingException {
        String str = null;

        ArrayList<String> assuntosCorrelatos = new ArrayList<String>();

        str = getProperties(resource, "owl:sameAs");

        str = str.substring(4).replaceAll("[\"\']","");

        String[] resources =  str.split("\n");

        for (String s:resources) {
            if (s.trim().isEmpty()) continue;
            s = URLDecoder.decode(s.trim(),"UTF-8");

            if (languageIsEn(s) || languageIsEs(s))
                assuntosCorrelatos.add(s);

            //System.out.println("url: "+s);
        }
        return assuntosCorrelatos;
    }
}
