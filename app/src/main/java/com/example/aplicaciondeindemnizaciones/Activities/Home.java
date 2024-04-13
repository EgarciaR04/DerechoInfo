package com.example.aplicaciondeindemnizaciones.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.aplicaciondeindemnizaciones.R;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void enterIndemnizacion(View v)
    {
        // Abrir interfaz de Indemnizacion
    }

    public void enterVacaciones(View v)
    {
        // Abrir interaz de Vacaciones pendientes
    }

    public void enterAguinaldo(View v)
    {
        // Abrir interfaz de Aguinaldos pendientes
    }

    public void enterBono14(View v)
    {
//        Abrir interfaz de Bono 14
    }

    public void enterSalariosPendientes(View v)
    {
        // Abrir interfaz de Salarios pendientes
    }

    public void enterHorasExtraPendientes(View v)
    {
        // Abrir interfaz de Horas Extras Pendientes
    }
}