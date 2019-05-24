package omniknow.soportefinal.modelo;

public class Evento {
    private int idEvento;
    private int idUsuario;
    private String problema;
    private String solucion;
    private String estado;
    private int idInge;
    private String fecha;

    public Evento(int idEvento, int idUsuario, String problema, String solucion, String estado, int idInge, String fecha) {
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.problema = problema;
        this.solucion = solucion;
        this.estado = estado;
        this.idInge = idInge;
        this.fecha = fecha;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdInge() {
        return idInge;
    }

    public void setIdInge(int idInge) {
        this.idInge = idInge;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", idUsuario='" + idUsuario + '\'' +
                ", problema='" + problema + '\'' +
                ", solucion='" + solucion + '\'' +
                ", estado='" + estado + '\'' +
                ", idInge=" + idInge +
                ", fecha=" + fecha +
                '}';
    }
}
