package dbpedia;

import com.github.kevinsawicki.http.HttpRequest;
import scala.util.parsing.combinator.testing.Str;
import ufjf.Video;

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
        final String[] PROPERTIES = {"rdf:type"};

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

    public static final ArrayList<String> getResourceSameAs(String resource) {

        return dbpediaGet("distinct ?x where {<" + resource + ">  owl:sameAs ?x}");

    }

    public static final ArrayList<String> dbpediaGet(String query){
        StringBuilder textoEncode = new StringBuilder();

        textoEncode.append(query);

        StringBuilder requisicaoDBPedia = new StringBuilder();

        requisicaoDBPedia.append("http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+");
        try {
            requisicaoDBPedia.append(URLEncoder.encode(textoEncode.toString(), "UTF-8"));
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        requisicaoDBPedia.append("+LIMIT+100&timeout=30000&debug=on");

        String resultado = HttpRequest.get(requisicaoDBPedia.toString())
                .accept("text/csv").body();

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

    public static final ArrayList<String> getResourcesByCategory(String category) {

        return dbpediaGet("distinct ?x where {?x  dcterms:subject <" + category + ">}");

    }

    public static final ArrayList<String> getCategoryByResource(String resource){

        return dbpediaGet("distinct ?x where {<" + resource + ">  dcterms:subject ?x}");

    }

    public static final ArrayList<String> getClassByResource(String resource){

         return dbpediaGet("distinct ?x where {<" + resource + ">  rdf:type ?x}");

    }

    public static final ArrayList<String> getResourcesByClass(String _class) {

        return dbpediaGet("distinct ?x where {?x rdf:type <" + _class + ">}");

    }

    public static final ArrayList<String> getResourcesRelated(Video video) {

        ArrayList<String> related = new ArrayList<String>();

        /**
         * laço todas as referencias do video
         */
        for (String reference : video.getReferences()) {

            ArrayList<String> referencesSameAs;
            ArrayList<String> categories;
            ArrayList<String> resources;

            if (DBPedia.languageIsPt(reference)) {
                //se esta em portugues prcura por sameAs
                referencesSameAs = DBPedia.getResourceSameAs(reference);
            }
            else{
                referencesSameAs = new ArrayList<String>();
                referencesSameAs.add(reference);
            }

            for (String referenceSameAs : referencesSameAs) {

                //para cada sameAs encontrado procura a categoria

                categories = DBPedia.getCategoryByResource(referenceSameAs);

                for (String category : categories) {

                    //para cada categoria encontrada busca os recursos desta categoria

                    resources = DBPedia.getResourcesByCategory(category);

                    for (String resource: resources){
                        related.add(resource);
                        System.out.println(resource);
                    }
                }
            }
        }
        return related;
    }
}
