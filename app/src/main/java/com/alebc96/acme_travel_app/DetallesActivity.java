package com.alebc96.acme_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetallesActivity extends AppCompatActivity {

    TextView city_name, lugar_salida;
    TextView fecha_salida;
    TextView fecha_llegada;
    TextView precio;

    ImageView img_city;

    Button button_pagar_viaje, button_add_favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_trips);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), InicioActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_filter:
                    startActivity(new Intent(getApplicationContext(), FilterActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
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


        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");

        city_name = findViewById(R.id.textView_cityName_item);
        fecha_salida = findViewById(R.id.textView_fecha_salida_item);
        fecha_llegada = findViewById(R.id.textView_fecha_llegada_item);
        precio = findViewById(R.id.textView_precio_item);
        img_city = findViewById(R.id.imageView_city);
        lugar_salida = findViewById(R.id.lugar_salida);
        button_pagar_viaje = findViewById(R.id.button_pagar_viaje);
        button_add_favoritos = findViewById(R.id.button_add_favoritos);

        city_name.setText(element.getCity_name());
        fecha_llegada.setText(element.getFecha_llegada());
        fecha_salida.setText(element.getFecha_salida());
        precio.setText(element.getPrecio());
        img_city.setImageResource(element.getImgResource());
        lugar_salida.setText(element.getLugar_salida());

        button_add_favoritos.setOnClickListener(view -> {
            Intent intent = new Intent(this, TravelListActivity.class);
            startActivity(intent);
            element.setFavorito(!element.getFavorito());
            Log.d("FAVORITOOOOOOOOOOOOO", element.getFavorito().toString());
        });

    }
}