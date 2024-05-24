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

import com.example.aplicaciondeindemnizaciones.Activities.Home;
import com.example.aplicaciondeindemnizaciones.Activities.SalarioPromedioActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private EditText edt_user, edt_mail;
    private TextView tv_fecha_inicio, tv_salario, tv_continuar;
    private String sp_first_date;
    private SharedPreferences sp;
    private String url = "https://www.youtube.com/watch?v=mK5s9n2mVMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Declaracion_variables();
        Declarar_Click();
    }

    public void Declarar_Click()
    {
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


                        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
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
                sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);

                tv_fecha_inicio.setText( "Fecha de inicio: " + sp.getString("first_date", ""));
                tv_salario.setText( "Salario Promedio: " + sp.getFloat("average_salary", 0));

                String mail = edt_mail.getText().toString();
                String name = edt_user.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name_user", name);
                editor.putString("mail_user", mail);

                editor.apply();

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });


    }

    // Enviar en url
    public void URL(View view){
//        Uri _url = Uri.parse(url);
        Intent i = new Intent(this, MainActivity2.class);
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

        sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
        Float texto = sp.getFloat("average_salary", 0f);
        tv_salario.append(String.valueOf(texto));


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