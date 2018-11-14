package br.unicamp.ft.s187064_m184082.buckboard.model;

import com.google.firebase.database.ServerValue;

public class Postagem {

    private String conteudo;

    private String foto;
    private String idUsuario;
    private String tipo;
    private String idReferenciada;
    private String id;
    private Object timestamp;

    public Postagem() {
    }

    public Postagem(String conteudo, String foto, String idUsuario, String tipo, String idReferenciada) {
        this.conteudo = conteudo;
        this.foto = foto;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.idReferenciada = idReferenciada;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdReferenciada() {
        return idReferenciada;
    }

    public void setIdReferenciada(String idReferenciada) {
        this.idReferenciada = idReferenciada;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
