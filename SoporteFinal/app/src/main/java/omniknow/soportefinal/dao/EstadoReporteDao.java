package omniknow.soportefinal.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import omniknow.soportefinal.baseDeDatos.AdminSQLiteOpenHelper;
import omniknow.soportefinal.modelo.EstadoReporte;

import java.util.ArrayList;
import java.util.List;

public class EstadoReporteDao {
    final private String BASE = "SOPORTE_MANTENIMIENTO";
    final private String VER = "SELECT * FROM ESTADOS_REPORTES";
    final private String CONSULTA = "SELECT * FROM ESTADOS_REPORTES WHERE ID_ESTADO_REPORTE = ?";

    public void alta(Context context, EstadoReporte estadoReporte) {

    }
    public void cambio(Context context, EstadoReporte estadoReporte) {

    }
    public void baja(Context context, EstadoReporte estadoReporte) {

    }
    public EstadoReporte consulta(Context context, int idEstadoReporte) {
        EstadoReporte consulta = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(CONSULTA, new String[]{idEstadoReporte+""});

        while(fila.moveToNext())
            consulta = new EstadoReporte(fila.getInt(0), fila.getString(1));

        fila.close();
        baseDeDatos.close();

        return consulta;
    }

    public List<EstadoReporte> ver(Context context) {
        List<EstadoReporte> ver = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(VER, null);

        while(fila.moveToNext())
            ver.add(new EstadoReporte(fila.getInt(0), fila.getString(1)));

        fila.close();
        baseDeDatos.close();
        return ver;
    }
}
