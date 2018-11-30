package com.exemplo.aula.exemploaula;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.exemplo.aula.exemploaula.Objetos.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    List<Pessoa> pessoaList;
    Button btnAddLista;
    EditText campoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        arrayList = new ArrayList<>();
        pessoaList = new ArrayList<>();
        listView = findViewById(R.id.list_item1);
        btnAddLista = findViewById(R.id.btnAddLista);
        campoTexto = findViewById(R.id.textoExemplo);

        campoTexto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //AQUI
                if (i == KeyEvent.KEYCODE_ENTER) {
                    adicionarTexto(campoTexto.getText().toString());
                }
                return false;
            }
        });

        btnAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarTexto();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int linha, long coluna) {
                Intent tela = new Intent(getApplicationContext(), DetalheLista.class);
                tela.putExtra("texto",arrayList.get(linha));
                tela.putExtra("objeto", pessoaList.get(linha));
                startActivity(tela);
            }
        });
    }

    private void adicionarTexto() {
        String textoTela = campoTexto.getText().toString();
        adicionarTexto(textoTela);
    }

    private void adicionarTexto(String textoTela) {
        if (!textoTela.trim().equals("")) {
            arrayList.add(textoTela);
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(textoTela);
            pessoaList.add(pessoa);
            campoTexto.getText().clear();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        } else {
            campoTexto.setError("Preencha esse campo!");
        }
    }
}
