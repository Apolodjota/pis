package modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Estudiante extends Persona{
    private String titulo_bachiller;
    private String trabaja; //F o T

    public Estudiante() {
    }

    public Estudiante(String titulo_bachiller, String trabaja, Integer id, Integer id_rol, String nombres, String apellidos, Date fechaNacimiento, String cedula, String telefonoCasa, String telefonoCelular, String genero, String direccionResidencia) {
        super(id, id_rol, nombres, apellidos, fechaNacimiento, cedula, telefonoCasa, telefonoCelular, genero, direccionResidencia);
        this.titulo_bachiller = titulo_bachiller;
        this.trabaja = trabaja;
    }
    
    public String getTitulo_bachiller() {
        return titulo_bachiller;
    }

    public void setTitulo_bachiller(String titulo_bachiller) {
        this.titulo_bachiller = titulo_bachiller;
    }
    
    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
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
                else if (field.equalsIgnoreCase("fechaNacimiento"))
                    return getFechaNacimiento().compareTo(e.getFechaNacimiento()) > 0;
                else if (field.equalsIgnoreCase("direccionResidencia"))
                    return getDireccionResidencia().compareToIgnoreCase(e.getDireccionResidencia()) > 0;
                else if (field.equalsIgnoreCase("tituloBachiller"))
                    return getTitulo_bachiller().compareToIgnoreCase(e.getTitulo_bachiller()) > 0;
                else if (field.equalsIgnoreCase("genero"))
                    return getGenero().compareToIgnoreCase(e.getGenero()) > 0;
                else if (field.equalsIgnoreCase("trabaja"))
                    return getTrabaja().compareToIgnoreCase(e.getTrabaja()) > 0;
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < e.getId().intValue();
                else if (field.equalsIgnoreCase("nombres"))
                    return getNombres().compareToIgnoreCase(e.getNombres()) < 0;
                else if (field.equalsIgnoreCase("apellidos"))
                    return getApellidos().compareToIgnoreCase(e.getApellidos()) < 0;
                else if (field.equalsIgnoreCase("cedula"))
                    return getCedula().compareToIgnoreCase(e.getCedula()) < 0;
                else if (field.equalsIgnoreCase("fechaNacimiento"))
                    return getFechaNacimiento().compareTo(e.getFechaNacimiento()) < 0;
                else if (field.equalsIgnoreCase("direccionResidencia"))
                    return getDireccionResidencia().compareToIgnoreCase(e.getDireccionResidencia()) < 0;
                else if (field.equalsIgnoreCase("tituloBachiller"))
                    return getTitulo_bachiller().compareToIgnoreCase(e.getTitulo_bachiller()) < 0;
                else if (field.equalsIgnoreCase("genero"))
                    return getGenero().compareToIgnoreCase(e.getGenero()) < 0;
                else if (field.equalsIgnoreCase("trabaja"))
                    return getTrabaja().compareToIgnoreCase(e.getTrabaja()) < 0;
            default:
                return false;
        }
    }

}
