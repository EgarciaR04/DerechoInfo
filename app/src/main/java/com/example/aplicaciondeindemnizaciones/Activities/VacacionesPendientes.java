package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class VacacionesPendientes extends AppCompatActivity {
    private CheckBox docente_work;
    private EditText vacation_days;
    private TextView first_date, last_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacaciones_pendientes);

        Declaracion();

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
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
                    DatePickerDialog dd = new DatePickerDialog(VacacionesPendientes.this, new DatePickerDialog.OnDateSetListener() {
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

                            SharedPreferences.Editor edi = sp.edit();
                            edi.putString("first_date", date);
                            edi.apply();
                        }
                    },Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                            Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                            Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                    );
                    dd.show();
                }
            });
        }

        String date_2 = sp.getString("last_date", "");
        if(!date_2.equals(""))
        {
            last_date.setText("Fecha de despido: " + date_2);
        }
        else
        {
            last_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dd =new DatePickerDialog(VacacionesPendientes.this, new DatePickerDialog.OnDateSetListener() {
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

                            SharedPreferences.Editor edi = sp.edit();
                            edi.putString("last_date", date);
                            edi.apply();
                        }
                    },Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")),
                            Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,
                            Integer.parseInt(obtenerFecha("dd", "America/Guatemala"))
                    );
                    dd.show();
                }
            });
        }

        docente_work.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    vacation_days.setEnabled(false);
                }
                else
                {
                    vacation_days.setEnabled(true);
                }
            }
        });
    }

    public void Calculo_vacaciones(View v)
    {
        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Float average_salary = sp.getFloat("average_salary", 0f);

        if (average_salary == 0)
        {
            Toast.makeText(this, "Regrese a ingresar su salario promedio", Toast.LENGTH_SHORT).show();
            return;
        }

        if(calcularDiasTranscurridos() == -1)
        {
            Toast.makeText(this, "Ingrese sus fechas de inicio y despido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(docente_work.isChecked())
        {
            Float prestaciones = average_salary * 2f;
            prestaciones = prestaciones / 304f;
            prestaciones = prestaciones * calcularDiasTranscurridos();
            Toast.makeText(this, "Vacaciones pendientes: " + prestaciones, Toast.LENGTH_SHORT).show();

            // Guardar info
            SharedPreferences sp1 = getSharedPreferences("PagosPendientes", Context.MODE_PRIVATE);
            SharedPreferences.Editor edi = sp1.edit();
            edi.putFloat("Prestaciones", prestaciones);
            edi.apply();
        }
        else
        {
            if(vacation_days.getText().toString().equals(""))
            {
                Toast.makeText(this, "Ingrese sus dias de vacaciones", Toast.LENGTH_SHORT).show();
                return;
            }
            
            Float prestaciones = ((average_salary / 365) * (Float.parseFloat(vacation_days.getText().toString()) / 30)) * calcularDiasTranscurridos();
            Toast.makeText(this, "Vacaciones pendientes: " + prestaciones, Toast.LENGTH_SHORT).show();
        }
    }

    private void Declaracion() {
        docente_work = findViewById(R.id.docente_check);
        vacation_days = findViewById(R.id.vacation_days);
        first_date = findViewById(R.id.first_date_vac);
        last_date = findViewById(R.id.last_date_vac);
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

    private float calcularDiasTranscurridos() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
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
}