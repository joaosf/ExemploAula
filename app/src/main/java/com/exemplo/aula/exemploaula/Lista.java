package com.exemplo.aula.exemploaula;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    Button btnAddLista;
    EditText campoTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        arrayList = new ArrayList<String>();
        listView = findViewById(R.id.list_item1);
        btnAddLista = findViewById(R.id.btnAddLista);
        campoTexto = findViewById(R.id.textoExemplo);
        btnAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarTexto();
            }
        });

    }

    private void adicionarTexto() {
        String textoTela = campoTexto.getText().toString();
        if (!textoTela.trim().equals("")) {
            arrayList.add(campoTexto.getText().toString());
            campoTexto.setText("");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        }
    }
}
