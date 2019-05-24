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

public class altaOperador extends AppCompatActivity {

    private EditText txtUsuario = null;
    private EditText txtProblema = null;

    private TextView ctxtNombre = null;

    private Button btnBuscar = null;
    private Button btnOk = null;

    private Usuario user1 = null;
    private Evento event1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_operador);


        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtProblema = (EditText) findViewById(R.id.txtProblema);

        ctxtNombre = (TextView) findViewById(R.id.ctxtProblema);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnOk = (Button)findViewById(R.id.btnOk);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                impUsuario impUser = new impUsuario(altaOperador.this);
                String usuario = txtUsuario.getText().toString();
                user1 = impUser.traeUsuario(usuario);
                if(user1 == null)
                {
                    ctxtNombre.setText("No se encontró");
                }else
                {
                    ctxtNombre.setText(user1.getNombre()  + "");
                }

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                impEvento impEvent = new impEvento(altaOperador.this);
                String problema = txtProblema.getText().toString();

                event1 = new Evento(0,user1.getIdUsuario(),problema,"","ABIERTO",6,"");
                String resultado = impEvent.altaReporte(event1,user1);
                Toast.makeText(altaOperador.this,resultado,Toast.LENGTH_SHORT).show();
                Intent int1 = new Intent(altaOperador.this,verOperador.class);
                startActivity(int1);

            }
        });

    }


}
