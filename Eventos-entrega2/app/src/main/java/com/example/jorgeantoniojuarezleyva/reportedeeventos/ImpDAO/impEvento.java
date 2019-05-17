package com.example.jorgeantoniojuarezleyva.reportedeeventos.ImpDAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Evento;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo.Usuario;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.SQLite;
import com.example.jorgeantoniojuarezleyva.reportedeeventos.altaOperador;

public class impEvento {
    Context contxt = null;

    public impEvento(Context contxt)
    {
        this.contxt = contxt;
    }

    public String altaReporte(Evento event, Usuario user1)
    {
        String resultado = "";

        SQLite bhEventos = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase BDAltas = bhEventos.getWritableDatabase();

        SQLiteDatabase BDConsultas = bhEventos.getReadableDatabase();
        Cursor res = BDConsultas.rawQuery("select ifnull(max(idEvento),0)+1 as id from EVENTOS;",null);
        if (res.moveToNext())
        {
            event.setIdEvento(res.getInt(0));
        }

        ContentValues conE = new ContentValues();
        conE.put("idEvento",event.getIdEvento());
        conE.put("idUsuario",user1.getIdUsuario());
        conE.put("problema",event.getProblema());
        conE.put("solucion",event.getSolucion());
        conE.put("idInge",event.getIdInge());
        conE.put("estado",event.getEstado());

        long insertado = BDAltas.insert("EVENTOS",null,conE);

        if (insertado > 0) {
            resultado = "Registrado correctamente";
        }else{
            resultado = "No se pudo completar el registro";
        }
        return resultado;
    }

    public String borrar(int idEvento)
    {
        String res = "";
        SQLite bh = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase BDBorrar = bh.getReadableDatabase();
        long resultado = BDBorrar.delete("EVENTOS","idEvento = " + idEvento,null);
        if (resultado > 0)
        {
            res = "Reporte borrado exitosamente";
        }else{
            res = "Upsi ocurrio otro prooblemota sorry";
        }
        return res;
    }

    public Evento traeEvento(int idEvento) {

        Evento event = null;
        SQLite bhEventos = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase BDConsultas = bhEventos.getReadableDatabase();
        Cursor res = BDConsultas.rawQuery("select * from EVENTOS where idEvento = '"+idEvento+"' ;",null);
        if (res.moveToNext()) {

            int idUsuario = res.getInt(1);

            String problema = res.getString(2);
            String solucion = res.getString(3);
            String estado = res.getString(4);
            int idInge = res.getInt(5);
            String fecha = res.getString(6);

            event = new Evento(idEvento,idUsuario,problema,solucion,estado,idInge,fecha);

        }
        return event;
    }

    public String asignar(int idInge,int idEvento) {

        String res = "";
        SQLite bh = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase bd = bh.getWritableDatabase();

        ContentValues con = new ContentValues();
        con.put("idInge",idInge);

        long resultado = bd.update("EVENTOS",con,"idEvento = '"+idEvento+"';",null);
        if (resultado >0)
        {
            res = "Asignado correctamente";
        }else{
            res = "ocurrio un problemotote";
        }
        return res;
    }

    public String responder(String solucion, int idEvento) {
        String res = "";
        SQLite bh = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase bd = bh.getWritableDatabase();

        ContentValues con = new ContentValues();
        con.put("solucion",solucion);
        con.put("estado","CERRADO");

        long resultado = bd.update("EVENTOS",con,"idEvento = '"+idEvento+"';",null);
        if (resultado >0)
        {
            res = "Reporte cerrado correctamente";
        }else{
            res = "ocurrio un problemotote";
        }
        return res;

    }

    public String enviarMantenimiento(int idEvento) {
        String res = "";
        SQLite bh = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase bd = bh.getWritableDatabase();

        ContentValues con = new ContentValues();
        con.put("estado","MANTENIMIENTO");

        long resultado = bd.update("EVENTOS",con,"idEvento = '"+idEvento+"';",null);
        if (resultado >0)
        {
            res = "Enviado a mantenimiento";
        }else{
            res = "ocurrio un problemotote";
        }
        return res;
    }

    public String cambiarReporte(Evento event, Usuario user1) {
        String resultado = "";

        SQLite bhEventos = new SQLite(contxt,"EVENTOS",null,1);
        SQLiteDatabase BDAltas = bhEventos.getWritableDatabase();


        ContentValues conE = new ContentValues();
        conE.put("idUsuario",user1.getIdUsuario());
        conE.put("problema",event.getProblema());
        conE.put("solucion",event.getSolucion());
        conE.put("idInge",event.getIdInge());
        conE.put("estado",event.getEstado());

        long insertado = BDAltas.update("EVENTOS",conE,"idEvento = '"+event.getIdEvento()+"';",null);

        if (insertado > 0) {
            resultado = "Reporte actualizado";
        }else{
            resultado = "No se pudo completar el registro";
        }
        return resultado;
    }
}
