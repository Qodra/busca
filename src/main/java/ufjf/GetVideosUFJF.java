package ufjf;

import com.github.kevinsawicki.http.HttpRequest;
import org.joda.time.DateTime;
import scala.util.parsing.combinator.testing.Str;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetVideosUFJF {

    private static final String requisicaoUFJF(String sujeito, String DCTERMS) throws UnsupportedEncodingException{

        String textoEncode = " ?o where {<"+sujeito+"> "+DCTERMS+" ?o}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append("http://200.131.219.214:10035/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        return HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();
    }

    public static final String getTitle(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:title";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getCourse(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:course";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getAbstract(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:abstract";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getPublischer(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:publisher";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getCreator(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:creator";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getLicense(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:license";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getLanguage(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:language";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getEducationLevel(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:educationLevel";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final Date getDate(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:date";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        DateTime d = DateTime.parse(valores[1].replace("[", "").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]",""));

        Calendar c = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(d.getYear(), d.getMonthOfYear() - 1, d.getDayOfMonth());
        return calendar.getTime();
    }

    public static final ArrayList<String> getKeywords(String sujeito)throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:keyword";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        valores[1] = valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");
        valores = valores[1].split(",");

        ArrayList<String> keyWords = new ArrayList<String>();

        for (String s:valores){
            keyWords.add(s);
        }

        return keyWords;
    }


    public static final ArrayList<String> getRefences(String sujeito)throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:references>";

        String gsonArrayQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonArrayQodra.split("\"values\":");

        valores[1] = valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        valores = valores[1].split("],");

        String[] triplaRDF;

        ArrayList<String> references = new ArrayList<String>();

        for (String valor:valores) {
            valor = valor.replace("page","resource");
            valor = URLDecoder.decode(valor, "UTF-8");
            references.add(valor);
        }

        return references;
    }

}
