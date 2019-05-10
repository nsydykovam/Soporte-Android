package omniknow.soporte.reportemantenimiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import omniknow.soporte.reportemantenimiento.dao.ReporteMantenimientoDao;
import omniknow.soporte.reportemantenimiento.dao.EstadoReporteDao;
import omniknow.soporte.reportemantenimiento.dao.TipoMantenimientoDao;
import omniknow.soporte.reportemantenimiento.extras.Constante;
import omniknow.soporte.reportemantenimiento.modelo.EstadoReporte;
import omniknow.soporte.reportemantenimiento.modelo.TipoMantenimiento;

public class EditarReporte extends AppCompatActivity {
    Spinner sEstado, sTipo;
    EditText etAsunto, etDescripcion, etSolucion;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_reporte);

        sEstado = (Spinner) findViewById(R.id.sEstado);
        sTipo = (Spinner) findViewById(R.id.sTipo);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etSolucion = (EditText) findViewById(R.id.etSolucion);
        bGuardar = (Button) findViewById(R.id.bGuardar);

        cargarSpinner(sEstado, 0);
        cargarSpinner(sTipo, 1);

        sEstado.setSelection(Constante.reporteActual.getIdEstadoReporte() - 1);
        sTipo.setSelection(Constante.reporteActual.getIdTipoMantenimiento() - 1);
        etAsunto.setText(Constante.reporteActual.getAsunto());
        etDescripcion.setText(Constante.reporteActual.getDescripcion());
        etSolucion.setText(Constante.reporteActual.getSolucion());
    }

    public void cargarSpinner(Spinner spinner, int tipo) {
        EstadoReporteDao estadoReporteDao = new EstadoReporteDao();
        TipoMantenimientoDao tipoMantenimientoDao = new TipoMantenimientoDao();

        List<EstadoReporte> estados;
        List<TipoMantenimiento> tipos;
        List<String> etiquetas = new ArrayList<>();
        if(tipo == 0) {
            estados = estadoReporteDao.ver(this);
            for(EstadoReporte i : estados)
                if(i.getIdEstadoReporte() < 3)
                    etiquetas.add(i.getNombre());
        }
        else if(tipo == 1) {
            tipos = tipoMantenimientoDao.ver(this);
            for(TipoMantenimiento i : tipos)
                etiquetas.add(i.getNombre());
        }
        else
            System.out.println("\n\nError al cargar spinners\n\n");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, etiquetas);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void guardar(View v) {
        ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();

        int idEstadoReporte = sEstado.getSelectedItemPosition() + 1;
        String asunto = etAsunto.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String solucion = etSolucion.getText().toString();

        Constante.reporteActual.setIdEstadoReporte(idEstadoReporte);
        Constante.reporteActual.setAsunto(asunto);
        Constante.reporteActual.setDescripcion(descripcion);
        Constante.reporteActual.setSolucion(solucion);

        reporteMantenimientoDao.cambio(this, Constante.reporteActual);

        Toast.makeText(EditarReporte.this, "Reporte actualizado", Toast.LENGTH_LONG).show();
        Intent i = new Intent(EditarReporte.this, VerReporte.class);
        startActivity(i);
    }
}
