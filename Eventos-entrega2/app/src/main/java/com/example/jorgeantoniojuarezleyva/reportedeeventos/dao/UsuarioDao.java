package com.example.jorgeantoniojuarezleyva.reportedeeventos.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.baseDeDatos.AdminSQLiteOpenHelper;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Usuario;

public class UsuarioDao {
    final private String BASE = "SOPORTE_MANTENIMIENTO";
    final private String BUSCA_POR_NOMBRE = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
    final private String ACCESO = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND CONTRASENA = ?";
    final private String CONSULTA = "SELECT * FROM USUARIOS WHERE ID_USUARIO = ?";
    final private String VER = "SELECT * FROM USUARIOS";

    public Usuario buscaPorNombre(Context context, String nombre) {
        Usuario buscaPorNombre = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(BUSCA_POR_NOMBRE, new String[]{nombre});

        while(fila.moveToNext())
            buscaPorNombre = new Usuario(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), (fila.getInt(5)==1));

        fila.close();
        baseDeDatos.close();

        return buscaPorNombre;
    }

    public Usuario acceso(Context context, String usuario, String contrasena) {
        Usuario acceso = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(ACCESO, new String[]{usuario, contrasena});

        while(fila.moveToNext())
            acceso = new Usuario(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), (fila.getInt(5)==1));

        fila.close();
        baseDeDatos.close();

        return acceso;
    }

    public Usuario consulta(Context context, int idUsuario) {
        Usuario consulta = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(CONSULTA, new String[]{idUsuario+""});

        while(fila.moveToNext())
            consulta = new Usuario(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), (fila.getInt(5)==1));

        fila.close();
        baseDeDatos.close();

        return consulta;
    }

    public List<Usuario> ver(Context context) {
        List<Usuario> ver = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, BASE, null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery(VER, null);

        while(fila.moveToNext())
            ver.add(new Usuario(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), (fila.getInt(5)==1)));

        fila.close();
        baseDeDatos.close();
        return ver;
    }
}
