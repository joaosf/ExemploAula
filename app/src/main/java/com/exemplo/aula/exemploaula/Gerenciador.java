package com.exemplo.aula.exemploaula;

import com.exemplo.aula.exemploaula.Objetos.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    private static final Gerenciador ourInstance = new Gerenciador();
    private List<Pessoa> pessoaList;
    private ArrayList<String> arrayList;

    public static Gerenciador getInstance() {
        return ourInstance;
    }

    private Gerenciador() {
        pessoaList = new ArrayList<>();
        arrayList = new ArrayList<>();
    }

    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
}
