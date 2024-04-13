package com.example.aplicaciondeindemnizaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aplicaciondeindemnizaciones.Activities.Home;

public class MainActivity extends AppCompatActivity {
    private EditText edt_user, edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Declaracion_variables();
    }

    // Funcion utilizada para la declaracion de variables
    public void Declaracion_variables()
    {
        edt_user = findViewById(R.id.enter_user);
        edt_password = findViewById(R.id.enter_password);
    }

    public void enterSystem(View v)
    {
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }
}