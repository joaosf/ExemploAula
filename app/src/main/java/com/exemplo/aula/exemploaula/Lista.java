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
    Button btnAddLista;
    EditText campoNome;
    EditText campoSobrenome;
    EditText campoIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Gerenciador.getInstance().setContext(this);

        listView = findViewById(R.id.list_item1);
        btnAddLista = findViewById(R.id.btnAddLista);
        campoNome = findViewById(R.id.txtNome);
        campoSobrenome = findViewById(R.id.txtSobrenome);
        campoIdade = findViewById(R.id.txtIdade);

        btnAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicinarPessoa();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int linha, long coluna) {
                Intent tela = new Intent(getApplicationContext(), DetalheLista.class);
                tela.putExtra("index", linha);
                startActivity(tela);
            }
        });
    }

    public void adicinarPessoa() {
        String nome = campoNome.getText().toString();
        String sobrenome = campoSobrenome.getText().toString();
        //Integer.parseInt = Converter uma String para inteiro
        Integer idade = Integer.parseInt(campoIdade.getText().toString());

        if ((!nome.trim().equals("")) && (!sobrenome.trim().equals(""))) {
            // Definindo valores do meu Objeto Pessoa
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setSobrenome(sobrenome);
            pessoa.setIdade(idade);
            //Salvando informações no Singleton
            Gerenciador.getInstance().addPessoa(pessoa);

            // Limpando Campos em tela
            campoNome.getText().clear();
            campoSobrenome.getText().clear();
            campoIdade.getText().clear();

            //Criação do list adapter em tela
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, Gerenciador.getInstance().getNomeList());
            listView.setAdapter(adapter);

        } else if (nome.trim().equals("")) {
            // Mostrando erro no campo em tela
            campoNome.setError("Preencha esse campo!");
        } else if (sobrenome.trim().equals("")) {
            // Mostrando erro no campo em tela
            campoSobrenome.setError("Preencha esse campo!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Criação do list adapter em tela
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Gerenciador.getInstance().getNomeList());
        listView.setAdapter(adapter);
    }
}
