package omniknow.soporte.reportemantenimiento;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import omniknow.soporte.reportemantenimiento.dao.ReporteMantenimientoDao;
import omniknow.soporte.reportemantenimiento.dao.EstadoReporteDao;
import omniknow.soporte.reportemantenimiento.dao.TipoMantenimientoDao;
import omniknow.soporte.reportemantenimiento.dao.UsuarioDao;
import omniknow.soporte.reportemantenimiento.extras.Constante;
import omniknow.soporte.reportemantenimiento.modelo.EstadoReporte;
import omniknow.soporte.reportemantenimiento.modelo.ReporteMantenimiento;
import omniknow.soporte.reportemantenimiento.modelo.TipoMantenimiento;
import omniknow.soporte.reportemantenimiento.modelo.Usuario;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NuevoReporte extends AppCompatActivity {
    Spinner sEstado, sTipo, sProgramador;
    EditText etAsunto, etDescripcion, etSolucion, etHoras;
    Button bGuardar;
    TextView tvProgramador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_reporte);

        sEstado = (Spinner) findViewById(R.id.sEstado);
        sTipo = (Spinner) findViewById(R.id.sTipo);
        sProgramador = (Spinner) findViewById(R.id.sProgramador);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etSolucion = (EditText) findViewById(R.id.etSolucion);
        etHoras = (EditText) findViewById(R.id.etHoras);
        bGuardar = (Button) findViewById(R.id.bGuardar);
        tvProgramador = (TextView) findViewById(R.id.tvProgramador);

        cargarSpinner(sEstado, 0);
        cargarSpinner(sTipo, 1);
        cargarSpinner(sProgramador, 2);

        if(Constante.usuarioActual.getTipo() != 5) { // Si el usuario no es Gerente de mantenimiento
            tvProgramador.setVisibility(View.GONE);
            sProgramador.setVisibility(View.GONE);
        }
    }

    public void cargarSpinner(Spinner spinner, int tipo) {
        EstadoReporteDao estadoReporteDao = new EstadoReporteDao();
        TipoMantenimientoDao tipoMantenimientoDao = new TipoMantenimientoDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        List<EstadoReporte> estados;
        List<TipoMantenimiento> tipos;
        List<Usuario> usuarios;
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
        else if(tipo == 2) {
            usuarios = usuarioDao.ver(this);
            etiquetas.add("Ninguno");
            for(Usuario i : usuarios)
                if(i.getTipo() == 6) // Si es un programador
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
        int idEstadoReporte = sEstado.getSelectedItemPosition() + 1;
        String descripcion = etDescripcion.getText().toString();
        String solucion = etSolucion.getText().toString();

        if(descripcion.equals("")) // Si la descripción está vacía
            Toast.makeText(NuevoReporte.this, "Falta una descripción del problema", Toast.LENGTH_LONG).show();
        else if(solucion.equals("")) // Si la solución está vacía
            if(idEstadoReporte == 1) { //Se marca como pendiente
                darDeAlta(0);
            }
            else { // Se marca como resuelta
                Toast.makeText(NuevoReporte.this, "No hay solución para el reporte resuelto", Toast.LENGTH_LONG).show();
            }
        else { // Hay descripción y hay solución
            if(idEstadoReporte == 1) { // Se marca como pendiente
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Nuevo reporte")
                        .setMessage("El reporte será guardado como \"Resuelto\" porque tiene una solución.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // En la siguiente línea se pone "idEstadoReporte + 1" porque así el valor será 2 de "Resuelto"
                                darDeAlta(1);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
            else { // Se marca como resuelto
                darDeAlta(0);
            }
        }
    }

    public void darDeAlta(int extra) {
        ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        int idEstadoReporte = sEstado.getSelectedItemPosition() + 1;
        int idTipoMantenimiento = sTipo.getSelectedItemPosition() + 1;
        String asunto = etAsunto.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String solucion = etSolucion.getText().toString();
        int horas = 0;
        if(!etHoras.getText().toString().equals(""))
            horas = Integer.parseInt(etHoras.getText().toString());
        int idUsuario = 0;
        if(Constante.usuarioActual.getTipo() == 5) // Es gerente
            if(sProgramador.getSelectedItemPosition() != 0)
                idUsuario = usuarioDao.buscaPorNombre(this, sProgramador.getSelectedItem().toString()).getIdUsuario();
        if(Constante.usuarioActual.getTipo() == 6) // Si el usuario es programador, automáticamente se le asignará el reporte que genere
            idUsuario = Constante.usuarioActual.getIdUsuario();

        ReporteMantenimiento reporteMantenimiento = new ReporteMantenimiento(0, 0, idEstadoReporte + extra, idTipoMantenimiento, idUsuario, new Timestamp(new java.util.Date().getTime()), asunto, descripcion, solucion, horas);
        reporteMantenimientoDao.alta(this, reporteMantenimiento);
        Toast.makeText(NuevoReporte.this, "Reporte guardado", Toast.LENGTH_LONG).show();
        Intent i = new Intent(NuevoReporte.this, ListaReportes.class);
        finish();
        startActivity(i);
    }

    public void cerrar(View view) {
        Intent i = new Intent(NuevoReporte.this, ListaReportes.class);
        finish();
        startActivity(i);
    }
}
