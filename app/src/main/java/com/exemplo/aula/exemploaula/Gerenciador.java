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

    public Context getContext() {
        return context;
    }

    public void deletePessoa(String nomePessoa) {
        Query consulta = Base.getQuery("pessoas","nome", nomePessoa);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        DatabaseReference dados = Base.getTableReference("pessoas/"+postSnapshot.getKey(),false);
                        dados.setValue(null);
                    }
                    atualizarLista();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addPessoa(final Pessoa pessoa) {
        Query consulta = Base.getQuery("pessoas","nome", pessoa.getNome());
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        DatabaseReference dados = Base.getTableReference("pessoas/"+postSnapshot.getKey(),false);
                        dados.setValue(pessoa);
                    }
                } else {
                    DatabaseReference dados = Base.getTableReference("pessoas",false);
                    dados.push().setValue(pessoa);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        Query consulta = Base.getQuery("pessoas","nome", "");
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1, listNomePessoas);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
