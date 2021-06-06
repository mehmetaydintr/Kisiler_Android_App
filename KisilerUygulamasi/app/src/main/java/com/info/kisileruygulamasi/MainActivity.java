package com.info.kisileruygulamasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.info.kisileruygulamasi.Adapters.KisilerAdapter;
import com.info.kisileruygulamasi.DatabaseHelper.KisilerDao;
import com.info.kisileruygulamasi.DatabaseHelper.Veritabani;
import com.info.kisileruygulamasi.Objects.Kisiler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;

    private ArrayList<Kisiler> kisilers;
    private Veritabani veritabani;
    private KisilerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.floatingActionButton);

        toolbar.setTitle("Kişiler");
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGoster();
            }
        });

        veritabani = new Veritabani(this);
        kisilers = new KisilerDao().tumKisiler(veritabani);

        adapter = new KisilerAdapter(this, kisilers, veritabani);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        kisilers = new KisilerDao().kisiAra(veritabani,query);
        adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
        rv.setAdapter(adapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        kisilers = new KisilerDao().kisiAra(veritabani,newText);
        adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
        rv.setAdapter(adapter);
        return false;
    }

    public void alertGoster(){
        LayoutInflater layout = LayoutInflater.from(this);
        View tasarim = layout.inflate(R.layout.alert_tasarim,null);

        EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Kişi Ekle");
        ad.setView(tasarim);
        ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String kisiAd = editTextAd.getText().toString().trim();
                String kisiTel = editTextTel.getText().toString().trim();

                new KisilerDao().kisiEkle(veritabani,kisiAd,kisiTel);

                kisilers = new KisilerDao().tumKisiler(veritabani);
                adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
                rv.setAdapter(adapter);

            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();
    }
}