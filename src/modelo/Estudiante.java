package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Estudiante extends Persona{
    private String titulo_bachiller;
    private String ciudad_Procedencia;

    public Estudiante() {
    }

    public Estudiante(String titulo_bachiller, String ciudad_Procedencia, Integer id, String nombres, String apellidos, String nacionalidad, Date fecha_nac, String cedula, String telefono) {
        super(id, nombres, apellidos, nacionalidad, fecha_nac, cedula, telefono);
        this.titulo_bachiller = titulo_bachiller;
        this.ciudad_Procedencia = ciudad_Procedencia;
    }

    public String getTitulo_bachiller() {
        return titulo_bachiller;
    }

    public void setTitulo_bachiller(String titulo_bachiller) {
        this.titulo_bachiller = titulo_bachiller;
    }

    public String getCiudad_Procedencia() {
        return ciudad_Procedencia;
    }

    public void setCiudad_Procedencia(String ciudad_Procedencia) {
        this.ciudad_Procedencia = ciudad_Procedencia;
    }
    
    public Boolean comparar(Estudiante e, Integer type, String field, Integer aux){
        if (type == 0)
            type = 1;
        else 
            type = 0;
        return comparar(e, type, field);
    }
    
    public Boolean comparar(Estudiante e, Integer type, String field){
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
                else if (field.equalsIgnoreCase("tituloBachiller"))
                    return getTitulo_bachiller().compareToIgnoreCase(e.getTitulo_bachiller()) > 0;
                else if (field.equalsIgnoreCase("ciudadProcedencia"))
                    return getCiudad_Procedencia().compareToIgnoreCase(e.getCiudad_Procedencia()) > 0;
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < e.getId().intValue();
                else if (field.equalsIgnoreCase("nombre"))
                    return getNombres().compareToIgnoreCase(e.getNombres()) < 0;
                else if (field.equalsIgnoreCase("apellidos"))
                    return getApellidos().compareToIgnoreCase(e.getApellidos()) < 0;
                else if (field.equalsIgnoreCase("cedula"))
                    return getCedula().compareToIgnoreCase(e.getCedula()) < 0;
                else if (field.equalsIgnoreCase("fecha_nac"))
                    return getFecha_nac().compareTo(e.getFecha_nac()) < 0;
                else if (field.equalsIgnoreCase("nacionalidad"))
                    return getNacionalidad().compareToIgnoreCase(e.getNacionalidad()) < 0;
                else if (field.equalsIgnoreCase("tituloBachiller"))
                    return getTitulo_bachiller().compareToIgnoreCase(e.getTitulo_bachiller()) > 0;
                else if (field.equalsIgnoreCase("ciudadProcedencia"))
                    return getCiudad_Procedencia().compareToIgnoreCase(e.getCiudad_Procedencia()) > 0;
            default:
                return false;
        }
    }
    
}
