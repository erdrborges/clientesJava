package com.example.clientes.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String email;
    private String idade;
    private String url_foto;
    private String key; //chave de manipulação

    public Cliente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getIdade() {
        return idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return "Cliente{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", url_foto='" + url_foto + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
