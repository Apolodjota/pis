package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Docente extends Persona{
    private Date fecha_inicio_docencia;   
    private String titulo_tercerNivel;
    private String titulo_cuartoNivel;
    
    public Docente() {
    }

    public Docente(Date fecha_inicio_docencia, String titulo_tercerNivel, String titulo_cuartoNivel, Integer id, Integer id_rol, String nombres, String apellidos, Date fechaNacimiento, String cedula, String telefonoCasa, String telefonoCelular, String genero, String direccionResidencia) {
        super(id, id_rol, nombres, apellidos, fechaNacimiento, cedula, telefonoCasa, telefonoCelular, genero, direccionResidencia);
        this.fecha_inicio_docencia = fecha_inicio_docencia;
        this.titulo_tercerNivel = titulo_tercerNivel;
        this.titulo_cuartoNivel = titulo_cuartoNivel;
    }
    
    public void setFecha_inicio_docencia(Date fecha_inicio_docencia) {
        this.fecha_inicio_docencia = fecha_inicio_docencia;
    }

    public Date getFecha_inicio_docencia() {
        return fecha_inicio_docencia;
    }

    public String getTitulo_tercerNivel() {
        return titulo_tercerNivel;
    }

    public void setTitulo_tercerNivel(String titulo_tercerNivel) {
        this.titulo_tercerNivel = titulo_tercerNivel;
    }
 
    public String getTitulo_cuartoNivel() {
        return titulo_cuartoNivel;
    }

    public void setTitulo_cuartoNivel(String titulo_cuartoNivel) {
        this.titulo_cuartoNivel = titulo_cuartoNivel;
    }

    public Boolean comparar(Docente e, Integer type, String field, Integer aux){
        if (type == 0)
            type = 1;
        else 
            type = 0;
        return comparar(e, type, field);
    }
    
    public Boolean comparar(Docente e, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > e.getId().intValue();
                else if (field.equalsIgnoreCase("nombres"))
                    return getNombres().compareToIgnoreCase(e.getNombres()) > 0;
                else if (field.equalsIgnoreCase("apellidos"))
                    return getApellidos().compareToIgnoreCase(e.getApellidos()) > 0;
                else if (field.equalsIgnoreCase("cedula"))
                    return getCedula().compareToIgnoreCase(e.getCedula()) > 0;
                else if (field.equalsIgnoreCase(" fechaNacimiento"))
                    return getFechaNacimiento().compareTo(e.getFechaNacimiento()) > 0;
                else if (field.equalsIgnoreCase("direccionResidencia"))
                    return getDireccionResidencia().compareToIgnoreCase(e.getDireccionResidencia()) > 0;
                else if (field.equalsIgnoreCase("genero"))
                    return getGenero().compareToIgnoreCase(e.getGenero()) > 0;
                else if (field.equalsIgnoreCase("fecha_inicio_docencia"))
                    return getFecha_inicio_docencia().compareTo(e.getFecha_inicio_docencia()) > 0;
                else if (field.equalsIgnoreCase("titulo_tercerNivel"))
                    return getTitulo_tercerNivel().compareToIgnoreCase(e.getTitulo_tercerNivel()) > 0;
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < e.getId().intValue();
                else if (field.equalsIgnoreCase("nombres"))
                    return getNombres().compareToIgnoreCase(e.getNombres()) < 0;
                else if (field.equalsIgnoreCase("apellidos"))
                    return getApellidos().compareToIgnoreCase(e.getApellidos()) < 0;
                else if (field.equalsIgnoreCase("cedula"))
                    return getCedula().compareToIgnoreCase(e.getCedula()) < 0;
                else if (field.equalsIgnoreCase(" fechaNacimiento"))
                    return getFechaNacimiento().compareTo(e.getFechaNacimiento()) < 0;
                else if (field.equalsIgnoreCase("direccionResidencia"))
                    return getDireccionResidencia().compareToIgnoreCase(e.getDireccionResidencia()) < 0;
                else if (field.equalsIgnoreCase("genero"))
                    return getGenero().compareToIgnoreCase(e.getGenero()) < 0;
                else if (field.equalsIgnoreCase("fecha_inicio_docencia"))
                    return getFecha_inicio_docencia().compareTo(e.getFecha_inicio_docencia()) < 0;
                else if (field.equalsIgnoreCase("titulo_tercerNivel"))
                    return getTitulo_tercerNivel().compareToIgnoreCase(e.getTitulo_tercerNivel()) < 0;
            default:
                return false;
        }
    }

}
