package br.unicamp.ft.s187064_m184082.buckboard.controller;

public class Sessao {

    private static String usuarioLogado;

    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(String usuario) {
        usuarioLogado = usuario;
    }

}
