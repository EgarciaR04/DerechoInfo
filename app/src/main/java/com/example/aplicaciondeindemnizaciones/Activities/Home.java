package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.MainActivity;
import com.example.aplicaciondeindemnizaciones.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Home extends AppCompatActivity {
    private TextView average_salary, first_date;
    private String sp_first_date;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sp = getSharedPreferences("SalariosPendientes", Context.MODE_PRIVATE);
        editor = sp.edit();
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
        Intent intent = new Intent(this, AguinaldoActivity.class);
        startActivity(intent);
    }

    public void enterBono14(View v)
    {
        Intent intent = new Intent(this, Bono14.class);
        startActivity(intent);
    }

    public void enterSalariosPendientes(View v)
    {
        Intent intent =new Intent(this, SalariosPendientes.class);
        startActivity(intent);
    }

    public void enterHorasExtraPendientes(View v)
    {
        Intent intent = new Intent(this, HorasExtraordinarias.class);
        startActivity(intent);
    }

    public void Resumen(View v)
    {

    }

    public void Borrar_datos(View v)
    {
        editor.putBoolean("Borrar",  false);
        editor.apply();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}