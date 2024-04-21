package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Indemnizaciones extends AppCompatActivity {
    private EditText salary_user;
    private ImageView start_date, finish_date;
    private TextView fecha_inicio, fecha_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indemnizaciones);

        Declaracion();

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Integer salary = sp.getInt("average_salary", 0);
        if(salary != 0)
        {
            salary_user.setText(salary.toString());

        }

        String first_date = sp.getString("first_date", "");
        if(first_date != "")
        {
            fecha_inicio.setText("Fecha de inicio: " + first_date);
        }

//        start_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dd = new DatePickerDialog(Indemnizaciones.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        fecha_inicio.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
//                    }
//                }, Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
//                        Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
//                        Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
//                );
//                dd.show();
//            }
//        });



        finish_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dd = new DatePickerDialog(Indemnizaciones.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha_fin.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                        Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                        Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                );
                dd.show();
            }
        });
    }

    public void Declaracion()
    {
        salary_user = findViewById(R.id.salary);
        start_date = findViewById(R.id.start_calendar);
        finish_date = findViewById(R.id.finish_calendar);

        fecha_inicio = findViewById(R.id.fecha_inicio);
        fecha_fin = findViewById(R.id.fecha_fin);
    }

    // Metodo para obtener fecha en formato dia/mes/año
    public static String obtenerFechaActual(String zona)
    {
        String formato = "dd-MM-yyyy";
        return obtenerFecha(formato, zona);
    }

    // metodo para obtener la fecha horaria al momento de la ejecucion;
    private static String obtenerFecha(String formato, String zonaHoraria)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }
}