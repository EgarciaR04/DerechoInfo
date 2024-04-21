package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Home extends AppCompatActivity {
    private TextView average_salary, first_date;
    private String sp_first_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        average_salary = findViewById(R.id.average_salary);
        first_date = findViewById(R.id.start_date);

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Integer salary = sp.getInt("average_salary", 0);
        if (!salary.equals(0))
        {
            average_salary.setText("Salario promedio registrado: " + salary);
        }

        String first_date_declaretion = sp.getString("first_date", "");
        if(!first_date_declaretion.equals(""))
        {
            first_date.setText("Fecha inicial registrada: " + first_date_declaretion);
        }
    }

    private static String obtenerFecha(String formato, String zonaHoraria)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }

    public void enterIndemnizacion(View v)
    {
        Intent intent = new Intent(this, Indemnizaciones.class);
        startActivity(intent);
    }

    public void enterVacaciones(View v)
    {
        Intent intet = new Intent(this, VacacionesPendientes.class);
        startActivity(intet);
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

    public void Registrar_fecha_inicial(View v)
    {
        DatePickerDialog dd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sp_first_date = dayOfMonth + "/" + (month + 1) + "/" + year;
                SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("first_date", sp_first_date);
                editor.apply();

                first_date.setText("Fecha inicial registrada: " + sp_first_date);
            }
        }, Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                Integer.parseInt(obtenerFecha("MM", "America/Guatemala")),
                Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
        );
        dd.show();
    }

    public void Reiniciar_fecha_inicial(View v)
    {
        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("first_date", "");
        editor.apply();

        first_date.setText("Fecha inicial registrada: ");
    }
}