package omniknow.soportefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import omniknow.soportefinal.dao.impEvento;
import omniknow.soportefinal.dao.impUsuario;
import omniknow.soportefinal.modelo.Evento;
import omniknow.soportefinal.modelo.Usuario;

public class verReporteCerrado extends AppCompatActivity {
    private TextView txtUsuario;
    private TextView txtFecha;
    private TextView txtProblema;
    private TextView txtSolucion;

    private int idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte_cerrado);


        Intent int1 = getIntent();
        idEvento = Integer.parseInt(int1.getStringExtra("idEvento"));

        final impEvento impEvent = new impEvento(verReporteCerrado.this);
        final Evento event = impEvent.traeEvento(idEvento);

        impUsuario impUser = new impUsuario(verReporteCerrado.this);
        Usuario user = impUser.traeUsuario(event.getIdUsuario());


        txtUsuario = (TextView)findViewById(R.id.txtUser);
        txtFecha = (TextView)findViewById(R.id.txtFecha);
        txtProblema = (TextView)findViewById(R.id.txtProblema);
        txtSolucion = (TextView)findViewById(R.id.txtSolucion);

        txtUsuario.setText(user.getNombre() + "");
        txtProblema.setText(event.getProblema() + "");
        txtFecha.setText(event.getFecha() + "");
        txtSolucion.setText(event.getSolucion() + "");
    }
}
