package com.alebc96.acme_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    List<ListElement> elements;
    List<ListElement> filtredElements;
    Button filter;
    private String fecha_ini;
    private String fecha_fin;

    private EditText search;

    private Switch columns;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        //filter = findViewById(R.id.button_date_final);
        //search_view = findViewById(R.id.search_view);
        columns = findViewById(R.id.switch_columns_favoritos);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_favorites);

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

                    return true;
                case R.id.bottom_trips:
                    startActivity(new Intent(getApplicationContext(), TravelListActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        try {
            init();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() throws ParseException {
        filtredElements = new ArrayList<>();

        elements = new ArrayList<ListElement>();
        elements = initTrips();


            for(int i = 0; i < elements.size(); i++){
                if (elements.get(i).getFavorito() == true) {
                    filtredElements.add(elements.get(i));
                    Log.d("PRUEBAAAAAAAAAAAAAAAAAAA", elements.get(i).getCity_name());
                }
            }
        ListAdapter listAdapter = new ListAdapter(filtredElements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDetails(item);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_travels_favoritos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(listAdapter);

        columns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gridLayoutManager.setSpanCount(2);
                } else {
                    gridLayoutManager.setSpanCount(1);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
    public void moveToDetails(ListElement item){
        Intent intent = new Intent(this, DetallesActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);
    }

    public ArrayList<ListElement> initTrips(){
        elements = new ArrayList<ListElement>();
        elements.add(new ListElement("#775447", "Barcelona", "3/7/2023", "1/7/2023", "$ 300", R.drawable.barcelona, true, "Sevilla"));
        elements.add(new ListElement("#607d8b", "Madrid", "12/8/2023", "18/8/2023", "$ 400", R.drawable.madrid, true, "Sevilla"));
        elements.add(new ListElement("#775447", "Sevilla", "24/1/2023", "24/1/2023", "$ 500", R.drawable.sevilla, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Granada", "6/11/2023", "6/11/2023", "$ 100", R.drawable.granada, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Cadiz", "9/9/2023", "9/9/2023", "$ 89", R.drawable.cadiz, false, "Sevilla"));

        elements.add(new ListElement("#775447", "Barcelona", "3/7/2023", "1/7/2023", "$ 300", R.drawable.barcelona, false, "Sevilla"));
        elements.add(new ListElement("#607d8b", "Madrid", "12/8/2023", "18/8/2023", "$ 400", R.drawable.madrid, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Sevilla", "24/1/2023", "24/1/2023", "$ 500", R.drawable.sevilla, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Granada", "6/11/2023", "6/11/2023", "$ 100", R.drawable.granada, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Cadiz", "9/9/2023", "9/9/2023", "$ 89", R.drawable.cadiz, true, "Sevilla"));

        elements.add(new ListElement("#775447", "Barcelona", "3/7/2023", "1/7/2023", "$ 300", R.drawable.barcelona, false, "Sevilla"));
        elements.add(new ListElement("#607d8b", "Madrid", "12/8/2023", "18/8/2023", "$ 400", R.drawable.madrid, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Sevilla", "24/1/2023", "24/1/2023", "$ 500", R.drawable.sevilla, true, "Sevilla"));
        elements.add(new ListElement("#775447", "Granada", "6/11/2023", "6/11/2023", "$ 100", R.drawable.granada, false, "Sevilla"));
        elements.add(new ListElement("#775447", "Cadiz", "9/9/2023", "9/9/2023", "$ 89", R.drawable.cadiz, false, "Sevilla"));
        return (ArrayList<ListElement>) elements;
    }
}