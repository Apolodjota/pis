package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Revision {
    private Integer id;
    private Integer id_asignacion;
    private Integer id_matricula;  
    private String estado;
    private String comentario;
    private Double calificacion;

    public Revision() {
    }

    public Revision(Integer id, Integer id_asignacion, Integer id_matricula, String estado, String comentario, Double calificacion) {
        this.id = id;
        this.id_asignacion = id_asignacion;
        this.id_matricula = id_matricula;
        this.estado = estado;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(Integer id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public Integer getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(Integer id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    public Boolean comparar(Revision r, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > r.getId().intValue();
                else if (field.equalsIgnoreCase("id_asignacion"))
                    return getId_asignacion() > r.getId_asignacion();
                else if (field.equalsIgnoreCase("id_matricula"))
                    return getId_matricula()> r.getId_matricula();
                else if (field.equalsIgnoreCase("calificacion"))
                    return getCalificacion()> r.getCalificacion();
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < r.getId().intValue();
                else if (field.equalsIgnoreCase("id_asignacion"))
                    return getId_asignacion() < r.getId_asignacion();
                else if (field.equalsIgnoreCase("id_matricula"))
                    return getId_matricula()< r.getId_matricula();
                else if (field.equalsIgnoreCase("calificacion"))
                    return getCalificacion()< r.getCalificacion();
                
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Revision" + id +"  de la asignacion "+id_asignacion;
    }
    
    
}
