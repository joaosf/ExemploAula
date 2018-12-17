package com.exemplo.aula.exemploaula.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

public class Pessoa {

    Integer codigo;
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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getInsert() {
        return "insert into pessoas(nome, sobrenome, idade) VALUES(\""+nome+"\",\""+sobrenome+"\","+idade+");";
    }

    public String getUpdate() {
        return "update pessoas set nome = \""+nome+"\", sobrenome = \""+sobrenome+"\", idade = \"+idade+\" where codigo = "+codigo+";";
    }
}
