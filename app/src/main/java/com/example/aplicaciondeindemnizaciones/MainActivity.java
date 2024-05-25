package com.example.aplicaciondeindemnizaciones;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.ConditionVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciondeindemnizaciones.Activities.AguinaldoActivity;
import com.example.aplicaciondeindemnizaciones.Activities.Home;
import com.example.aplicaciondeindemnizaciones.Activities.SalarioPromedioActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private EditText edt_user, edt_mail;
    private TextView tv_fecha_inicio, tv_salario, tv_continuar, tv_last_date;
    private String sp_first_date, sp_last_date;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String url = "https://www.youtube.com/watch?v=mK5s9n2mVMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Establecer valores en nada

        Borrar_Shared();

        Declaracion_variables();
        Declarar_Click();
    }

    private void Borrar_Shared() {
        // Borrar datos de usuaio
        sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getBoolean("Borrar", false))
        {
            editor.putBoolean("Borrar", false);
            editor.apply();
            return;
        }

        editor.putFloat("average_salary", 0f);
        editor.putString("last_date", "");
        editor.putString("mail_user", "");
        editor.putString("name_user", "");
        editor.putString("first_date", "");
        editor.apply();

        // Borrar operaciones
        sp = getSharedPreferences("PagosPendientes", Context.MODE_PRIVATE);
        editor = sp.edit();

        editor.putFloat("Salarios_pendiente", 0f);
        editor.putFloat("Prestaciones", 0f);
        editor.putFloat("Horas_debidas", 0f);
        editor.putFloat("indemnizacion", 0f);
        editor.putFloat("Bono", 0f);
        editor.putFloat("Aguinaldo", 0f);

        editor.apply();



    }

    public void Declarar_Click()
    {
        tv_last_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        tv_last_date.setText("Fecha de despido: " + date);
                        SharedPreferences.Editor editor = sp.edit();

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

        tv_fecha_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                        tv_fecha_inicio.setText("Fecha de inicio: " + sp_first_date);

                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("first_date", sp_first_date);
                        editor.apply();

                        tv_fecha_inicio.setText("Fecha inicial registrada: " + sp_first_date);
                    }
                }, Integer.parseInt(Obetener_fecha("yyyy")),
                        Integer.parseInt(Obetener_fecha("MM")) - 1,
                        Integer.parseInt(Obetener_fecha("dd"))
                );
                dd.show();
            }
        });

        tv_salario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SalarioPromedioActivity.class);
                startActivity(intent);
            }
        });

        tv_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!sp_first_date.equals("") && !sp_last_date.equals("") && !edt_user.getText().toString().equals("") && !edt_mail.getText().toString().equals("") && sp.getFloat("average_salary", 0f) !=  0f)
                {
                    String mail = edt_mail.getText().toString();
                    String name = edt_user.getText().toString();

                    editor.putString("last_date", sp_last_date);
                    editor.putString("first_date", sp_first_date);
                    editor.putString("name_user", name);
                    editor.putString("mail_user", mail);

                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Ingrese todos los datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        edt_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    try
                    {
                        editor.putString("name_user", edt_user.getText().toString());
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Nombre bien", Toast.LENGTH_SHORT).show();

                    }catch (Exception ex)
                    {
                        Toast.makeText(MainActivity.this, "Nombre fallido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        edt_mail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    try
                    {
                        editor.putString("mail_user",edt_mail.getText().toString());
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Mail correct", Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(MainActivity.this, "Mail incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    // Enviar en url
    public void URL(View view){
        Uri _url = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, _url);
        startActivity(i);
    }

    // Funcion utilizada para la declaracion de variables
    public void Declaracion_variables()
    {
        tv_fecha_inicio = findViewById(R.id.fecha_inicio_inicio);
        tv_salario = findViewById(R.id.salario_promedio);
        tv_continuar = findViewById(R.id.Continuar);
        edt_user = findViewById(R.id.nombre_user);
        edt_mail = findViewById(R.id.mail_user);
        tv_last_date = findViewById(R.id.last_date);

        // Busqueda de Cache
        sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        editor = sp.edit();

        Float texto = sp.getFloat("average_salary", 0f);
        tv_salario.setText("Salario: "+String.valueOf(texto));
        edt_user.setText(sp.getString("name_user", ""));
        edt_mail.setText(sp.getString("mail_user", ""));
        sp_first_date = sp.getString("first_date", "");
        tv_fecha_inicio.setText("Fecha de inicio: "+sp_first_date);
        sp_last_date = sp.getString("last_date","");
        tv_last_date.setText("Fecha de despido: "+sp_last_date);



//        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putFloat("average_salary", 0f);
//        editor.apply();
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
}