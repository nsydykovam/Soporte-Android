package omniknow.soporte.reportemantenimiento;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import omniknow.soporte.reportemantenimiento.dao.ReporteMantenimientoDao;
import omniknow.soporte.reportemantenimiento.dao.EstadoReporteDao;
import omniknow.soporte.reportemantenimiento.dao.TipoMantenimientoDao;
import omniknow.soporte.reportemantenimiento.extras.Constante;
import omniknow.soporte.reportemantenimiento.modelo.ReporteMantenimiento;

public class VerReporte extends AppCompatActivity {
    TextView tvTitulo, tvFecha, tvTipo, tvEstado, tvDescripcion, tvSolucion, tvHoras;
    Button bPagar, bEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte);

        TipoMantenimientoDao tipoMantenimientoDao = new TipoMantenimientoDao();
        EstadoReporteDao estadoReporteDao = new EstadoReporteDao();

        ReporteMantenimiento reporteMantenimiento = Constante.reporteActual;

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvTipo = (TextView) findViewById(R.id.tvTipo);
        tvEstado = (TextView) findViewById(R.id.tvEstado);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvSolucion = (TextView) findViewById(R.id.tvSolucion);
        tvHoras = (TextView) findViewById(R.id.tvHoras);
        bPagar = (Button) findViewById(R.id.bPagar);
        bEditar = (Button) findViewById(R.id.bEditar);

        if(reporteMantenimiento.getAsunto().equals(""))
            tvTitulo.setText("Reporte " + reporteMantenimiento.getIdReporteMantenimiento());
        else
            tvTitulo.setText(reporteMantenimiento.getAsunto());
        tvFecha.setText(reporteMantenimiento.getFecha().toString());
        tvTipo.setText(tipoMantenimientoDao.consulta(this, reporteMantenimiento.getIdTipoMantenimiento()).getNombre());
        tvEstado.setText(estadoReporteDao.consulta(this, reporteMantenimiento.getIdEstadoReporte()).getNombre());
        tvHoras.setText(reporteMantenimiento.getHoras() + " ($" + 60*reporteMantenimiento.getHoras() + ")");
        tvDescripcion.setText(reporteMantenimiento.getDescripcion());
        if(reporteMantenimiento.getSolucion().equals(""))
            tvSolucion.setText("El problema no ha sido solucionado.");
        else
            tvSolucion.setText(reporteMantenimiento.getSolucion());
        if(reporteMantenimiento.getIdEstadoReporte() > 3) {
            bPagar.setVisibility(View.GONE);
            bEditar.setVisibility(View.GONE);
        }


    }

    public void eliminar(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Eliminar reporte")
                .setMessage("¿Desea eliminar el reporte \"" + tvTitulo.getText() + "\"?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();
                        reporteMantenimientoDao.baja(VerReporte.this, Constante.reporteActual);
                        Toast.makeText(VerReporte.this, "Reporte eliminado", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(VerReporte.this, ListaReportes.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void editar(View view) {
        Intent i = new Intent(VerReporte.this, EditarReporte.class);
        startActivity(i);
    }

    public void pagar(View view) {
        if(Constante.reporteActual.getIdEstadoReporte() < 3) // Si el reporte no está cerrado (está antes que cerrado)
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Error al pagar reporte")
                .setMessage("Solamente un reporte cerrado puede ser pagado.")
                .setPositiveButton("Aceptar", null)
                .show();
        else {
            ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();
            Constante.reporteActual.setIdEstadoReporte(4);
            reporteMantenimientoDao.cambio(this, Constante.reporteActual);
            Toast.makeText(VerReporte.this, "Reporte pagado", Toast.LENGTH_LONG).show();
            finish();
            Intent i = new Intent(VerReporte.this, ListaReportes.class);
            startActivity(i);
        }
    }
}
