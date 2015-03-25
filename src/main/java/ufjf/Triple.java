package ufjf;

import java.io.Serializable;

public class Triple implements Serializable{

    String sujeito;

    String predicado;

    String Objeto;

    public String getSujeito() {
        return sujeito;
    }

    public void setSujeito(String sujeito) {
        this.sujeito = sujeito;
    }

    public String getPredicado() {
        return predicado;
    }

    public void setPredicado(String predicado) {
        this.predicado = predicado;
    }

    public String getObjeto() {
        return Objeto;
    }

    public void setObjeto(String objeto) {
        Objeto = objeto;
    }
}
