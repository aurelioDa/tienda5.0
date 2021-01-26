package com.example.bdsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Consultar extends AppCompatActivity {

    EditText etNombre, etDireccion, etEmail, etPassword;
    TextInputLayout tilNombre, tilDireccion, tilEmail, tilPassword, tilID;
    Button bnResgstrar;
    SQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        conn = new SQLiteHelper(getApplicationContext(),"bd_usuario", null,1);
        etDireccion= (EditText) findViewById(R.id.etDomicilio);
        etNombre= (EditText) findViewById(R.id.etNombre);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etEmail= (EditText) findViewById(R.id.etCorreo);
        tilNombre= (TextInputLayout) findViewById(R.id.tilNombre);
        tilDireccion= (TextInputLayout) findViewById(R.id.tilDomicilio);
        tilPassword= (TextInputLayout) findViewById(R.id.tilPassword);
        tilEmail= (TextInputLayout) findViewById(R.id.tilCorreo);
        tilID= (TextInputLayout) findViewById(R.id.tilId);
    }

    public void consultar (View view){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {tilID.getEditText().getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_DOMICILIO, Utilidades.CAMPO_EMAIL,Utilidades.CAMPO_PASSWORD};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",
                    parametros,null,null,null);
            cursor.moveToFirst();

            etNombre.setText(cursor.getString(0));
            etDireccion.setText(cursor.getString(1));
            etEmail.setText(cursor.getString(2));
            etPassword.setText(cursor.getString(3));
            tilNombre.setHint(cursor.getString(0));
            tilDireccion.setHint(cursor.getString(1));
            tilEmail.setHint(cursor.getString(2));
            tilPassword.setHint(cursor.getString(3));
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No Exixte El ID", Toast.LENGTH_LONG).show();
            //limpiar();
        }
    }

    public void actualizar(View view){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {tilID.getEditText().getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE, tilNombre.getEditText().getText().toString());
        values.put(Utilidades.CAMPO_DOMICILIO, tilDireccion.getEditText().getText().toString());
        values.put(Utilidades.CAMPO_EMAIL, tilEmail.getEditText().getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, tilPassword.getEditText().getText().toString());
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_NOMBRE, values);

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Datos Actualizados: " + idResultante, Toast.LENGTH_LONG).show();
        db.close();

    }

    public void eliminar (View view){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {tilID.getEditText().getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Datos Eliminados", Toast.LENGTH_LONG).show();
        tilID.setHint("");
        db.close();
    }
}