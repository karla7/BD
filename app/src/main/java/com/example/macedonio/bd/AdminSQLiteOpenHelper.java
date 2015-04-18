package com.example.macedonio.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    // Creamos el constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Se crea la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table accesorios (id_accesorio integer primary key unique, user text unique, nombre text, color text, tipo text) ");
    }

    // borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists accesorios");
        db.execSQL("create table accesorios (id_accesorio integer primary key unique, user text unique, nombre text, color text, tipo text) ");
    }
}

