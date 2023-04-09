package com.alebc96.acme_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FilterActivity extends AppCompatActivity {
    EditText fechaInicio, fechaFin;
    Button filtrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        fechaInicio = findViewById(R.id.editTextDate_inicio);
        fechaFin = findViewById(R.id.editTextDate_final);
        Button btn_fechaInicio = findViewById(R.id.button_date_inicio);
        Button btn_fechaFinal = findViewById(R.id.button_date_final);
        filtrar = findViewById(R.id.button_filtrar);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_filter);

        fechaInicio.addTextChangedListener(textWatcher);
        fechaFin.addTextChangedListener(textWatcher);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_filter:

                    return true;
                case R.id.bottom_favorites:
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_trips:
                    startActivity(new Intent(getApplicationContext(), TravelListActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        btn_fechaInicio.setOnClickListener(view -> {
            mostrarCalendario(view, fechaInicio);
        });

        btn_fechaFinal.setOnClickListener(view -> {
            mostrarCalendario(view, fechaFin);
        });
        filtrar.setOnClickListener(view -> {
            //SharedPreferences shaaredPref = getSharedPreferences("Filtrado", Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = shaaredPref.edit();
            //editor.putString("fecha_inicio", fechaInicio.getText().toString());
            //editor.putString("fecha_final", fechaFin.getText().toString());
            //editor.commit();
            Intent intent = new Intent(this, TravelListActivity.class);
            intent.putExtra("fecha_inicio", fechaInicio.getText().toString());
            intent.putExtra("fecha_fin", fechaFin.getText().toString());
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Se filtraron los datos", Toast.LENGTH_SHORT).show();
            //Log.d("GUARDADO", shaaredPref.getString("fecha_inicio", "default"));
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String fecha1 = fechaInicio.getText().toString().trim();
            String fecha2 = fechaFin.getText().toString().trim();
            //Log.d("GUARDADO", fecha1);
            if (esFechaValida(fecha1) && esFechaValida(fecha2)) {
                filtrar.setEnabled(true);
            } else {
                filtrar.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };


    public void mostrarCalendario(View v, TextView tv){
        DatePickerDialog d = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tv.setText(day+"/"+(month+1)+"/"+year);
            }
        }, 2023, 0, 1);
        d.show();
    }

    public boolean esFechaValida(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);

        try {
            formatoFecha.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}