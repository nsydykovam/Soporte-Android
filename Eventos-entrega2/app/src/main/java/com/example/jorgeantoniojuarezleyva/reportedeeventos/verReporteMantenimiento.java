package com.example.jorgeantoniojuarezleyva.reportedeeventos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO.impEvento;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO.impUsuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Evento;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Usuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.sUsuario;

public class verReporteMantenimiento extends AppCompatActivity {

    private TextView txtUsuario;
    private TextView txtFecha;
    private TextView txtProblema;

    private EditText txtSolucion;


    private Button btnCerrar;

    private int idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte_mantenimiento);

        Intent int1 = getIntent();
        idEvento = Integer.parseInt(int1.getStringExtra("idEvento"));

        final impEvento impEvent = new impEvento(verReporteMantenimiento.this);
        final Evento event = impEvent.traeEvento(idEvento);

        impUsuario impUser = new impUsuario(verReporteMantenimiento.this);
        Usuario user = impUser.traeUsuario(event.getIdUsuario());


        txtUsuario = (TextView)findViewById(R.id.txtUser);
        txtFecha = (TextView)findViewById(R.id.txtFecha);
        txtProblema = (TextView)findViewById(R.id.txtProblema);

        txtSolucion = (EditText)findViewById(R.id.txtSolucion);

        btnCerrar = (Button)findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impEvento impEvent = new impEvento(verReporteMantenimiento.this);
                String res = impEvent.responder(txtSolucion.getText().toString(),idEvento);
                Toast.makeText(verReporteMantenimiento.this,res,Toast.LENGTH_SHORT).show();
                if (sUsuario.tipo == 3) {
                    Intent int1 = new Intent(verReporteMantenimiento.this, verGerente.class);
                    startActivity(int1);
                }else if (sUsuario.tipo == 2){
                    Intent int1 = new Intent(verReporteMantenimiento.this, verInge.class);
                    startActivity(int1);
                }
            }
        });


        txtUsuario.setText(user.getNombre() + "");
        txtProblema.setText(event.getProblema() + "");
        txtFecha.setText(event.getFecha() + "");




    }
}
