package com.example.jorgeantoniojuarezleyva.reportedeeventos.Modelo;

import java.sql.Timestamp;

public class ReporteMantenimiento {
    private int idReporteMantenimiento;
    private int idReporteEvento;
    private int idEstadoReporte;
    private int idTipoMantenimiento;
    private int idUsuario;
    private Timestamp fecha;
    private String asunto;
    private String descripcion;
    private String solucion;
    private int horas;
    private boolean evento;

    public ReporteMantenimiento(int idReporteMantenimiento, int idReporteEvento, int idEstadoReporte, int idTipoMantenimiento, int idUsuario, Timestamp fecha, String asunto, String descripcion, String solucion, int horas, boolean evento) {
        this.idReporteMantenimiento = idReporteMantenimiento;
        this.idReporteEvento = idReporteEvento;
        this.idEstadoReporte = idEstadoReporte;
        this.idTipoMantenimiento = idTipoMantenimiento;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.horas = horas;
        this.evento = evento;
    }

    public int getIdReporteMantenimiento() {
        return idReporteMantenimiento;
    }

    public void setIdReporteMantenimiento(int idReporteMantenimiento) {
        this.idReporteMantenimiento = idReporteMantenimiento;
    }

    public int getIdReporteEvento() {
        return idReporteEvento;
    }

    public void setIdReporteEvento(int idReporteEvento) {
        this.idReporteEvento = idReporteEvento;
    }

    public int getIdEstadoReporte() {
        return idEstadoReporte;
    }

    public void setIdEstadoReporte(int idEstadoReporte) {
        this.idEstadoReporte = idEstadoReporte;
    }

    public int getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(int idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public boolean isEvento() {
        return evento;
    }

    public void setEvento(boolean evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "ReporteMantenimiento{" +
                "idReporteMantenimiento=" + idReporteMantenimiento +
                ", idReporteEvento=" + idReporteEvento +
                ", idEstadoReporte=" + idEstadoReporte +
                ", idTipoMantenimiento=" + idTipoMantenimiento +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", solucion='" + solucion + '\'' +
                ", horas=" + horas +
                ", evento=" + evento +
                '}';
    }
}
