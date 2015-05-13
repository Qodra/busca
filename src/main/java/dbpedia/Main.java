package dbpedia;


import com.github.kevinsawicki.http.HttpRequest;
import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) throws UnsupportedEncodingException {

        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        String id = "0";
        while (true) {
            try {
                System.out.println("Entre com id do video para fazer uma nova consulta ou digite \"0\" para sair: ");
                id = ler.readLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            if ("0".equals(id)) {
                break;
            }
            else {
                Video video = new Video(id);
            }

        }

    }




}
