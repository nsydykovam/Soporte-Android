package omniknow.soportefinal.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import omniknow.soportefinal.baseDeDatos.SQLite;
import omniknow.soportefinal.extras.sUsuario;
import omniknow.soportefinal.modelo.Usuario;

public class impUsuario {
    Context contxt = null;

    public impUsuario(Context contxt)
    {
        this.contxt = contxt;
    }

    public int iniciarSesion(String user, String pass)
    {
        int iniciado = 0;
        SQLite bh = new SQLite(contxt,"USUARIOS",null,1);
        SQLiteDatabase bd = bh.getReadableDatabase();
        Cursor res = bd.rawQuery("SELECT * FROM USUARIOS WHERE usuario = '"+user+"' and contraseña = '"+pass+"' ;",null);
        if (res.moveToFirst())
        {
            int id = res.getInt(0);
            String nombre = res.getString(3);
            int tipo = res.getInt(4);
            Usuario user1 = new Usuario(id,user,pass,nombre,tipo,false);
            sUsuario.idUsuario = id;
            sUsuario.usuario = user;
            sUsuario.contraseña = pass;
            sUsuario.nombre = nombre;
            sUsuario.tipo = tipo;
            iniciado = tipo;
        }
        return iniciado;
    }

    public Usuario traeUsuario(String usuario)
    {
        Usuario user1 = null;
        SQLite bh = new SQLite(contxt,"USUARIOS",null,1);
        if (bh != null)
        {
            SQLiteDatabase BD = bh.getReadableDatabase();
            Cursor res = BD.rawQuery("select * from USUARIOS where usuario = '" + usuario + "';",null);
            if (res.moveToFirst())
            {
                int idUsuario = (res.getInt(0));
                String contraseña = (res.getString(2));
                String nombre = (res.getString(3));
                int tipo = (res.getInt(4));
                user1 = new Usuario(idUsuario,usuario,contraseña,nombre,tipo,false);
            }
        }

        return user1;
    }
    public Usuario traeUsuario(int idUsuario)
    {
        Usuario user1 = null;
        SQLite bh = new SQLite(contxt,"USUARIOS",null,1);
        if (bh != null)
        {
            SQLiteDatabase BD = bh.getReadableDatabase();
            Cursor res = BD.rawQuery("select * from USUARIOS where idUsuario = '" + idUsuario + "';",null);
            if (res.moveToFirst())
            {
                String usuario = res.getString(1);
                String contraseña = (res.getString(2));
                String nombre = (res.getString(3));
                int tipo = (res.getInt(4));
                user1 = new Usuario(idUsuario,usuario,contraseña,nombre,tipo,false);
            }
        }

        return user1;
    }


}
