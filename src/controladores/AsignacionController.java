package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.BDD.DAO.Conexion;
import controlador.TDALista.LinkedList;
import java.sql.ResultSet;
import java.sql.Statement;
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
    
    public LinkedList<Asignacion> asignacionesdeCursa(Integer id_cursa){
        LinkedList<Asignacion> lista = new LinkedList<Asignacion>();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM asignacion WHERE id_cursa = "+id_cursa;
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Asignacion a = new Asignacion();
                a.setId(rs.getInt(1));
                a.setId_cursa(rs.getInt(2));
                a.setId_tarea(rs.getInt(3)); 
                /*File f = new File("ruta");
                OutputStream ou = new FileOutputStream(f, false);
                rs.getBinaryStream(4).transferTo(ou);*/
                a.setFechaEntrega(rs.getDate(5));
                a.setCalificacion(rs.getDouble(6));
                a.setComentario(rs.getString(7));
                a.setEstado(rs.getString(8));
                lista.add(a);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("Asignaciones en asignaciones de Cursa: \n"+lista.print());
        return lista;
    }
    
    public LinkedList<Asignacion> asignacionesCursaUnidad(Integer id_cursa,Integer unidad){
        LinkedList<Asignacion> lista = new LinkedList<Asignacion>();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM asignacion JOIN tarea ON (id_tarea = tarea.id) WHERE id_cursa = "+id_cursa
                    +" AND unidad = "+unidad;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Asignacion a = new Asignacion();
                a.setId(rs.getInt(1));
                a.setId_cursa(rs.getInt(2));
                a.setId_tarea(rs.getInt(3)); 
                /*File f = new File("ruta");
                OutputStream ou = new FileOutputStream(f, false);
                rs.getBinaryStream(4).transferTo(ou);*/
                a.setFechaEntrega(rs.getDate(5));
                a.setCalificacion(rs.getDouble(6));
                a.setComentario(rs.getString(7));
                a.setEstado(rs.getString(8));
                lista.add(a);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("Asignaciones en asignaciones de Cursa: \n"+lista.print());
        return lista;
    }
}
