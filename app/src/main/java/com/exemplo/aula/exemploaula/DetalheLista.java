package com.exemplo.aula.exemploaula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemplo.aula.exemploaula.Objetos.Pessoa;

public class DetalheLista extends AppCompatActivity {
    Integer index;

    EditText campoNome;
    EditText campoSobrenome;
    EditText campoIdade;
    Pessoa pessoa;

    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_lista);

        //findViewById = buscar componente em tela
        campoNome = findViewById(R.id.txtNomeEdit);
        campoSobrenome = findViewById(R.id.txtSobrenomeEdit);
        campoIdade = findViewById(R.id.txtIdadeEdit);

        //Carregando botão de salvar
        btnSalvar = findViewById(R.id.btnSalvar);

        Intent intent = getIntent();

        index = intent.getIntExtra("index",0);
        //Buscando informações no Singleton
        pessoa = Gerenciador.getInstance().getPessoaList().get(index);

        campoNome.setText(pessoa.getNome());
        campoSobrenome.setText(pessoa.getSobrenome());
        campoIdade.setText(pessoa.getIdade().toString());

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
    }

    private void salvarDados() {
        pessoa.setNome(campoNome.getText().toString());
        pessoa.setSobrenome(campoSobrenome.getText().toString());
        //Integer.parseInt = Converter uma String para inteiro
        pessoa.setIdade(Integer.parseInt(campoIdade.getText().toString()));

        Gerenciador.getInstance().getPessoaList().set(index,pessoa);
        Gerenciador.getInstance().getArrayList().set(index,pessoa.getNome());
        finish();
    }
}
