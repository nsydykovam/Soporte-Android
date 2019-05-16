package omniknow.soporte.reportemantenimiento.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import omniknow.soporte.reportemantenimiento.baseDeDatos.AdminSQLiteOpenHelper;
import omniknow.soporte.reportemantenimiento.modelo.ReporteMantenimiento;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReporteMantenimientoDao {
    final private String BASE = "SOPORTE_MANTENIMIENTO";
    final private String NUEVO_ID = "SELECT IFNULL(MAX(ID_REPORTE_MANTENIMIENTO),0)+1 FROM REPORTES_MANTENIMIENTOS";
    final private String VER = "SELECT * FROM REPORTES_MANTENIMIENTOS ORDER BY ID_REPORTE_MANTENIMIENTO DESC";
    final private String ALTA = "INSERT INTO REPORTES_MANTENIMIENTOS VALUES(?,?,?,?,?,?,?,?,?,?)";
    final private String BAJA = "DELETE FROM REPORTES_MANTENIMIENTOS WHERE ID_REPORTE_MANTENIMIENTO = ?";
    final private String CAMBIO = "UPDATE REPORTES_MANTENIMIENTOS SET ID_ESTADO_REPORTE= ?, ASUNTO = ?, DESCRIPCION = ?, SOLUCION = ?, HORAS = ? WHERE ID_REPORTE_MANTENIMIENTO = ?";

    public void alta(Context context, ReporteMantenimiento reporteMantenimiento) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase(); // la ponemos en modo de lectura y escritura

        // Conseguimos el id del reporte a generar
        Cursor fila = baseDeDatos.rawQuery(NUEVO_ID, null);
        fila.moveToFirst();
        int id = fila.getInt(0);
        reporteMantenimiento.setIdReporteMantenimiento(id); // le ponemos el id al reporte por registrar

        SQLiteStatement s = baseDeDatos.compileStatement(ALTA);
        s.clearBindings();
        s.bindLong(1, reporteMantenimiento.getIdReporteMantenimiento());
        s.bindLong(2, reporteMantenimiento.getIdReporteEvento());
        s.bindLong(3, reporteMantenimiento.getIdEstadoReporte());
        s.bindLong(4, reporteMantenimiento.getIdTipoMantenimiento());
        s.bindLong(5, reporteMantenimiento.getIdUsuario());
        s.bindString(6, reporteMantenimiento.getFecha().toString());
        s.bindString(7, reporteMantenimiento.getAsunto());
        s.bindString(8, reporteMantenimiento.getDescripcion());
        s.bindString(9, reporteMantenimiento.getSolucion());
        s.bindLong(10, reporteMantenimiento.getHoras());
        s.executeInsert();

        s.close();
        fila.close();
        baseDeDatos.close();

    }
    public void cambio(Context context, ReporteMantenimiento reporteMantenimiento) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase(); // la ponemos en modo de lectura y escritura

        SQLiteStatement s = baseDeDatos.compileStatement(CAMBIO);
        s.clearBindings();
        s.bindLong(1, reporteMantenimiento.getIdEstadoReporte());
        s.bindString(2, reporteMantenimiento.getAsunto());
        s.bindString(3, reporteMantenimiento.getDescripcion());
        s.bindString(4, reporteMantenimiento.getSolucion());
        s.bindLong(5, reporteMantenimiento.getIdReporteMantenimiento());
        s.bindLong(6, reporteMantenimiento.getHoras());
        s.executeUpdateDelete();

        s.close();
        baseDeDatos.close();
    }
    public void baja(Context context, ReporteMantenimiento reporteMantenimiento) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase(); // la ponemos en modo de lectura y escritura

        SQLiteStatement s = baseDeDatos.compileStatement(BAJA);
        s.clearBindings();
        s.bindLong(1, reporteMantenimiento.getIdReporteMantenimiento());
        s.executeUpdateDelete();

        s.close();
        baseDeDatos.close();
    }
    public List<ReporteMantenimiento> ver(Context context) {
        List<ReporteMantenimiento> ver = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(VER, null);

        while(fila.moveToNext())
            ver.add(new ReporteMantenimiento(fila.getInt(0), fila.getInt(1), fila.getInt(2), fila.getInt(3), fila.getInt(4), Timestamp.valueOf(fila.getString(5)), fila.getString(6), fila.getString(7), fila.getString(8), fila.getInt(9)));

        fila.close();
        baseDeDatos.close();
        return ver;
    }
}
