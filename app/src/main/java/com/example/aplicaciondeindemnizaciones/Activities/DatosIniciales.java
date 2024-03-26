package com.example.aplicaciondeindemnizaciones.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.aplicaciondeindemnizaciones.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DatosIniciales extends AppCompatActivity {
    private TextView inicial_date, firet_date;
    private String[] opcion = {"injustificado", "Invalidez"};
    private Spinner justificacion;
    private EditText age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_iniciales);
        DeclararVariables();
        age.setEnabled(false);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opcion);
        justificacion.setAdapter(adaptador);
//        justificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                age.setEnabled(true);
//                age.setHint("Ingrese la edad del trabajador");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void enterInicialDate(View v)
    {
        Fecha_inicial();
        justificacion = findViewById(R.id.justi);
        age = findViewById(R.id.age_edt);

    }

    private void DeclararVariables() {
        inicial_date = findViewById(R.id.tv_inicial);
    }

    public static String obtenerFecha(String formato, String zonaHoraria)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }

    public void Fecha_inicial()
    {
        DatePickerDialog date =new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                inicial_date.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                inicial_date.setTextSize(20);
            }
        }, Integer.parseInt(obtenerFecha("yyyy", "America/Guatemala")), Integer.parseInt(obtenerFecha("MM", "America/Guatemala")) - 1,Integer.parseInt(obtenerFecha("dd", "America/Guatemala")));
        date.show();
    }
}