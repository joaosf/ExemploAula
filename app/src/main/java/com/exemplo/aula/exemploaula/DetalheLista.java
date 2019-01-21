package com.exemplo.aula.exemploaula;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemplo.aula.exemploaula.Firebase.Base;
import com.exemplo.aula.exemploaula.Objetos.Pessoa;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

public class DetalheLista extends AppCompatActivity {
    EditText campoNome;
    EditText campoSobrenome;
    EditText campoIdade;
    Pessoa pessoa;

    String nomePessoa;

    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_lista);
        Gerenciador.getInstance().setContext(this);

        //findViewById = buscar componente em tela
        campoNome = findViewById(R.id.txtNomeEdit);
        campoSobrenome = findViewById(R.id.txtSobrenomeEdit);
        campoIdade = findViewById(R.id.txtIdadeEdit);

        //Carregando botão de salvar
        btnSalvar = findViewById(R.id.btnSalvar);

        Intent intent = getIntent();

        nomePessoa = intent.getStringExtra("nomePessoa");
        //Buscando informações no Firebase
        Query consulta = Base.getQuery("pessoas","nome", nomePessoa);
        consulta.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                pessoa = dataSnapshot.getValue(Pessoa.class);

                campoNome.setText(pessoa.getNome());
                campoSobrenome.setText(pessoa.getSobrenome());
                campoIdade.setText(pessoa.getIdade().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        Gerenciador.getInstance().updPessoa(nomePessoa, pessoa);
        finish();
    }
}
