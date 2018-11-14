package br.unicamp.ft.s187064_m184082.buckboard.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Conversa {

    private String id;

    private String primeiroUsuarioIdFirebase;

    private String segundoUsuarioIdFirebase;

    private Map<String, Mensagem> mensagens;

    private String idUltimaMensagem;

    private String nome;

    private Object timestamp;

    public Conversa() {
        //Required for Firebase
        this.mensagens = new HashMap<>();
    }

    public Conversa(String nome) {
        this.nome = nome;
        this.timestamp = ServerValue.TIMESTAMP;
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

    public void setIdUltimaMensagem(String idUltimaMensagem) {
        this.idUltimaMensagem = idUltimaMensagem;
    }

    public String getIdUltimaMensagem() {
        return idUltimaMensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
