package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.BDD.DAO.Conexion;
import controlador.TDALista.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import modelo.Asignacion;
import modelo.Cuenta;

/**
 *
 * @author Asus
 */
public class AsignacionController extends AdaptadorDao<Asignacion>{
    private Asignacion asignacion = new Asignacion();
    private LinkedList<Asignacion> asignaciones = new LinkedList<>();
    private Conexion conexion;
    
    public AsignacionController() {
        super(Cuenta.class);
        conexion = new Conexion();
    }
    
    public Integer save(){
        return guardar(asignacion);
    }
    
    public void inicializarAsinacion(Asignacion a){
        String query = "INSERT INTO asignacion(ID_CURSA,ID_TAREA) VALUES (?,?)";
        System.out.println("Sentencia: "+query);
        try {
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.setInt(1, a.getId_cursa());
            stament.setInt(2, a.getId_tarea());
            stament.executeUpdate();
        } catch (SQLException e){
            System.out.println("ERROR GUARDAR Asignacion" + e.getMessage().toString());
        }
    }
    
    public void actualizar(Asignacion asigna) throws Exception{
        modificar(asigna);
    }
    
    public LinkedList<Asignacion> asignacionesCursoUnidad(Integer id_materia,Integer id_docente,String paralelo, Integer unidad){
        //SELECT * FROM asignacion join cursa on (id_cursa = cursa.id) join tarea on (tarea.id = asignacion.id_tarea) where id_materia = 2 and id_docente = 6 and paralelo = 'A' and unidad = 2;
        LinkedList<Asignacion> lista = new LinkedList<Asignacion>();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM asignacion JOIN cursa ON (id_cursa = cursa.id) JOIN tarea ON (tarea.id = asignacion.id_tarea) where"
                    + " id_materia = "+id_materia+" and id_docente = "+id_docente+" and paralelo = '"+paralelo+"' and unidad = "+unidad;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Asignacion a = new Asignacion();
                a.setId(rs.getInt(1));
                a.setId_cursa(rs.getInt(2));
                a.setId_tarea(rs.getInt(3)); 
                InputStream recibido = rs.getBinaryStream(4);
                if(recibido != null){
                    File tempFile = File.createTempFile("Entrega", ".pdf");
                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = recibido.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    a.setArchivo(tempFile);
                }
                a.setFechaEntrega(rs.getDate(5));
                a.setCalificacion(rs.getDouble(6));
                a.setComentario(rs.getString(7));
                a.setEstado(rs.getString(8));
                lista.add(a);
            }
        } catch (Exception e) {
            System.out.println("error asignacionesdelaTarea: " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("Asignaciones en asignaciones de Cursa: \n"+lista.print());
        return lista;
    }
    
    public void actualizarAsignacion(Asignacion asigna)throws Exception{
        try {
            String query = "UPDATE asignacion SET archivo = ?,fechaentrega = ?,comentario = ?,estado = ? where id ="+asigna.getId();
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.setBinaryStream(1, new FileInputStream(asigna.getArchivo()), (int)asigna.getArchivo().length());
            /*String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(asigna.getFechaEntrega());
            stament.setString(2, fecha);*/
            java.sql.Date now = new java.sql.Date(asigna.getFechaEntrega().getTime());
            stament.setDate(2, now);
            stament.setString(3, asigna.getComentario());
            stament.setString(4, asigna.getEstado());
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                conexion.getConnection().close();
                conexion.setConnection(null);
            } catch (Exception e) {
                System.out.println("Error actualizando asignacion: "+e);
            }
        } catch (Exception e) {
            System.out.println("Error actualizando asignacion"+e);
        }
    }
    
    public void actualizarComentario(Asignacion a){
        try {
            String query = "UPDATE asignacion SET comentario = ? where id ="+a.getId();
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.setString(1, a.getComentario());
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                conexion.getConnection().close();
                conexion.setConnection(null);
            } catch (Exception e) {
                System.out.println("Error actualizando asignacion: "+e);
            }
        } catch (Exception e) {
            System.out.println("Error actualizando asignacion"+e);
        }
    }
    
    public void actualizarNota(Asignacion a){
        try {
            String query = "UPDATE asignacion SET calificacion = ?, estado = ? where id = "+a.getId();
            
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.setDouble(1, a.getCalificacion());
            stament.setString(2, a.getEstado());
            System.out.println(query);
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                conexion.getConnection().close();
                conexion.setConnection(null);
            } catch (Exception e) {
                System.out.println("Error actualizando asignacion: "+e);
            }
        } catch (Exception e) {
            System.out.println("Error actualizando asignacion"+e);
        }
    }
    
    public Asignacion buscar(Integer id){
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
                InputStream recibido = rs.getBinaryStream(4);
                if(recibido != null){
                    File tempFile = File.createTempFile("Entrega", ".pdf");
                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = recibido.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    a.setArchivo(tempFile);
                }
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
    
    public LinkedList<Asignacion> asignacionesdelaTarea(Integer id_tarea,Integer unidad){
        LinkedList<Asignacion> lista = new LinkedList<Asignacion>();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM asignacion WHERE id_tarea = "+id_tarea;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Asignacion a = new Asignacion();
                a.setId(rs.getInt(1));
                a.setId_cursa(rs.getInt(2));
                a.setId_tarea(rs.getInt(3)); 
                InputStream recibido = rs.getBinaryStream(4);
                if(recibido != null){
                    File tempFile = File.createTempFile("Entrega", ".pdf");
                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = recibido.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    a.setArchivo(tempFile);
                }
                a.setFechaEntrega(rs.getDate(5));
                a.setCalificacion(rs.getDouble(6));
                a.setComentario(rs.getString(7));
                a.setEstado(rs.getString(8));
                lista.add(a);
            }
        } catch (Exception e) {
            System.out.println("error asignacionesdelaTarea: " + e.getMessage());
            e.printStackTrace();
        }
        //System.out.println("Asignaciones en asignaciones de Cursa: \n"+lista.print());
        return lista;
    }
}
