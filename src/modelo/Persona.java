package modelo;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Persona {
    private Integer id;
    private Integer id_rol;
    private String nombres;
    private String apellidos;
    private Date fecha_nac;
    private String cedula;
    private String telefonoCasa;
    private String telefonoCelular;
    private String genero;
    private String direccionResidencia;
    

    public Persona() {
    }

    public Persona(Integer id, String nombres, String apellidos, Date fecha_nac, String cedula, String telefonoCasa, String telefonoCelular, String genero, String direccionResidencia, Integer id_rol) {
        this.id = id;
        this.id_rol = id_rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_nac = fecha_nac;
        this.cedula = cedula;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCelular = telefonoCelular;
        this.genero = genero;
        this.direccionResidencia = direccionResidencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }
    
    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }
  
    @Override
    public String toString() {
        return apellidos + " "+ nombres;
    }

}
