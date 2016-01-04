package ufjf;

import java.util.ArrayList;

public class GetVideosRelacionados {

    public ArrayList<String> comparaListas(ArrayList<String> lista1, ArrayList<String> lista2){

        ArrayList<String> result = new ArrayList<String>();

        for (String str1:lista1){
            for (String str2:lista2){
                if (str1.equals(str2)){
                    result.add(str1);
                }
            }
        }

        return result;
    }

}
