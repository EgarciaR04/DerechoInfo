package com.example.aplicaciondeindemnizaciones.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciondeindemnizaciones.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HorasExtraordinarias extends AppCompatActivity {
    private SharedPreferences sp;
    private TextView horas;
    private Float average_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_extraordinarias);

        sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        average_salary = sp.getFloat("average_salary", 0f);

        Declaracion();
    }

    public void Calculo_Horas_Extra(View v)
    {
        if(average_salary != 0)
        {
            Float horas_debidas = average_salary/30;
            horas_debidas = horas_debidas / 24;
            horas_debidas = horas_debidas * 1.5f;
            horas_debidas = horas_debidas * Float.parseFloat(horas.getText().toString());
            Toast.makeText(this, "Valor de las horas extras: " + horas_debidas, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Ingrese su salario promedio", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SalarioPromedioActivity.class);
            startActivity(intent);
        }
    }

    private void Declaracion() {
        horas = findViewById(R.id.horas_extraordinarias);
    }
}