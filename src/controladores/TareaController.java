package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.BDD.DAO.Conexion;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Asignacion;
import modelo.Tarea;

/**
 *
 * @author apolo
 */
public class TareaController extends AdaptadorDao<Tarea>{
    private Tarea tarea = new Tarea();
    private LinkedList<Tarea> tareas = new LinkedList<>();
    private Conexion conexion;
    
    public TareaController() {
        super(Tarea.class);
        conexion = new Conexion();
    }

    public Integer guardar() {
        return guardar(tarea);
    }

    public Integer guardarTarea(){
        String query = "INSERT INTO tarea(UNIDAD,TITULO,ARCHIVO,FECHAASIGNACION,FECHAENTREGA,DESCRIPCION) VALUES (?,?,?,?,?,?)";
        System.out.println("Sentencia: "+query);
        Integer idG = -1;
        try {
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.setInt(1, tarea.getUnidad());
            stament.setString(2, tarea.getTitulo());
            if(tarea.getArchivo() != null)
                stament.setBinaryStream(3, new FileInputStream(tarea.getArchivo()), (int)tarea.getArchivo().length());
            else
                stament.setBinaryStream(3, null);
            java.sql.Date asignacion = new java.sql.Date(tarea.getFechaAsignacion().getTime());
            java.sql.Date entrega = new java.sql.Date(tarea.getFechaEntrega().getTime());
            stament.setDate(4, asignacion);
            stament.setDate(5, entrega);
            stament.setString(6, tarea.getDescripcion());
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                ResultSet result = stament.executeQuery("SELECT TAREA_SEQ.CURRVAL FROM dual");
                if (result.next()){
                    idG = result.getInt(1);
                }
                conexion.getConnection().close();
                conexion.setConnection(null);
            } catch (SQLException ex){
                System.out.println("ERROR GUARDARP" + ex.getMessage());
            }
        } catch (SQLException e){
            System.out.println("ERROO GUARDAR P2" + e.getMessage().toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Error en archivo: "+ex);;
        }
        return idG;
    }
    
    
    public void actualizar(){
            try {
                String query = "UPDATE tarea SET UNIDAD = ?,TITULO = ?,ARCHIVO = ?,FECHAENTREGA = ?,DESCRIPCION = ? WHERE id = "+tarea.getId();
                PreparedStatement stament = conexion.getConnection().prepareStatement(query);
                stament.setInt(1, tarea.getUnidad());
                stament.setString(2, tarea.getTitulo());
                if(tarea.getArchivo() != null)
                    stament.setBinaryStream(3, new FileInputStream(tarea.getArchivo()), (int)tarea.getArchivo().length());
                else
                    stament.setBinaryStream(3, null);
                java.sql.Date now = new java.sql.Date(tarea.getFechaEntrega().getTime());
                stament.setDate(4, now);
                stament.setString(5, tarea.getDescripcion());
                stament.executeUpdate();
                try {
                    Statement seqStament = conexion.getConnection().createStatement();
                    conexion.getConnection().close();
                    conexion.setConnection(null);
                } catch (Exception e) {
                    System.out.println("Error actualizando tarea: "+e);
                }
            } catch (SQLException ex) {
                System.out.println("Error actualizando asignacion: "+ex);
            } catch (FileNotFoundException ex) {
            System.out.println("Error actualizando asignacion: "+ex);
        }
    }
    
    public void update(Tarea t) throws Exception{
        modificar(t);
    }
    
    public Tarea buscarTarea(Integer id){
        return obtener(id);
    }
    
    public Tarea obtenerTcompleta(Integer id){
        Tarea t = new Tarea();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM tarea WHERE id = "+id;
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                t.setId(rs.getInt(1));
                t.setUnidad(rs.getInt(2));
                t.setTitulo(rs.getString(3));
                InputStream recibido = rs.getBinaryStream(4);
                if(recibido != null){
                    File tempFile = File.createTempFile("tempfile", ".pdf");
                    FileOutputStream outputStream = new FileOutputStream(tempFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = recibido.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    t.setArchivo(tempFile);
                }
                /*OutputStream ou = new FileOutputStream(f, false);
                rs.getBinaryStream(4).transferTo(ou);*/
                t.setFechaAsignacion(rs.getDate(5));
                t.setFechaEntrega(rs.getDate(6));
                t.setDescripcion(rs.getString(7));
                //
            }
        } catch (Exception e) {
            System.out.println("error obteniendo tarea: " + e.getMessage());
            e.printStackTrace();
        }
        return t;
    } 
        
    public LinkedList<Tarea> tareasCursoParUnidad(Integer id_docente, Integer id_materia, String paralelo,Integer unidad){
        LinkedList<Tarea> lista = new LinkedList<>();
        try {
                Statement stmt = conexion.getConnection().createStatement();
                String query = "SELECT DISTINCT tarea.id FROM cursa join asignacion on (id_cursa = cursa.id) join tarea on (id_tarea = tarea.id) WHERE id_docente = "+
                    id_docente+" AND id_materia = "+id_materia+" AND paralelo = '"+paralelo+"'";
                System.out.println(query);
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) {
                    Tarea t = obtenerTcompleta(rs.getInt(1));
                    if(t.getUnidad() == unidad){
                        lista.add(t);
                    }else if(unidad == 0)
                        lista.add(t);
                    }
            }catch (Exception e) {
                System.out.println("error tareasdelCursoParalelo: " + e.getMessage());
            }
        return lista;
    }
    
    public LinkedList<Tarea> quickSort(LinkedList<Tarea> lista, Integer type, String field) {
        Tarea[] m = lista.toArray();
        Integer fin = m.length - 1;
        quickSort(m, type, field, 0, fin);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Tarea[] m, Integer type, String field, Integer inicio, Integer fin) {
        
        if (inicio >= fin) return;
        
        Tarea pivote = m[inicio];
        Integer elemIzq = inicio + 1;
        Integer elemDer = fin;
        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && m[elemIzq].comparar(pivote, type, field)) {
                elemIzq++;
            }
            while (elemDer > inicio && !m[elemDer].comparar(pivote, type, field)) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                Tarea temp = m[elemIzq];
                m[elemIzq] = m[elemDer];
                m[elemDer] = temp;
            }
        }
        if (elemDer > inicio) {
            Tarea temp = m[inicio];
            m[inicio] = m[elemDer];
            m[elemDer] = temp;
        }
        quickSort(m, type, field, inicio, elemDer - 1);
        quickSort(m, type, field, elemDer + 1, fin);
    }

    public LinkedList<Tarea> buscar(LinkedList<Tarea> lista, String text) throws VacioException, Exception {
        LinkedList<Tarea> lo = this.quickSort(lista, 0, "id");
        Tarea[] m = lo.toArray();
        LinkedList<Tarea> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId().toString().toLowerCase().contains(text.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
 
    /**
     * @return the tarea
     */
    public Tarea getTarea() {
        return tarea;
    }

    /**
     * @param tarea the tarea to set
     */
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    /**
     * @return the tareas
     */
    public LinkedList<Tarea> getTareas() {
        return tareas;
    }

    /**
     * @param tareas the tareas to set
     */
    public void setTareas(LinkedList<Tarea> tareas) {
        this.tareas = tareas;
    }
}
