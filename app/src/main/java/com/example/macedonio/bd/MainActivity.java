package com.example.macedonio.bd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText et_idaccesorio, et_nombre, et_color, et_tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_idaccesorio = (EditText) findViewById(R.id.et_idaccesorio);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_color = (EditText) findViewById(R.id.et_color);
        et_tipo = (EditText) findViewById(R.id.et_tipo);


    }

    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "accesorios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idaccesorio = et_idaccesorio.getText().toString();
        String nombre = et_nombre.getText().toString();
        String color = et_color.getText().toString();
        String tipo = et_tipo.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id_accesorio", idaccesorio);
        registro.put("nombre", nombre);
        registro.put("color", color);
        registro.put("tipo", tipo);

        bd.insert("accesorios", null, registro);
        bd.close();

        et_idaccesorio.setText("");
        et_nombre.setText("");
        et_color.setText("");
        et_tipo.setText("");


        Toast.makeText(this,"Se agrego un nuevo accesorio",Toast.LENGTH_SHORT).show();

    }

    //los numeros son la posicion que se le asigna comenzando de cero
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "accesorios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idaccesorio = et_idaccesorio.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, color, tipo from accesorios where id_accesorio=" + idaccesorio, null);
        if (fila.moveToFirst()) {
            et_nombre.setText(fila.getString(0));
            et_color.setText(fila.getString(1));
            et_tipo.setText(fila.getString(2));

        } else {
            Toast.makeText(this,"No existe el accesorio",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }


    //obtener idaccesorio y se elimina, el 1 es verdadero si es otro valor,  no existe
    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idaccesorio = et_idaccesorio.getText().toString();

       // La tabla y la condicion ( lo que esta en verde)
        int cant = bd.delete("accesorios","id_accesorio=" + idaccesorio, null);
        bd.close();

        et_idaccesorio.setText("");
        et_nombre.setText("");
        et_color.setText("");
        et_tipo.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró el accesorio",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el accesorio",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "accesorios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //extraer del edittext los valores y guardarlos en variables
        String idaccesorio = et_idaccesorio.getText().toString();
        String nombre = et_nombre.getText().toString();
        String color = et_color.getText().toString();
        String tipo = et_tipo.getText().toString();

        //se genera un registro
        ContentValues registro = new ContentValues();

        //variables
        registro.put("id_accesorio", idaccesorio);
        registro.put("nombre", nombre);
        registro.put("color", color);
        registro.put("tipo", tipo);

        //Va la tabla el content values y la condicion
        int cant = bd.update("accesorios", registro, "id_accesorio=" + idaccesorio, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del accesorio",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el accesorio",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpia (View v){
        et_idaccesorio.setText("");
        et_nombre.setText("");
        et_color.setText("");
        et_tipo.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
