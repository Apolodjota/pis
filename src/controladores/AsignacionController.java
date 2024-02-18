package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import modelo.Asignacion;
import modelo.Cuenta;

/**
 *
 * @author Asus
 */
public class AsignacionController extends AdaptadorDao<Asignacion>{
    private Asignacion asignacion = new Asignacion();
    private LinkedList<Asignacion> asignaciones = new LinkedList<>();
    
    public AsignacionController() {
        super(Cuenta.class);
    }
    
    private Integer save(){
        return guardar(asignacion);
    }
    
    private void actualizar(Asignacion asigna) throws Exception{
        modificar(asigna);
    }
    
    private Asignacion buscar(Integer id){
        return obtener(id);
    }
}
