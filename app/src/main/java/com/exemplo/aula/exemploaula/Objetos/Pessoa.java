package com.exemplo.aula.exemploaula.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

public class Pessoa implements Parcelable {

    String nome;

    public Pessoa() {

    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    protected Pessoa(Parcel in) {
        nome = in.readString();
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
    }
}
