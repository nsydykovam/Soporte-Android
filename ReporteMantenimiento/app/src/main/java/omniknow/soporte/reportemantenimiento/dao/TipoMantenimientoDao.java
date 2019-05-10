package omniknow.soporte.reportemantenimiento.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import omniknow.soporte.reportemantenimiento.baseDeDatos.AdminSQLiteOpenHelper;
import omniknow.soporte.reportemantenimiento.modelo.TipoMantenimiento;

import java.util.ArrayList;
import java.util.List;

public class TipoMantenimientoDao {
    final private String BASE = "SOPORTE_MANTENIMIENTO";
    final private String VER = "SELECT * FROM TIPOS_MANTENIMIENTOS";
    final private String CONSULTA = "SELECT * FROM TIPOS_MANTENIMIENTOS WHERE ID_TIPO_MANTENIMIENTO = ?";

    public void alta(Context context, TipoMantenimiento tipoMantenimiento) {

    }
    public void cambio(Context context, TipoMantenimiento tipoMantenimiento) {

    }
    public void baja(Context context, TipoMantenimiento tipoMantenimiento) {

    }
    public TipoMantenimiento consulta(Context context, int idTipoMantenimiento) {
        TipoMantenimiento consulta = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(CONSULTA, new String[]{idTipoMantenimiento+""});

        while(fila.moveToNext())
            consulta = new TipoMantenimiento(fila.getInt(0), fila.getString(1));

        fila.close();
        baseDeDatos.close();

        return consulta;
    }
    public List<TipoMantenimiento> ver(Context context) {
        List<TipoMantenimiento> ver = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(VER, null);

        while(fila.moveToNext())
            ver.add(new TipoMantenimiento(fila.getInt(0), fila.getString(1)));

        fila.close();
        baseDeDatos.close();
        return ver;
    }
}
