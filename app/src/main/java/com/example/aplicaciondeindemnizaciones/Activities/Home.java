package com.example.aplicaciondeindemnizaciones.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.R;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {
    private TextView average_salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        average_salary = findViewById(R.id.average_salary);

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Integer salary = sp.getInt("average_salary", 0);
        if (!salary.equals(0))
        {
            average_salary.setText("Salario promedio registrado: " + salary);
        }
    }

    public void enterIndemnizacion(View v)
    {
        Intent intent = new Intent(this, Indemnizaciones.class);
        startActivity(intent);
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
//      Abrir interfaz de Bono 14
    }

    public void enterSalariosPendientes(View v)
    {
        // Abrir interfaz de Salarios pendientes
    }

    public void enterHorasExtraPendientes(View v)
    {
        // Abrir interfaz de Horas Extras Pendientes
    }

    public void salarioPromedio(View v)
    {
        Intent intent = new Intent(this, SalarioPromedioActivity.class);
        startActivity(intent);
    }

    public void reiniciarSalario(View v)
    {
        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("average_salary",0);
        editor.apply();

        average_salary.setText("Salario promedio registrado: " + 0);

    }
}