package omniknow.soportefinal.modelo;

public class TipoMantenimiento {
    private int idTipoMantenimiento;
    private String nombre;

    public TipoMantenimiento(int idTipoMantenimiento, String nombre) {
        this.idTipoMantenimiento = idTipoMantenimiento;
        this.nombre = nombre;
    }

    public int getIdTipoMantenimiento() {
        return idTipoMantenimiento;
    }

    public void setIdTipoMantenimiento(int idTipoMantenimiento) {
        this.idTipoMantenimiento = idTipoMantenimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoMantenimiento{" +
                "idTipoMantenimiento=" + idTipoMantenimiento +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
