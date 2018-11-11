package br.unicamp.ft.s187064_m184082.buckboard.model;

public class PreviewMensagem {

    private String conversaId;

    private String nome, mensagem;

    private int foto;

    public PreviewMensagem(String conversaId, String nome, String mensagem, int foto) {
        this.conversaId = conversaId;
        this.nome = nome;
        this.mensagem = mensagem;
        this.foto = foto;
    }

    public String getConversaId() {
        return conversaId;
    }

    public void setConversaId(String conversaId) {
        this.conversaId = conversaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
