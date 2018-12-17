package com.exemplo.aula.exemploaula;

import android.content.Context;
import android.database.Cursor;

import com.exemplo.aula.exemploaula.Banco.BDAdapter;
import com.exemplo.aula.exemploaula.Objetos.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    private static final Gerenciador ourInstance = new Gerenciador();

    private Context context;

    public static Gerenciador getInstance() {
        return ourInstance;
    }

    private Gerenciador() {
    }

    public void setContext(Context pCtx) {
        context = pCtx;
    }

    public Pessoa getPessoa(int CodPessoa) {

        Cursor cursor = BDAdapter.executaConsultaSQL(context,"select * from pessoas where codigo = "+CodPessoa);
        Pessoa pessoa = new Pessoa();
        if (cursor.moveToFirst()) {
            pessoa.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoa.setSobrenome(cursor.getString(cursor.getColumnIndex("sobrenome")));
            pessoa.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
        }
        return pessoa;
    }

    public void addPessoa(Pessoa pessoa) {
        BDAdapter.executarComandoSQL(context,pessoa.getInsert());
    }

    public void updPessoa(Pessoa pessoa) {
        BDAdapter.executarComandoSQL(context,pessoa.getUpdate());
    }

    public List<Pessoa> getPessoaList() {
        Cursor cursor = BDAdapter.executaConsultaSQL(context,"select * from pessoas order by codigo");
        List<Pessoa> pessoas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pessoa.setSobrenome(cursor.getString(cursor.getColumnIndex("sobrenome")));
                pessoa.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
                pessoas.add(pessoa);
            } while (cursor.moveToNext());
        }
        return pessoas;
    }

    public ArrayList<String> getNomeList() {
        ArrayList<String> retorno = new ArrayList<>();
        List<Pessoa> pessoas = getPessoaList();
        for (int i = 0; i < pessoas.size(); i++) {
            retorno.add(pessoas.get(i).getNome());
        }
        return retorno;
    }
}
