package br.unicamp.ft.s187064_m184082.buckboard.model;

import java.util.ArrayList;
import java.util.List;

public class Conversa {

    private String primeiroUsuarioIdFirebase;

    private String segundoUsuarioIdFirebase;

    private List<Mensagem> mensagens;

    public Conversa() {
        //Required for Firebase
        this.mensagens = new ArrayList<>();
    }

    public Conversa(String primeiroUsuarioIdFirebase, String segundoUsuarioIdFirebase, List<Mensagem> mensagens) {
        this.primeiroUsuarioIdFirebase = primeiroUsuarioIdFirebase;
        this.segundoUsuarioIdFirebase = segundoUsuarioIdFirebase;
        this.mensagens = mensagens;
    }

    public String getPrimeiroUsuarioIdFirebase() {
        return primeiroUsuarioIdFirebase;
    }

    public void setPrimeiroUsuarioIdFirebase(String primeiroUsuarioIdFirebase) {
        this.primeiroUsuarioIdFirebase = primeiroUsuarioIdFirebase;
    }

    public String getSegundoUsuarioIdFirebase() {
        return segundoUsuarioIdFirebase;
    }

    public void setSegundoUsuarioIdFirebase(String segundoUsuarioIdFirebase) {
        this.segundoUsuarioIdFirebase = segundoUsuarioIdFirebase;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}
