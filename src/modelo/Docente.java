package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Docente extends Persona{
    private Integer anios_exp_docente;   
    private String titulo_tercerNivel;
    private String titulo_cuartoNivel;
    
    public Docente() {
    }

    public Docente(Integer anios_exp_docente, String titulo_tercerNivel, String titulo_cuartoNivel, Integer id, String nombres, String apellidos, String nacionalidad, Date fecha_nac, String cedula, String telefono) {
        super(id, nombres, apellidos, nacionalidad, fecha_nac, cedula, telefono);
        this.anios_exp_docente = anios_exp_docente;
        this.titulo_tercerNivel = titulo_tercerNivel;
        this.titulo_cuartoNivel = titulo_cuartoNivel;
    }

    public Integer getAnios_exp_docente() {
        return anios_exp_docente;
    }

    public void setAnios_exp_docente(Integer anios_exp_docente) {
        this.anios_exp_docente = anios_exp_docente;
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
                else if (field.equalsIgnoreCase("fecha_nac"))
                    return getFecha_nac().compareTo(e.getFecha_nac()) > 0;
                else if (field.equalsIgnoreCase("nacionalidad"))
                    return getNacionalidad().compareToIgnoreCase(e.getNacionalidad()) > 0;
                else if (field.equalsIgnoreCase("anios_exp_docente"))
                    return getAnios_exp_docente().intValue() > e.getAnios_exp_docente();
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
                else if (field.equalsIgnoreCase("fecha_nac"))
                    return getFecha_nac().compareTo(e.getFecha_nac()) < 0;
                else if (field.equalsIgnoreCase("nacionalidad"))
                    return getNacionalidad().compareToIgnoreCase(e.getNacionalidad()) < 0;
                else if (field.equalsIgnoreCase("anios_exp_docente"))
                    return getAnios_exp_docente().intValue() < e.getAnios_exp_docente();
                else if (field.equalsIgnoreCase("titulo_tercerNivel"))
                    return getTitulo_tercerNivel().compareToIgnoreCase(e.getTitulo_tercerNivel()) < 0;
            default:
                return false;
        }
    }

    

}
