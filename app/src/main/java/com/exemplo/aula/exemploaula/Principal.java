package com.exemplo.aula.exemploaula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    Button btnLista;
    Button btn2;
    TextView textoTelaInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Gerenciador.getInstance().setContext(this);

        btnLista = findViewById(R.id.btnLista);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaLista();
            }
        });

        btn2 = findViewById(R.id.btnListaComClique);

        String aula;
        aula = "TEXTO DE VARIAVEL:";
        textoTelaInicial = findViewById(R.id.textoTelaInicial);
        textoTelaInicial.setText(aula);

    }

    private void abrirTelaLista() {
        Intent tela = new Intent(this, Lista.class);
        startActivity(tela);
    }
}
