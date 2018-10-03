package br.unicamp.ft.s187064_m184082.buckboard;

public class Postagem {

    private String conteudo;

    private int foto;

    public Postagem(String conteudo, int foto) {
        this.conteudo = conteudo;
        this.foto = foto;
    }


    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
