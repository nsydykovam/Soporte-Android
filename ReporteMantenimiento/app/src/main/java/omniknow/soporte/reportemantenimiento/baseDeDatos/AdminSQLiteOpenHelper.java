package omniknow.soporte.reportemantenimiento.baseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ESTADOS_REPORTES(" +
                "    ID_ESTADO_REPORTE INTEGER," +
                "    NOMBRE VARCHAR," +
                "    PRIMARY KEY(ID_ESTADO_REPORTE)" +
                ")");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(1, 'Pendiente')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(2, 'Resuelto')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(3, 'Cerrado')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(4, 'Pagado')");
        sqLiteDatabase.execSQL("CREATE TABLE TIPOS_MANTENIMIENTOS(" +
                "    ID_TIPO_MANTENIMIENTO INTEGER," +
                "    NOMBRE VARCHAR," +
                "    PRIMARY KEY(ID_TIPO_MANTENIMIENTO)" +
                ")");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(1, 'Preventivo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(2, 'Correctivo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(3, 'Adaptativo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(4, 'Perfectivo')");
        sqLiteDatabase.execSQL("CREATE TABLE USUARIOS(" +
                "    ID_USUARIO INTEGER," +
                "    USUARIO VARCHAR," +
                "    CONTRASENA VARCHAR," +
                "    NOMBRE VARCHAR," +
                "    TIPO INTEGER," +
                "    INICIADO TINYINT," +
                "    PRIMARY KEY(ID_USUARIO)" +
                ")");
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE REPORTES_MANTENIMIENTOS(" +
                "    ID_REPORTE_MANTENIMIENTO INTEGER," +
                "    ID_REPORTE_EVENTO INTEGER," +
                "    ID_ESTADO_REPORTE INTEGER," +
                "    ID_TIPO_MANTENIMIENTO INTEGER," +
                "    ID_USUARIO INTEGER," +
                "    FECHA DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP))," +
                "    ASUNTO TEXT," +
                "    DESCRIPCION TEXT," +
                "    SOLUCION TEXT," +
                "    PRIMARY KEY(ID_REPORTE_MANTENIMIENTO)," +
//                "    FOREIGN KEY(ID_REPORTE_EVENTO) REFERENCES REPORTES_EVENTOS(ID_REPORTE_EVENTO)," +
                "    FOREIGN KEY(ID_ESTADO_REPORTE) REFERENCES ESTADOS_REPORTES(ID_ESTADO_REPORTE)," +
                "    FOREIGN KEY(ID_TIPO_MANTENIMIENTO) REFERENCES TIPOS_MANTENIMIENTOS(ID_TIPO_MANTENIMIENTO)," +
                "    FOREIGN KEY(ID_USUARIO) REFERENCES USUARIOS(ID_USUARIO)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
