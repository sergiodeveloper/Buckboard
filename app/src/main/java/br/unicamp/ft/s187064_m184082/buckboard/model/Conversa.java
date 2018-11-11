package br.unicamp.ft.s187064_m184082.buckboard.model;

import java.util.HashMap;
import java.util.Map;

public class Conversa {

    private String id;

    private String primeiroUsuarioIdFirebase;

    private String segundoUsuarioIdFirebase;

    private Map<String, Mensagem> mensagens;

    public Conversa() {
        //Required for Firebase
        this.mensagens = new HashMap<>();
    }

    public Conversa(String primeiroUsuarioIdFirebase, String segundoUsuarioIdFirebase, Map<String, Mensagem> mensagens) {
        this.primeiroUsuarioIdFirebase = primeiroUsuarioIdFirebase;
        this.segundoUsuarioIdFirebase = segundoUsuarioIdFirebase;
        this.mensagens = mensagens;
    }

    public Conversa(String id, String primeiroUsuarioIdFirebase, String segundoUsuarioIdFirebase, Map<String, Mensagem> mensagens) {
        this.id = id;
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

    public Map<String, Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(Map<String, Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
