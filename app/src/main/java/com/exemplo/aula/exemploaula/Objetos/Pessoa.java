package com.exemplo.aula.exemploaula.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

public class Pessoa {

    String nome;
    String sobrenome;
    Integer idade;

    public Pessoa() {

    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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
}
