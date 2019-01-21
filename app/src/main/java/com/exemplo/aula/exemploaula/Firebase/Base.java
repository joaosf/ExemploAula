package com.exemplo.aula.exemploaula.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Base {
    public static DatabaseReference getTableReference(String sTable, boolean bManterOffline) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(sTable);
        myRef.keepSynced(bManterOffline);
        return myRef;
    }

    public static Query getQuery(String tableName, String campo, String valor) {
        DatabaseReference myRef = getTableReference(tableName, true);
        if (!valor.equals("")) {
            return myRef.orderByChild(campo).equalTo(valor);
        } else {
            return myRef.orderByChild(campo);
        }
    }
}