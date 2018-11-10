package br.unicamp.ft.s187064_m184082.buckboard.model;

public class Usuario {

    private String nome;
    private String sobrenome;
    private String sexo;
    private String estadoCivil;
    private String email;

    public Usuario(String nome, String sobrenome, String sexo, String estadoCivil, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
