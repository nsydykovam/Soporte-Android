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
        sqLiteDatabase.execSQL(
                "CREATE TABLE ESTADOS_REPORTES(" +
                        "    ID_ESTADO_REPORTE INTEGER," +
                        "    NOMBRE TEXT," +
                        "    PRIMARY KEY(ID_ESTADO_REPORTE)" +
                        ")");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(1, 'Pendiente')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(2, 'Resuelto')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(3, 'Cerrado')");
        sqLiteDatabase.execSQL("INSERT INTO ESTADOS_REPORTES VALUES(4, 'Pagado')");
        sqLiteDatabase.execSQL(
                "CREATE TABLE TIPOS_MANTENIMIENTOS(" +
                        "    ID_TIPO_MANTENIMIENTO INTEGER," +
                        "    NOMBRE TEXT," +
                        "    PRIMARY KEY(ID_TIPO_MANTENIMIENTO)" +
                        ")");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(1, 'Preventivo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(2, 'Correctivo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(3, 'Adaptativo')");
        sqLiteDatabase.execSQL("INSERT INTO TIPOS_MANTENIMIENTOS VALUES(4, 'Perfectivo')");
        sqLiteDatabase.execSQL(
                "CREATE TABLE USUARIOS(" +
                        "    ID_USUARIO INTEGER," +
                        "    USUARIO TEXT," +
                        "    CONTRASENA TEXT," +
                        "    NOMBRE TEXT," +
                        "    TIPO INTEGER," +
                        "    INICIADO TINYINT," +
                        "    PRIMARY KEY(ID_USUARIO)" +
                        ")");
        // Tipo 5 es Gerente de Mantenimiento
        sqLiteDatabase.execSQL("INSERT INTO USUARIOS VALUES(1, 'gMantenimiento', 'gMantenimiento', 'Jorge Juárez', 5, 0)");
        // Tipo 6 es programador
        sqLiteDatabase.execSQL("INSERT INTO USUARIOS VALUES(2, 'programador1', 'programador1', 'Nuria Sydykova', 6, 0)");
        sqLiteDatabase.execSQL("INSERT INTO USUARIOS VALUES(3, 'programador2', 'programador2', 'Olena Rashkovan', 6, 0)");
        sqLiteDatabase.execSQL("INSERT INTO USUARIOS VALUES(4, 'programador3', 'programador3', 'Miguel Hernández', 6, 0)");
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE REPORTES_MANTENIMIENTOS(" +
                "    ID_REPORTE_MANTENIMIENTO INTEGER," +
                "    ID_REPORTE_EVENTO INTEGER," +
                "    ID_ESTADO_REPORTE INTEGER," +
                "    ID_TIPO_MANTENIMIENTO INTEGER," +
                "    ID_USUARIO INTEGER," + // Programador responsable
                "    FECHA DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP))," +
                "    ASUNTO TEXT," +
                "    DESCRIPCION TEXT," +
                "    SOLUCION TEXT," +
                "    HORAS INTEGER," +
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
