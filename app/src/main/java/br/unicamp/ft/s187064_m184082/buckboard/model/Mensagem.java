package br.unicamp.ft.s187064_m184082.buckboard.model;

import com.google.firebase.database.ServerValue;

public class Mensagem {

    private String remetenteIdFirebase;

    private String mensagem;

    private Object timestamp;

    private String id;

    public Mensagem() {
        //required for firebase
    }

    public Mensagem(String remetenteIdFirebase, String mensagem) {
        this.remetenteIdFirebase = remetenteIdFirebase;
        this.mensagem = mensagem;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public String getRemetenteIdFirebase() {
        return remetenteIdFirebase;
    }

    public void setRemetenteIdFirebase(String remetenteIdFirebase) {
        this.remetenteIdFirebase = remetenteIdFirebase;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
