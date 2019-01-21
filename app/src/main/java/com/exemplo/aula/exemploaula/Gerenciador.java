package com.exemplo.aula.exemploaula;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.exemplo.aula.exemploaula.Firebase.Base;
import com.exemplo.aula.exemploaula.Objetos.Pessoa;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    private static final Gerenciador ourInstance = new Gerenciador();

    private Context context;
    private ListView listView;

    public static Gerenciador getInstance() {
        return ourInstance;
    }

    private Gerenciador() {
    }

    public void setListView(ListView listView2) {
        this.listView = listView2;
    }

    public void setContext(Context pCtx) {
        context = pCtx;
    }

    public void addPessoa(Pessoa pessoa) {
        DatabaseReference dados = Base.getTableReference("pessoas",false);
        dados.push().setValue(pessoa);
    }

    public void updPessoa(String nomePessoa, final Pessoa pessoa) {
        Query consulta = Base.getQuery("pessoas","nome", nomePessoa);
        consulta.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DatabaseReference dados = Base.getTableReference("pessoas/"+dataSnapshot.getKey(),false);
                dados.setValue(pessoa);
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
    }

    public void atualizarLista() {
        Query consulta = Base.getQuery("pessoas","nomePessoa", "");
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> listNomePessoas = new ArrayList<>();
                if (dataSnapshot.exists()) {

                    List<Pessoa> listPessoas = new ArrayList<>();

                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        listPessoas.add(postSnapshot.getValue(Pessoa.class));

                    }

                    for (int i = 0; i < listPessoas.size(); i++) {
                        listNomePessoas.add(listPessoas.get(i).getNome());
                    }
                }

                //Criação do list adapter em tela
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, listNomePessoas);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
