package omniknow.soportefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import omniknow.soportefinal.dao.impEvento;
import omniknow.soportefinal.dao.impUsuario;
import omniknow.soportefinal.modelo.Evento;
import omniknow.soportefinal.modelo.Usuario;

public class cambiarReporte extends AppCompatActivity {

    private EditText txtUsuario = null;
    private EditText txtProblema = null;

    private TextView ctxtNombre = null;

    private Button btnBuscar = null;
    private Button btnOk = null;

    private Usuario user = null;
    private Evento event = null;

    private int idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_reporte);

        Intent int1 = getIntent();
        idEvento  = Integer.parseInt(int1.getStringExtra("idEvento"));

        impEvento impEvent = new impEvento(cambiarReporte.this);
        event = impEvent.traeEvento(idEvento);

        impUsuario impUser = new impUsuario(cambiarReporte.this);
        user = impUser.traeUsuario(event.getIdUsuario());

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtProblema = (EditText) findViewById(R.id.txtProblema);

        ctxtNombre = (TextView) findViewById(R.id.ctxtProblema);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnOk = (Button)findViewById(R.id.btnOk);

        txtUsuario.setText(user.getUsuario() + "");
        txtProblema.setText(event.getProblema() + "");
        ctxtNombre.setText(user.getNombre() + "");

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                impUsuario impUser = new impUsuario(cambiarReporte.this);
                String usuario = txtUsuario.getText().toString();
                user = impUser.traeUsuario(usuario);
                if(user == null)
                {
                    ctxtNombre.setText("No se encontró");
                }else
                {
                    ctxtNombre.setText(user.getNombre()  + "");
                }

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                impEvento impEvent = new impEvento(cambiarReporte.this);
                String problema = txtProblema.getText().toString();

                Evento event1 = new Evento(idEvento,user.getIdUsuario(),problema,"","ABIERTO",6,event.getFecha());
                String resultado = impEvent.cambiarReporte(event1,user);
                Toast.makeText(cambiarReporte.this,resultado,Toast.LENGTH_SHORT).show();
                Intent int1 = new Intent(cambiarReporte.this,verGerente.class);
                startActivity(int1);

            }
        });



    }
}
