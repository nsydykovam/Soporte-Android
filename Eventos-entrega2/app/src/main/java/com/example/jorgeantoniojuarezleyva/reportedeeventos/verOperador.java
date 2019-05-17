package com.example.jorgeantoniojuarezleyva.reportedeeventos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class verOperador extends AppCompatActivity {

    private ListView lista = null;


    private List<Map<String, String>> arrayLista = new ArrayList<Map<String, String>>();
    private ArrayList<String> arrayIDs = new ArrayList<>();
    private ArrayList<String> arrayEstados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_operador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btnMas = (FloatingActionButton) findViewById(R.id.btnMas);
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(verOperador.this,altaOperador.class);
                startActivity(int1);
            }
        });


        lista = (ListView)findViewById(R.id.lista);
        LlenarLista();
    }

    public void LlenarLista()
    {
        SQLite bh = new SQLite(verOperador.this,"EVENTOS",null,1);
        SQLiteDatabase BD = bh.getReadableDatabase();
        Cursor res = BD.rawQuery("select * from EVENTOS;",null);


        if (res.moveToFirst())
        {
            do {
                String problema = "",solucion = "",estado = "", fecha = "";
                int idUsuario = 0,idEvento = 0,idInge = 0;

                idEvento = res.getInt(0);
                idUsuario = res.getInt(1);
                problema = res.getString(2);
                solucion = res.getString(3);
                estado = res.getString(4);
                idInge = res.getInt(5);
                fecha = res.getString(6);

                Evento event = new Evento(idEvento,idUsuario,problema,solucion,estado,idInge,fecha);

                //Declaro una lista de mapas para colocar 2 textos al inicio, una variable de instancia,
                // para usarla en mi documento entero

                arrayIDs.add(idEvento + "");
                arrayEstados.add(event.getEstado() + "");
                //Declaro mi elemento de lista, en este caso un objeto MAP
                Map<String, String> mapa = new HashMap<String, String>(2);

                //En mi mapa agrego los campos que quiero
                mapa.put("reporte", "Reporte" + event.getIdEvento());
                mapa.put("fechaUser", event.getFecha() + " \t " + event.getEstado() );

                //pongo mi mapa en el arrayList
                arrayLista.add(mapa);


            }while (res.moveToNext());


            //Creo un adapter que controla el elemento de la lista del list view
            SimpleAdapter adapter = new SimpleAdapter(this, arrayLista,//mi array de mapas(el del inicio)
                    android.R.layout.simple_list_item_2,//default
                    new String[] {"reporte", "fechaUser"},//en nombre de los campos que puse arriba, como si fuera su "id"
                    new int[] {android.R.id.text1, android.R.id.text2});// default

            lista.setAdapter(adapter);

        }else{
            Toast.makeText(verOperador.this,"No hay reportes registrados",Toast.LENGTH_SHORT).show();
        }
    }
}
