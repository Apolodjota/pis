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

    public Administrador(Integer id, String nombres, String apellidos, String nacionalidad, Date fecha_nac, String cedula, String telefono) {
        super(id, nombres, apellidos, nacionalidad, fecha_nac, cedula, telefono);
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
    
    public static void main(String[] args) {
        Administrador administrador = new Administrador(1, "Bayron A", "Jimenez C", "Ecuatoriana", new Date(), "1150", "099");
        AdministradorControlador ac = new AdministradorControlador();
        ac.save(administrador);
    }
    
}
