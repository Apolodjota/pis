package controlador.BDD.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import controlador.TDALista.LinkedList;
import controlador.Utilidades.Utilidades;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Persona;
import vista.Util.Util;

/**
 * Clase adaptadora para los metodos de guardar, modificar, listar y buscar por id desde la Base de datos
 * @author infierno
 */
public class AdaptadorDao<T> implements InterfazDao<T>{
    /**
     * Objeto Conexion
     */
    private Conexion conexion;
    /**
     * Class del modelo a usar
     */
    private Class clazz;
    
    /**
     * Constructor de la clase
     * @param clazz El objeto de la clase del modelo Ejemplo: Persona.class
     */
    public AdaptadorDao(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
    }
    /**
     * Metodo que permite guardar
     * @param obj El objeto del modelo lleno
     * @return La llave primaria generada por el motor de base de datos (se sugiere construir la tabla de base de datos con la generacion de id auto incementable) 
     * @throws Exception Cuando no se puede guardar en la base de datos
     */
    
    public void guardarEyD(T obj) throws Exception {
        //INSERT INTO <TABLA> (..) value (...)
        String query = queryInsertHerencia(obj);
        System.out.println("Select: "+query);
        Integer idGenerado = -1;
        PreparedStatement statement
                = conexion.getConnection().prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        /*ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            idGenerado = generatedKeys.getInt(1);
        }*/
        conexion.getConnection().close();
        conexion.setConnection(null);
        //return idGenerado;
    }
    
    public Integer guardar(T object){
        String query = queryInsert(object);
        System.out.println("Sentencia: "+query);
        Integer idG = -1;
        try {
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                ResultSet result = stament.executeQuery("SELECT "+ object.getClass().getSimpleName().toUpperCase()+"_SEQ.CURRVAL FROM dual");
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
        }
        return idG;
    }
    
    /**
     * Metodo que permite modificar un registro en la base de datos, para modificar se debe primero consultar el Objeto haciendo uso del metodo Obtener
     * @param obj El objeto del modelo a modificar
     * @throws Exception Alguna Excepcion si no modifica
     */
    @Override
    public void modificar(T obj) throws Exception {
        String query = "";
        if (obj.getClass().getSimpleName().equalsIgnoreCase("estudiante") || obj.getClass().getSimpleName().equalsIgnoreCase("docente")){
            System.out.println("1pasa");
            query = queryUpdateHerencia(obj);
        } else {
            query = queryUpdate(obj);
        }
        System.out.println("Sentencia: "+query);
        try {
            PreparedStatement stament = conexion.getConnection().prepareStatement(query);
            stament.executeUpdate();
            try {
                Statement seqStament = conexion.getConnection().createStatement();
                conexion.getConnection().close();
                conexion.setConnection(null);
            } catch (Exception e) {
                System.out.println("PRIMERO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "SEGUNDO");
        }
//        Statement st = conexion.getConnection().createStatement();
//        st.executeUpdate(query);
//        conexion.getConnection().close();
//        conexion.setConnection(null);
    }
    /**
     * Metodo que permite sacar los datos de la base de datos
     * @return Un Objeto de la ListaEnlazada con los datos llenos
     */
    @Override
    public LinkedList<T> listar() {
        LinkedList<T> lista = new LinkedList<>();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM " + clazz.getSimpleName().toLowerCase();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lista.add(llenarObjeto(rs));
                //lista.insertar(llenarObjeto(rs));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    /**
     * Permite obtener un objeto de la base de datos a travez del Id
     * @param id El id a buscar en la base de datos
     * @return El objeto buscado, es null si no esxiste el objeto
     */
    @Override
    public T obtener(Integer id) {
        T data = null;
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "select * from " + clazz.getSimpleName().toLowerCase() + " where id = " + id;
            System.out.println("Sentencia obtener: "+query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data = llenarObjeto(rs);
            }
        } catch (Exception e) {
        }
        return data;
    }

    //--------------ESTO ES DEL CRUD NO MODIFICAR AL MENOS QUE LO AMERITE------
    
    private T llenarObjeto(ResultSet rs) {
        T data = null;
        try {
            data = (T) clazz.getDeclaredConstructor().newInstance();
            for (Field f : clazz.getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }
            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return data;
    }

    private void fijarDatos(Field f, ResultSet rs, T data, String atributo) {
        try {
            Method m = null;

            if (f.getType().getSimpleName().equalsIgnoreCase("String")) {
                m = clazz.getMethod("set" + atributo, String.class);
                m.invoke(data, rs.getString(atributo));
            }
            if (f.getType().getSimpleName().equalsIgnoreCase("Integer")) {
                m = clazz.getMethod("set" + atributo, Integer.class);
                m.invoke(data, rs.getInt(atributo));
            }
            if (f.getType().getSimpleName().equalsIgnoreCase("Double")) {
                m = clazz.getMethod("set" + atributo, Double.class);
                m.invoke(data, rs.getDouble(atributo));
            }
            if (f.getType().getSimpleName().equalsIgnoreCase("Boolean")) {
                m = clazz.getMethod("set" + atributo, Boolean.class);
                m.invoke(data, rs.getBoolean(atributo));
            }
            if (f.getType().getSimpleName().equalsIgnoreCase("Date")) {
                m = clazz.getMethod("set" + atributo, Date.class);
                m.invoke(data, rs.getDate(atributo)); 
            }
            if (f.getType().isEnum()) {
                m = clazz.getMethod("set" + atributo, (Class<Enum>) f.getType());
                m.invoke(data, Enum.valueOf((Class<Enum>) f.getType(), rs.getString(atributo)));
            }
        } catch (Exception e) {
            System.out.println("chiqui error " + e);
        }
    }

    private HashMap<String, Object> obtenerObjeto(T obj) {
        HashMap<String, Object> mapa = new HashMap<>();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }
            }

            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }
               
            }
        } catch (Exception e) {
            System.out.println("No se pudo tener dato");
        }
        return mapa;
    }
    
    private String queryInsertHerencia (T obj) {
        String query = "INSERT INTO ";
        query += "" + clazz.getSimpleName().toLowerCase() + "(id,";
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            query += f.getName() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += ") VALUES (";
        try {
            Field campo = obj.getClass().getSuperclass().getDeclaredField("id");
            campo.setAccessible(true);
            query += campo.get(obj) + ",";
            Field[] fiels = clazz.getDeclaredFields();
            for (Field fi : fiels) {
                fi.setAccessible(true);
                if(fi.get(obj).getClass().getSimpleName().equalsIgnoreCase("Date")){   
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YY");
                //query += '"' + formato.format(entry.getValue()) + '"' + ", ";
                query += "'" + formato.format(fi.get(obj)) + "'" + ", ";
                } else { 
                    query += "'" + fi.get(obj) + "'" + ",";
                }
            }
            query = query.substring(0, query.length() - 1);
            query += ")";
        } catch (Exception ed) {
            System.out.println("ex" + ed.getMessage());
        }
        System.out.println("--> " + query);
        return query;
        
    }
            
        
    
    
    private String queryInsert(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "INSERT INTO " + clazz.getSimpleName().toLowerCase() + " (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            query += entry.getKey() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += ") VALUES (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number") || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                query += entry.getValue() + ", ";
            }
            if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                //SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YY hh:mm:ss");
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YY");
                //query += '"' + formato.format(entry.getValue()) + '"' + ", ";
                query += "'" + formato.format(entry.getValue()) + "'" + ", ";
            }
            if (entry.getValue().getClass().isEnum() || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
                //query += '"' + entry.getValue().toString() + '"' + ", ";
                query += "'" + entry.getValue().toString() + "'" + ", ";
            }
        }
        query = query.substring(0, query.length() - 2);
        query += ")";
        System.out.println("-->" + query);
        return query;
    }
    
    private String queryUpdateHerencia(T obj) throws Exception{
        String query = "UPDATE ";
        try {
            
            query += "" + clazz.getSimpleName().toLowerCase() + " SET ";
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                if(f.get(obj).getClass().getSimpleName().equalsIgnoreCase("Date")){   
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YY");
                //query += '"' + formato.format(entry.getValue()) + '"' + ", ";
                query += f.getName() + " = " +  "'" + formato.format(f.get(obj)) + "'" + ", ";
                } else { 
                    query += f.getName() + " = " + "'" + f.get(obj) + "'" + ",";
                }
            }
            System.out.println("despues");
            query = query.substring(0, query.length() - 1);
            Field campo = obj.getClass().getSuperclass().getDeclaredField("id");
            campo.setAccessible(true);
            query += " WHERE id = " + campo.get(obj);
            //System.out.println("---- > bb --> " + query);
            
        } catch (Exception e) {
            System.out.println("ELLORL" + e.getMessage());
        }
        return query;
    }

    private String queryUpdate(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "UPDATE " + clazz.getSimpleName().toLowerCase() + " SET ";
        Integer id = 0;
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("id")) {
                System.out.println("amtes");
                id = (Integer) entry.getValue();
                System.out.println("despuems");
            } else {
                query += entry.getKey() + " = ";
                if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number") || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                    query += entry.getValue() + ", ";
                }
                if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YY");
                    //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    query += "'" + formato.format(entry.getValue()) + "'" + ", ";
                }
                if (entry.getValue().getClass().isEnum() || entry.getValue().getClass().getSimpleName().equalsIgnoreCase("String")) {
                    query += "'" + entry.getValue().toString() + "'" + ", ";
                }
            }

        }

        query += "";

        query = query.substring(0, query.length() - 2);
        query += " WHERE id = " + id;
        return query;
    }
}
