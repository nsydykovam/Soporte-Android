package omniknow.soportefinal.modelo;

public class EstadoReporte {
    private int idEstadoReporte;
    private String nombre;

    public EstadoReporte(int idEstadoReporte, String nombre) {
        this.idEstadoReporte = idEstadoReporte;
        this.nombre = nombre;
    }

    public int getIdEstadoReporte() {
        return idEstadoReporte;
    }

    public void setIdEstadoReporte(int idEstadoReporte) {
        this.idEstadoReporte = idEstadoReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadoReporte{" +
                "idEstadoReporte=" + idEstadoReporte +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
