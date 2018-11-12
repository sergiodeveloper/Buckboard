package br.unicamp.ft.s187064_m184082.buckboard.model;

public class Mensagem {

    public static final String dataHoraFormat = "dd/MM/yyyy HH:mm:ss";

    private String remetenteIdFirebase;

    private String mensagem;

    private String dataHora;

    private String id;

    public Mensagem() {
        //required for firebase
    }

    public Mensagem(String remetenteIdFirebase, String mensagem, String dataHora) {
        this.remetenteIdFirebase = remetenteIdFirebase;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
