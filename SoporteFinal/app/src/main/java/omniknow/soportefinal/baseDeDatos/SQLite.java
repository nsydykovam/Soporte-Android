package omniknow.soportefinal.baseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    private String TABLA_USUARIOS = "CREATE TABLE USUARIOS(idUsuario int primary key,usuario text,contraseña text,nombre text,tipo int);";
    private String TABLA_EVENTOS = "CREATE TABLE EVENTOS(idEvento int primary key,idUsuario int,problema text,solucion text,estado text,idInge int,fecha timestamp default current_timestamp);";
    private String DATOS_FALSOS = "insert into usuarios(idUsuario,usuario, contraseña, nombre, tipo) values " +
            "('1','operador1','operator','Angel Marin','1')," +
            "('2','operador2','operator','Angel Hernandez','1')," +
            "('3','inge1','engineer','Nuria Sydykova','2')," +
            "('4','inge2','engineer','Dustin Sul','2')," +
            "('5','inge3','engineer','Daniel Sivan','2')," +
            "('6','gerente','manager','Tony Rusher','3')," +
            "('7','Dylan','Dylan','Dylan Geick','0')," +
            "('8','Jackson','Jackson','Jackson Krecioch','0')," +
            "('9','Javier','Javier','Javier Santaolalla','0')," +
            "('10','Marti','Marti','Marti Fornier','0')," +
            "('11','Jose','Jose','Jose Luis Crespo','0')," +
            "('12','Aldo','Aldo','Aldo Batra','0')," +
            "('13','Fabian','Fabian','Fabian Arnold','0');";

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
        db.execSQL(TABLA_EVENTOS);
        db.execSQL(DATOS_FALSOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}