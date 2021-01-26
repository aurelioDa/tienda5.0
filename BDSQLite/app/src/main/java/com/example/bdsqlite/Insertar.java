package com.example.bdsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class Insertar extends AppCompatActivity {
    EditText etNombre, etDireccion, etEmail, etPassword;
    TextInputLayout tilNombre, tilDireccion, tilEmail, tilPassword;
    Button bnResgstrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        etDireccion= (EditText) findViewById(R.id.etDomicilio);
        etNombre= (EditText) findViewById(R.id.etNombre);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etEmail= (EditText) findViewById(R.id.etCorreo);
        tilNombre= (TextInputLayout) findViewById(R.id.tilNombre);
        tilDireccion= (TextInputLayout) findViewById(R.id.tilDomicilio);
        tilPassword= (TextInputLayout) findViewById(R.id.tilPassword);
        tilEmail= (TextInputLayout) findViewById(R.id.tilCorreo);
        bnResgstrar= (Button) findViewById(R.id.btnRegistrar);

        bnResgstrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private boolean validarNombre(String nombre) {

        if (nombre.equals("") || nombre.length() >=20 ) {
            tilNombre.setError("Nombre Invalido");
            return false;
        } else {
            tilNombre.setError(null);
        }
        return true;
    }

    private boolean validarDireccion(String direccion) {
        Pattern patron = Pattern.compile("[0-9a-zA-Z]");
        if (direccion.equals("") || direccion.length() >=30 ) {
            tilDireccion.setError("Direccion Invalida");
            return false;
        } else {
            tilDireccion.setError(null);

        }
        return true;
    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilEmail.setError("Correo Inválido");
            return false;
        } else {
            tilEmail.setError(null);
        }

        return true;
    }

    private void registrar() {
      final String nombre = tilNombre.getEditText().getText().toString();
      final String direccion = tilDireccion.getEditText().getText().toString();
      final String correo = tilEmail.getEditText().getText().toString();

        boolean a = validarNombre(nombre);
        boolean b = validarDireccion(direccion);
        boolean c = esCorreoValido(correo);

        if (a && b && c) {

            SQLiteHelper conn = new SQLiteHelper(this,"bd_usuario", null, 1);
                    SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE, tilNombre.getEditText().getText().toString());
            values.put(Utilidades.CAMPO_DOMICILIO, tilDireccion.getEditText().getText().toString());
            values.put(Utilidades.CAMPO_EMAIL, tilEmail.getEditText().getText().toString());
            values.put(Utilidades.CAMPO_PASSWORD, tilPassword.getEditText().getText().toString());
            Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_NOMBRE, values);

            Toast.makeText(getApplicationContext(), "Numero De Registro: " + idResultante, Toast.LENGTH_LONG).show();
         db.close();
        }  else{
                Toast.makeText(getApplicationContext(), "Datos Invalidos" , Toast.LENGTH_LONG).show();
        }
    }
}




