package com.example.bdsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteHelper conn = new SQLiteHelper(
                this,"bd_usuario",null,1
        );
}

public void Ainsertar (View view){
    Toast.makeText(this,"Abriendo Pantalla", Toast.LENGTH_LONG).show();
    Intent insertar = new Intent (this, Insertar.class);
    startActivity(insertar);
    }

    public void Aconsultar (View view){
        Toast.makeText(this,"Abriendo Pantalla", Toast.LENGTH_LONG).show();
        Intent consultar = new Intent (this, Consultar.class);
        startActivity(consultar);
    }

    public void Alista (View view){
        Toast.makeText(this,"Abriendo Pantalla", Toast.LENGTH_LONG).show();
        Intent consultar = new Intent (this, VerDatos.class);
        startActivity(consultar);
    }
}