package ufjf;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GetVideosUFJF {

    public static final ArrayList<Triple> getAll()throws UnsupportedEncodingException{
        String textoEncode = "?a ?b ?c {?a ?b ?c}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append("http://200.131.219.214:10035/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        System.out.println(requisicaoQodra.toString());

        //String gsonArrayQodra = HttpRequest.get("http://200.131.219.214:10035/repositories/qodra?query=select%20%3Fs%20%3Fp%20%3Fo%20%7B%3Fs%20%3Fp%20%3Fo%7D&queryLn=SPARQL&infer=false&uuid=p3d1s1xuh7y95mazl3orh&returnQueryMetadata=true")
        //        .accept("application/json").body();

        String gsonArrayQodra = HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();
                //.accept("text/simple-csv").body();

        System.out.println(gsonArrayQodra);

        ArrayList<Triple> rdfsQodra;

        //return gsonArrayQodra;

        String valores[] = gsonArrayQodra.split("\"values\":");


        valores[1] = valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","");

        valores = valores[1].split("],");

        String[] triplaRDF;

        ArrayList<Triple> triplas = new ArrayList<Triple>();
        for (String valor:valores) {
            System.out.println(valor);
            triplaRDF = valor.split(",");

            Triple t = new Triple();

            int i = 0;

            t.setSujeito(triplaRDF[0]);
            t.setPredicado(triplaRDF[1]);
            t.setObjeto(triplaRDF[2]);

            triplas.add(t);
        }

        return triplas;
        // rdfsQodra = JSONBuilder.getGson().getJSONArray.fromJson(requisicaoQodra, Tripla.class);

    }
}
