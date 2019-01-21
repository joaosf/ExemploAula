package com.exemplo.aula.exemploaula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

        listView = findViewById(R.id.list_item1);
        btnAddLista = findViewById(R.id.btnAddLista);
        campoNome = findViewById(R.id.txtNome);
        campoSobrenome = findViewById(R.id.txtSobrenome);
        campoIdade = findViewById(R.id.txtIdade);

        Gerenciador.getInstance().setContext(this);
        Gerenciador.getInstance().setListView(listView);

        btnAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPessoa();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int linha, long coluna) {
                Intent tela = new Intent(getApplicationContext(), DetalheLista.class);
                String data = adapterView.getItemAtPosition(linha).toString();
                tela.putExtra("nomePessoa", data);
                startActivity(tela);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String nomePessoa = ((TextView) view).getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(Gerenciador.getInstance().getContext())
                        .setTitle("Tem certeza que deseja excluir?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Gerenciador.getInstance().deletePessoa(nomePessoa);
                            }
                        })
                        .setNegativeButton("Não",null);
                alert.show();
                return false;
            }
        });
    }

    public void adicionarPessoa() {
        String nome = campoNome.getText().toString();
        String sobrenome = campoSobrenome.getText().toString();
        Integer idade;
        //Integer.parseInt = Converter uma String para inteiro
        try {
            idade = Integer.parseInt(campoIdade.getText().toString());
        } catch (Exception ex) {
            campoIdade.setError("Campo inválido!");
            return;
        }


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

            Gerenciador.getInstance().atualizarLista();

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
        Gerenciador.getInstance().atualizarLista();
    }
}
