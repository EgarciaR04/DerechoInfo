package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciondeindemnizaciones.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AguinaldoActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText average_salary;
    private TextView first_date, last_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aguinaldo);

        sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        editor = sp.edit();
        
        Declaracion();

        String date_1 = sp.getString("first_date", "");
        if(!date_1.equals(""))
        {
            first_date.setText("Fecha de inicio: " + date_1);
        }
        else
        {
            first_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dd = new DatePickerDialog(AguinaldoActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = "";
                            if (dayOfMonth < 10 && month < 9)
                            {
                                date = year + "-"+ "0" + (month + 1) + "-" +"0" + dayOfMonth;
                            }
                            else if (dayOfMonth < 10 && month >= 9)
                            {
                                date = year + "-" + (month + 1) + "-" +"0"+ dayOfMonth;
                            }
                            else if (dayOfMonth >= 10 && month >= 9)
                            {
                                date = year + "-" + (month + 1) + "-" + dayOfMonth;
                            }
                            else if (dayOfMonth >= 10 && month < 9)
                            {
                                date = year + "-" + "0"+(month + 1) + "-" + dayOfMonth;
                            }
                            first_date.setText("Fecha de inicio: " + date);

                            editor.putString("first_date", date);
                            editor.apply();
                        }
                    },Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                            Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                            Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                    );
                    dd.show();
                }
            });
        }

        String date_2 = sp.getString("last_date","");
        if(!date_2.equals(""))
        {
            last_date.setText("Fecha de despido: " + date_2);
        }
        else
        {
            last_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dd = new DatePickerDialog(AguinaldoActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = "";
                            if (dayOfMonth < 10 && month < 9)
                            {
                                date = year + "-"+ "0" + (month + 1) + "-" +"0" + dayOfMonth;
                            }
                            else if (dayOfMonth < 10 && month >= 9)
                            {
                                date = year + "-" + (month + 1) + "-" +"0"+ dayOfMonth;
                            }
                            else if (dayOfMonth >= 10 && month >= 9)
                            {
                                date = year + "-" + (month + 1) + "-" + dayOfMonth;
                            }
                            else if (dayOfMonth >= 10 && month < 9)
                            {
                                date = year + "-" + "0"+(month + 1) + "-" + dayOfMonth;
                            }
                            first_date.setText("Fecha de despido: " + date);

                            editor.putString("last_date", date);
                            editor.apply();
                        }
                    },Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                            Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                            Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                    );
                    dd.show();
                }
            });
        }

        Float average = sp.getFloat("average_salary", 0f);
        if(average != 0)
        {
            average_salary.setText(average.toString());
        }


    }

    private void Declaracion() {
        average_salary = findViewById(R.id.average_salary_agui);
        first_date = findViewById(R.id.first_date_agui);
        last_date = findViewById(R.id.last_date_agui2);
    }

    public void Calcular_aguinaldo(View view)
    {
        Float aguinaldo;
        aguinaldo = ((Float.parseFloat(average_salary.getText().toString()) / 365) * calcularDiasTranscurridos());
        Toast.makeText(this, "Aguinaldo pendiente: " + aguinaldo, Toast.LENGTH_SHORT).show();
        SharedPreferences sp1 = getSharedPreferences("PagosPendientes", Context.MODE_PRIVATE);
        SharedPreferences.Editor edi = sp1.edit();
        edi.putFloat("Aguinaldo", aguinaldo);
        edi.apply();
    }

    private float calcularDiasTranscurridos() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String fecha_inicio = sp.getString("first_date", "");
        String fecha_fin = sp.getString("last_date", "");

        try {
            // Convertir las cadenas de fecha a objetos Date
            Date fechaInicial = formato.parse(fecha_inicio);
            Date fechaFinal = formato.parse(fecha_fin);

            // Calcular los milisegundos transcurridos entre las fechas
            long diferencia = fechaFinal.getTime() - fechaInicial.getTime();

            // Convertir los milisegundos a días
            return diferencia / (1000f * 60f * 60f * 24f);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Retorna -1 en caso de error
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
}