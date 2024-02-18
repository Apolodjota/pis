package modelo;
import java.io.File;
import java.util.Date;

/**
 *
 * @author Apolo
 */
public class Asignacion {
    private Integer id;
    private Integer id_cursa;
    private Integer id_tarea;
    private File archivo;
    private Date fechaEntrega;
    private Double calificacion;
    private String comentario;
    private String estado;
    

    public Asignacion() {
    }

    public Asignacion(Integer id, Integer id_cursa, Integer id_tarea, File archivo, Date fechaEntrega, Double calificacion, String comentario, String estado) {
        this.id = id;
        this.id_cursa = id_cursa;
        this.id_tarea = id_tarea;
        this.archivo = archivo;
        this.fechaEntrega = fechaEntrega;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cursa() {
        return id_cursa;
    }

    public void setId_cursa(Integer id_cursa) {
        this.id_cursa = id_cursa;
    }

    public Integer getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   /*
    public Boolean comparar(Asignasion a, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > a.getId().intValue();
                else if (field.equalsIgnoreCase("id_cursa"))
                    return getId_cursa()> a.getId_cursa();
                else if (field.equalsIgnoreCase("unidad"))
                    return getUnidad() > a.getUnidad();
                else if (field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(a.getTitulo()) > 0 ;
                else if (field.equalsIgnoreCase("fechaAsignacion"))
                    return getFechaAsignacion().after(a.getFechaAsignacion());
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(a.getFechaEntrega());
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < a.getId().intValue();
                else if (field.equalsIgnoreCase("id_cursa"))
                    return getId_cursa()< a.getId_cursa();
                else if (field.equalsIgnoreCase("unidad"))
                    return getUnidad() < a.getUnidad();
                else if (field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(a.getTitulo()) < 0 ;
                else if (field.equalsIgnoreCase("fechaAsignacion"))
                    return getFechaAsignacion().before(a.getFechaAsignacion());
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().before(a.getFechaEntrega());
                
            default:
                return false;
        }
    }*/
    
    
    
}
