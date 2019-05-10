package omniknow.soporte.reportemantenimiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import omniknow.soporte.reportemantenimiento.dao.ReporteMantenimientoDao;
import omniknow.soporte.reportemantenimiento.extras.Constante;
import omniknow.soporte.reportemantenimiento.extras.ReporteClickAdapter;
import omniknow.soporte.reportemantenimiento.modelo.ReporteMantenimiento;

import java.util.ArrayList;

public class ListaReportes extends AppCompatActivity {
    RecyclerView rvReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reportes);

        rvReportes = findViewById(R.id.rvReportes);

        // Setting up the RecyclerView
        assert rvReportes != null;
        rvReportes.setLayoutManager(new LinearLayoutManager(this));

        // Getting your ArrayList - this will be up to you
        ReporteMantenimientoDao reporteMantenimientoDao = new ReporteMantenimientoDao();
        ArrayList<ReporteMantenimiento> reportesMantenimientos = new ArrayList<>();
        // Guardar en el arraylist todos los reportes de mantenimiento
        for(ReporteMantenimiento rm : reporteMantenimientoDao.ver(this))
            reportesMantenimientos.add(rm);
        Constante.reportes = reportesMantenimientos;

        // RecyclerView with a click listener
        ReporteClickAdapter clickAdapter = new ReporteClickAdapter(reportesMantenimientos);
        clickAdapter.setOnEntryClickListener(new ReporteClickAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                // stuff that will happen when a list item is clicked
                Constante.reporteActual = Constante.reportes.get(position);
                Intent i = new Intent(ListaReportes.this, VerReporte.class);
                startActivity(i);
            }
        });
        rvReportes.setAdapter(clickAdapter);
    }

    public void agregar(View v) {
        Intent i = new Intent(ListaReportes.this, NuevoReporte.class);
        startActivity(i);
    }
}
