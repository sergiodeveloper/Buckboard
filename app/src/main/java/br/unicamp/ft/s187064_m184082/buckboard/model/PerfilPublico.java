package br.unicamp.ft.s187064_m184082.buckboard.model;

public class PerfilPublico {

    private String nome, sobrenome, foto, sexo;

    public PerfilPublico(String nome, String sobrenome, String foto, String sexo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.foto = foto;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
