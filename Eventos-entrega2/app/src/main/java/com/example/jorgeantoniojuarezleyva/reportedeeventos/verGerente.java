package com.example.jorgeantoniojuarezleyva.reportedeeventos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO.impEvento;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class verGerente extends AppCompatActivity {

    private ListView lista = null;

    private Button btnMas = null;
    private Button btnCambiar = null;
    private Button btnBorrar = null;

    private List<Map<String, String>> arrayLista = new ArrayList<Map<String, String>>();
    private ArrayList<String> arrayIDs = new ArrayList<>();

    private Object mActionMode;
    private int idReporteBorrar;

    private String estadoLista = "ABIERTO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_gerente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        lista = (ListView)findViewById(R.id.lista);
        LlenarLista();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (estadoLista.equals("ABIERTO"))
                {
                    Intent intResponder = new Intent(verGerente.this,verReporteAbierto.class);
                    intResponder.putExtra("idEvento",arrayIDs.get(position));
                    startActivity(intResponder);
                }else if (estadoLista.equals("CERRADO"))
                {
                    Intent intVer = new Intent(verGerente.this,verReporteCerrado.class);
                    intVer.putExtra("idEvento",arrayIDs.get(position));
                    startActivity(intVer);

                }else if (estadoLista.equals("MANTENIMIENTO"))
                {
                    Intent intVer = new Intent(verGerente.this,verReporteMantenimiento.class);
                    intVer.putExtra("idEvento",arrayIDs.get(position));
                    startActivity(intVer);

                }
                else if (estadoLista.equals("SOLUCIONADO"))
                {
                    Intent intVer = new Intent(verGerente.this,verReporteSolucionado.class);
                    intVer.putExtra("idEvento",arrayIDs.get(position));
                    startActivity(intVer);

                }
            }
        });


        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                idReporteBorrar = Integer.parseInt(arrayIDs.get(position));
                mActionMode = verGerente.this.startActionMode(menuOpciones);
                return true;
            }
        });
    }

    //ESTE ES EL MENU DE LOS TRES PUNTITOS PARA CAMBIAR EL TIPO DE LISTA
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Abierto:
                estadoLista = "ABIERTO";
                break;
            case R.id.Cerrado:
                estadoLista = "CERRADO";
                break;
            case R.id.Mantenimiento:
                estadoLista = "MANTENIMIENTO";
                break;
            case R.id.Solucionado:
                estadoLista = "SOLUCIONADO";
                break;
            case R.id.close:
                Intent int1 = new Intent(verGerente.this,MainActivity.class);
                startActivity(int1);

        }
        arrayLista.removeAll(arrayLista);
        arrayIDs.removeAll(arrayIDs);
        LlenarLista();
        return true;
    }


    //ESTE ES EL OTRO MENU EN DONDE ESTAN LOS BOTONES DE EDITAR Y NBORRAR, SOLAMENTE VÁLIDOS PARA LSO ABIERTOS
    private ActionMode.Callback menuOpciones = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.btnBorrar)
            {

                impEvento impEvent =  new impEvento(verGerente.this);
                impEvent.borrar(idReporteBorrar);
                LlenarLista();
                mode.finish();

            } else if (item.getItemId() == R.id.btnCambiar)
            {

                Intent intEditar = new Intent(verGerente.this,cambiarReporte.class);
                intEditar.putExtra("idEvento",idReporteBorrar + "");
                startActivity(intEditar);
                finish();
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

    public void LlenarLista()
    {

        arrayLista.removeAll(arrayLista);
        arrayIDs.removeAll(arrayIDs);

        SQLite bh = new SQLite(verGerente.this,"EVENTOS",null,1);
        SQLiteDatabase BD = bh.getReadableDatabase();
        Cursor res = BD.rawQuery("select * from EVENTOS where estado = '"+estadoLista+"' and idInge = 6;",null);


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
            SimpleAdapter adapter = new SimpleAdapter(this, arrayLista,//mi array de mapas(el del inicio)
                    android.R.layout.simple_list_item_2,//default
                    new String[] {"reporte", "fechaUser"},//en nombre de los campos que puse arriba, como si fuera su "id"
                    new int[] {android.R.id.text1, android.R.id.text2});// default

            lista.setAdapter(adapter);
            Toast.makeText(verGerente.this,"No hay reportes registrados",Toast.LENGTH_SHORT).show();
        }
    }
}
