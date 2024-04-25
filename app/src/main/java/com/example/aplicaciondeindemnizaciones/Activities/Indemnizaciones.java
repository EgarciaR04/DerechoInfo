package com.example.aplicaciondeindemnizaciones.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciondeindemnizaciones.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Indemnizaciones extends AppCompatActivity {
    private EditText salary_user;
    private ImageView start_date, finish_date;
    private TextView fecha_inicio, fecha_fin;
    private String fecha_contratacion_str, fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indemnizaciones);

        Declaracion();

        AndroidThreeTen.init(this);


        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Float salary = sp.getFloat("average_salary", 0);
        if(salary != 0)
        {
            salary_user.setText(salary.toString());
        }

        String first_date = sp.getString("first_date", "");
        if(first_date != "")
        {
            fecha_inicio.setText("Fecha de inicio: " + first_date);
            fecha_contratacion_str = first_date;
        }

        finish_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dd = new DatePickerDialog(Indemnizaciones.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        if (dayOfMonth < 10 && month < 9)
                        {
                            fin = year + "-"+ "0" + (month + 1) + "-" +"0" + dayOfMonth;
                        }
                        else if (dayOfMonth < 10 && month >= 9)
                        {
                            fin = year + "-" + (month + 1) + "-" +"0"+ dayOfMonth;
                        }
                        else if (dayOfMonth >= 10 && month >= 9)
                        {
                            fin = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }
                        else if (dayOfMonth >= 10 && month < 9)
                        {
                            fin = year + "-" + "0"+(month + 1) + "-" + dayOfMonth;
                        }

                        fecha_fin.append(fin);

                        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString("last_date", fin);
                        edit.apply();
                    }
                }, Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                        Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                        Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                );
                dd.show();
            }
        });
    }

    public void Calculo(View v)
    {
        float meses_trabajados = (calcularDiasTranscurridos(fecha_contratacion_str, fin) / 30);
        float salario_promedio = Float.parseFloat(salary_user.getText().toString());

        float indemnizacion = (salario_promedio * meses_trabajados) / 12;

        SharedPreferences sp = getSharedPreferences("PagosPendientes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("indemnizacion", indemnizacion);
        editor.apply();

        Toast.makeText(this, String.valueOf(indemnizacion), Toast.LENGTH_SHORT).show();
    }

    public void Declaracion()
    {
        salary_user = findViewById(R.id.salary);
        start_date = findViewById(R.id.start_calendar);
        finish_date = findViewById(R.id.finish_calendar);

        fecha_inicio = findViewById(R.id.fecha_inicio);
        fecha_fin = findViewById(R.id.fecha_fin);
    }

    @SuppressLint("SimpleDateFormat")
    private static float calcularDiasTranscurridos(String fechaInicio, String fechaFin) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Convertir las cadenas de fecha a objetos Date
            Date fechaInicial = formato.parse(fechaInicio);
            Date fechaFinal = formato.parse(fechaFin);

            // Calcular los milisegundos transcurridos entre las fechas
            long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

            // Convertir los milisegundos a días
            return diferencia / (1000f * 60f * 60f * 24f);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Retorna -1 en caso de error
        }
        
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


//
//    private String Obtener_fecha_actual()
//    {
//        Date fecha_actual = new Date();
//
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        String fecha_formateada = formato.format(fecha_actual);
//
//        return fecha_formateada;
//    }
}