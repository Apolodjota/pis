package modelo;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Tarea {
    private Integer id;
    private Integer id_revision;
    private Integer id_matricula;
    private Byte archivo;
    private Date fechaEntrega;
    private String comentario;

    public Tarea() {
    }

    public Tarea(Integer id, Integer id_revision, Integer id_matricula, Byte archivo, Date fechaEntrega, String comentario) {
        this.id = id;
        this.id_revision = id_revision;
        this.id_matricula = id_matricula;
        this.archivo = archivo;
        this.fechaEntrega = fechaEntrega;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_revision() {
        return id_revision;
    }

    public void setId_revision(Integer id_revision) {
        this.id_revision = id_revision;
    }

    public Integer getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(Integer id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Byte getArchivo() {
        return archivo;
    }

    public void setArchivo(Byte archivo) {
        this.archivo = archivo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public Boolean comparar(Tarea t, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > t.getId().intValue();
                else if (field.equalsIgnoreCase("id_revision"))
                    return getId_revision()> t.getId_revision();
                else if (field.equalsIgnoreCase("id_matricula"))
                    return getId_matricula()> t.getId_matricula();
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(t.getFechaEntrega());
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < t.getId().intValue();
                else if (field.equalsIgnoreCase("id_revision"))
                    return getId_revision() <  t.getId_revision();
                else if (field.equalsIgnoreCase("id_matricula"))
                    return getId_matricula() < t.getId_matricula();
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(t.getFechaEntrega());
                
            default:
                return false;
        }
    }
}
