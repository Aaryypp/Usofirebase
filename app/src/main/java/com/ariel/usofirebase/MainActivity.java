package com.ariel.usofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView texto;
    Button regre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Obtener la referencia de los elementos de la interfaz de usuario
        texto = findViewById(R.id.exampleText);
        regre = findViewById(R.id.regresar);

        Intent intent = getIntent();
        String correo = intent.getStringExtra("correo");

        // Establecer el texto en el TextView
        texto.setText(correo);

        regre.setOnClickListener(l -> retroceder());
    }

    public void retroceder() {
        super.onBackPressed();
    }
}

