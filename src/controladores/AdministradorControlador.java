
package controladores;


import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Administrador;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class AdministradorControlador extends DataAccesObject <Administrador>{
    private LinkedList <Administrador> administradores = new LinkedList <Administrador>();
    private Administrador administrador = new Administrador();

    public AdministradorControlador() {
        super(Administrador.class);
    }

    public LinkedList <Administrador> getAdministradores() {
        if (administradores.isEmpty())
            administradores = listall();
        return administradores;
    }

    public void setAdministradores(LinkedList <Administrador> administradores) {
        this.administradores = administradores;
    }

    public Administrador getAdministrador() {
        if (administrador == null)
            administrador = new Administrador();
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    
    public Boolean guardar(){
        administrador.setId(generated_id());
        return save(administrador);
    }
    
    public Boolean modificar(Integer index){
        return update(administrador, index);
    }
    
}
