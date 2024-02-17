package modelo;
import java.io.File;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Tarea {
    private Integer id;
    private Integer unidad;
    private String titulo;
    private File archivo;
    private Date fechaAsignacion;
    private Date fechaEntrega;
    private String descripcion;

    public Tarea() {
    }

    public Tarea(Integer id, Integer unidad, String titulo, File archivo, Date fechaAsignacion, Date fechaEntrega, String descripcion) {
        this.id = id;
        this.unidad = unidad;
        this.titulo = titulo;
        this.archivo = archivo;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaEntrega = fechaEntrega;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public Boolean comparar(Tarea t, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > t.getId().intValue();
                else if(field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(t.getTitulo()) > 0;
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(t.getFechaEntrega());
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < t.getId().intValue();
                else if(field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(t.getTitulo()) < 0;
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(t.getFechaEntrega());
                
            default:
                return false;
        }
    }
}
