package com.example.yuri.app.model;

/**
 * Created by yuri on 29/08/17.
 */

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private int ddi;
    private int ddd;
    private int numero_celular;

    public Usuario(String nome, String email, int ddi, int ddd, int numero_celular) {
        this.nome = nome;
        this.email = email;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero_celular = numero_celular;
    }

    public Usuario(int id, String nome, String email, int ddi, int ddd, int numero_celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero_celular = numero_celular;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDdi() {
        return ddi;
    }

    public void setDdi(int ddi) {
        this.ddi = ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(int numero_celular) {
        this.numero_celular = numero_celular;
    }


}
