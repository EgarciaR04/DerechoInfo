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
        Float average_salary;
        average_salary = Average();
        SP_average(average_salary);
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }




    private Float Average()
    {
        Float average_salary = 0f;
//        average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString()) +
//                Float.parseFloat(third_salary.getText().toString()) + Float.parseFloat(fourth_salary.getText().toString()) +
//                Float.parseFloat(fifth_salary.getText().toString()) + Float.parseFloat(sixth_salary.getText().toString());
//        average_salary = average_salary / 6;
        if (third_salary.getText().toString().equals(""))
        {
            average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString());
            average_salary = average_salary/2;
        }
        else if(fourth_salary.getText().toString().equals(""))
        {
            average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString()) +
                    Float.parseFloat(third_salary.getText().toString());
            average_salary = average_salary / 3;
        }
        else if (fifth_salary.getText().toString().equals(""))
        {
            average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString()) +
                    Float.parseFloat(third_salary.getText().toString()) + Float.parseFloat(fourth_salary.getText().toString());
            average_salary = average_salary / 4;
        }

        else if (sixth_salary.getText().toString().equals(""))
        {
            average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString()) +
                    Float.parseFloat(third_salary.getText().toString()) + Float.parseFloat(fourth_salary.getText().toString()) +
                    Float.parseFloat(fifth_salary.getText().toString());
            average_salary = average_salary / 5;
        }
        else if (!sixth_salary.getText().toString().equals(""))
        {
            average_salary = Float.parseFloat(first_salary.getText().toString()) + Float.parseFloat(second_salary.getText().toString()) +
                    Float.parseFloat(third_salary.getText().toString()) + Float.parseFloat(fourth_salary.getText().toString()) +
                    Float.parseFloat(fifth_salary.getText().toString()) + Float.parseFloat(sixth_salary.getText().toString());
            average_salary = average_salary / 6;
        }

        return average_salary;
    }

    private void SP_average(Float average)
    {

        SharedPreferences sp = getSharedPreferences("Average_Salary", Context.MODE_PRIVATE);
//        SharedPreferences.Editor = sp.edit();
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("average_salary", average);
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