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
import java.time.format.DateTimeFormatter;
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
        Float salary = sp.getFloat("average_salary", 0f);
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

    private static String Obetener_fecha(String formato)
    {

        Date fecha_actual = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formato);
        String fecha_formateada = format.format(fecha_actual);

        return fecha_formateada;
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

    public void salarioPromedio(View v)
    {
        Intent intent = new Intent(this, SalarioPromedioActivity.class);
        startActivity(intent);
    }

    public void reiniciarSalario(View v)
    {
        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("average_salary",0f);
        editor.apply();

        average_salary.setText("Salario promedio registrado: " + 0.0);

    }

    public void Registrar_fecha_inicial(View v)
    {
        DatePickerDialog dd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                if (dayOfMonth < 10 && month < 9)
                {
                    sp_first_date = year + "-"+ "0" + (month + 1) + "-" +"0" +dayOfMonth;
                }
                else if (dayOfMonth < 10 && month >= 9)
                {
                    sp_first_date =year + "-" + (month + 1) + "-" + "0" +dayOfMonth;
                }
                else if (dayOfMonth >= 10 && month >= 9)
                {
                    sp_first_date = year + "-" + (month + 1) + "-" + dayOfMonth;
                }
                else if (dayOfMonth >= 10 && month < 9)
                {
                    sp_first_date = year + "-" + "0"+(month + 1) + "-" + dayOfMonth;
                }


                SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("first_date", sp_first_date);
                editor.apply();

                first_date.setText("Fecha inicial registrada: " + sp_first_date);
            }
        }, Integer.parseInt(Obetener_fecha("yyyy")),
                Integer.parseInt(Obetener_fecha("MM")) - 1,
                Integer.parseInt(Obetener_fecha("dd"))
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