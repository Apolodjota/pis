package modelo;
import controladores.AdministradorControlador;
import java.util.Date;


/**
 *
 * @author alexg
 */
public class Administrador extends Persona{
    private Integer id;

    public Administrador() {
    }

    public Administrador(Integer id, Integer id_rol, String nombres, String apellidos, Date fechaNacimiento, String cedula, String telefonoCasa, String telefonoCelular, String genero, String direccionResidencia) {
        super(id, id_rol, nombres, apellidos, fechaNacimiento, cedula, telefonoCasa, telefonoCelular, genero, direccionResidencia);
        this.id = id;
    }

    @Override
    public String toString() {
        return super.getNombres() + " " + super.getApellidos();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
}
