package com.example.aplicaciondeindemnizaciones.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aplicaciondeindemnizaciones.R;

public class SalarioPromedioActivity extends AppCompatActivity {
    private EditText first_salary, second_salary, third_salary, fourth_salary, fifth_salary, sixth_salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salario_promedio);

        declaracion();

    }

    public void Toma_de_datos(View v){
        Integer average_salary;
        average_salary = Average();
        SP_average(average_salary);
        Intent intent = new Intent(this, Indemnizaciones.class);
        startActivity(intent);
    }



    private Integer Average()
    {
        Integer average_salary;
        average_salary = Integer.parseInt(first_salary.getText().toString()) + Integer.parseInt(second_salary.getText().toString()) +
                Integer.parseInt(third_salary.getText().toString()) + Integer.parseInt(fourth_salary.getText().toString()) +
                Integer.parseInt(fifth_salary.getText().toString()) + Integer.parseInt(sixth_salary.getText().toString());
        average_salary = average_salary / 6;
        return average_salary;
    }

    private void SP_average(Integer average)
    {

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
//        SharedPreferences.Editor = sp.edit();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("average_salary", average);
        editor.apply();
    }

    // Funcion usada para declarar variables
    private void declaracion() {
        first_salary = findViewById(R.id.first_salary);
        second_salary = findViewById(R.id.second_salary);
        third_salary = findViewById(R.id.third_salary);
        fourth_salary = findViewById(R.id.fourth_salary);
        fifth_salary = findViewById(R.id.fifth_salary);
        sixth_salary = findViewById(R.id.sixth_salary);
    }
}