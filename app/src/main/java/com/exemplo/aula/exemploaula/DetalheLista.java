package com.exemplo.aula.exemploaula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.exemplo.aula.exemploaula.Objetos.Pessoa;

public class DetalheLista extends AppCompatActivity {

    TextView textoDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_lista);

        textoDetalhe = findViewById(R.id.textoDetalhe);

        Intent intent = getIntent();

        String texto = intent.getStringExtra("texto");
        textoDetalhe.setText(texto);
        Pessoa pessoa = intent.getParcelableExtra("objeto");

    }
}
