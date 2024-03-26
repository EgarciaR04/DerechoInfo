package com.example.aplicaciondeindemnizaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.Activities.DatosIniciales;

public class MainActivity extends AppCompatActivity {
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Declaracion_variables();

    }

    public void Inicio(View v)
    {
        Intent intent = new Intent(this, DatosIniciales.class);
        startActivity(intent);
    }

    private void Declaracion_variables() {
        texto = findViewById(R.id.tv1);
    }
}