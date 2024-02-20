/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.BDD.DAO.Conexion;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class EstudianteControlador extends AdaptadorDao<Estudiante> {

    private LinkedList<Estudiante> estudiantes = new LinkedList<Estudiante>();
    private Estudiante estudiante = new Estudiante();
    private Conexion conexion =new Conexion();
    private Connection con = conexion.getConnection();

    public EstudianteControlador() {
        super(Estudiante.class);
    }

    public LinkedList<Estudiante> getEstudiantes() {
        if (estudiantes.isEmpty()) {
            estudiantes = listarEyD();
        }
        return estudiantes;
    }

    public void setEstudiantes(LinkedList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudiante() {
        if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void guardar() throws Exception {
        guardarEyD(estudiante);
    }

    public void update() throws Exception {
        modificar(estudiante);
    }

    private void intercambio(Estudiante e[], int i, int j) {
        Estudiante aux = e[j];
        e[j] = e[i];
        e[i] = aux;
    }

    public LinkedList<Estudiante> ordenarQuickSort(LinkedList<Estudiante> lista, Integer type, String atribute) throws VacioException {
        Estudiante estudent[] = lista.toArray();
        LinkedList<Estudiante> a = ordenarQuickSort(estudent, type, atribute, 0, lista.getSize() - 1);
        return a;
    }

    public ResultSet segundaSentencia(Integer id_estudiante, Integer id_periodo) {
        String query2 = "SELECT * FROM matricula WHERE id_estudiante = " + id_estudiante + " AND id_periodoacademico = " + id_periodo;//Ojo esto ultimo
        //System.out.println(query2);
        try {
            return conexion.getConnection().createStatement().executeQuery(query2);
        } catch (SQLException ex) {
            System.out.println("Error al filtrar matríulas de estudiantes: "+ex);
        }
        return null;
    }

    public LinkedList<Estudiante> getEstudiantesNoMatriculados() {
        LinkedList<Estudiante> lista = new LinkedList<>();
        try {
            Statement stmt = conexion.getConnection().createStatement();//Poner conexion arriba
            String query = "SELECT * FROM estudiante JOIN persona using (id)";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Boolean estaMatriculado = !segundaSentencia(rs.getInt(1), new PeriodoController().getPerioVigente().getId()).next();
                if (estaMatriculado) {
                    Estudiante e = new Estudiante();
                    e.setId(rs.getInt(1));
                    e.setTitulo_bachiller(rs.getString(2));
                    e.setTrabaja(rs.getString(3));
                    e.setId_rol(rs.getInt(4));
                    e.setCedula(rs.getString(5));
                    e.setNombres(rs.getString(6));
                    e.setApellidos(rs.getString(7));
                    e.setGenero(rs.getString(8));
                    e.setFechaNacimiento(rs.getDate(9));
                    e.setTelefonoCasa(rs.getString(10));
                    e.setTelefonoCelular(rs.getString(11));
                    e.setDireccionResidencia(rs.getString(12));
                    lista.add(e);
                }
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return lista;
    }

    public Estudiante obtenerEstudianteporIDcursa(Integer id_cursa){
        Estudiante e = new Estudiante();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM persona JOIN matricula ON(persona.id = id_estudiante) join cursa on (matricula.id = id_matricula) where"
                    + " cursa.id = "+id_cursa;
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                e.setId(rs.getInt(1));
                e.setNombres(rs.getString(4));
                e.setApellidos(rs.getString(5));
                /*e.setTitulo_bachiller(rs.getString(2));
                e.setTrabaja(rs.getString(3));
                e.setId_rol(rs.getInt(5));
                e.setCedula(rs.getString(6));
                e.setGenero(rs.getString(9));
                e.setFechaNacimiento(rs.getDate(10));
                e.setTelefonoCasa(rs.getString(11));
                e.setTelefonoCelular(rs.getString(12));
                e.setDireccionResidencia(rs.getString(13));*/
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return e;
    }
    
    public Estudiante buscarEstudiante(Integer id) {
        Estudiante e = new Estudiante();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM estudiante JOIN persona on (estudiante.id = " + id + " and persona.id = " + id + ")";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                e.setId(rs.getInt(1));
                e.setTitulo_bachiller(rs.getString(2));
                e.setTrabaja(rs.getString(3));
                e.setId_rol(rs.getInt(5));
                e.setCedula(rs.getString(6));
                e.setNombres(rs.getString(7));
                e.setApellidos(rs.getString(8));
                e.setGenero(rs.getString(9));
                e.setFechaNacimiento(rs.getDate(10));
                e.setTelefonoCasa(rs.getString(11));
                e.setTelefonoCelular(rs.getString(12));
                e.setDireccionResidencia(rs.getString(13));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return e;
    }

    public LinkedList<Estudiante> listarEyD() {
        LinkedList<Estudiante> lista = new LinkedList<>();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM estudiante JOIN persona using (id)";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt(1));
                e.setTitulo_bachiller(rs.getString(2));
                e.setTrabaja(rs.getString(3));
                e.setId_rol(rs.getInt(4));
                e.setCedula(rs.getString(5));
                e.setNombres(rs.getString(6));
                e.setApellidos(rs.getString(7));
                e.setGenero(rs.getString(8));
                e.setFechaNacimiento(rs.getDate(9));
                e.setTelefonoCasa(rs.getString(10));
                e.setTelefonoCelular(rs.getString(11));
                e.setDireccionResidencia(rs.getString(12));
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return lista;
    }

    public LinkedList<Estudiante> ordenarQuickSort(Estudiante estudiante[], Integer type, String atribute, Integer primero, Integer ultimo) throws VacioException {
        LinkedList<Estudiante> es = new LinkedList<Estudiante>();
        int i, j, central;
        Estudiante pivote;
        central = (primero + ultimo) / 2;
        pivote = estudiante[central];
        i = primero;
        j = ultimo;
        do {
            while (estudiante[i].comparar(pivote, type, atribute)) {

                i++;
            }
            while (estudiante[j].comparar(pivote, type, atribute, 1)) {
                j--;
            }
            if (i <= j) {
                intercambio(estudiante, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuickSort(estudiante, type, atribute, primero, j);
        }

        if (i < ultimo) {
            ordenarQuickSort(estudiante, type, atribute, i, ultimo);
        }
        es = es.toList(estudiante);
        return es;
    }

    public LinkedList<Estudiante> buscarNombres(LinkedList<Estudiante> lista, String text, String nombre) throws Exception {
        LinkedList<Estudiante> lo = this.ordenarQuickSort(lista, 0, text);
        Estudiante[] e = lo.toArray();
        LinkedList<Estudiante> result = new LinkedList<Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getNombres().equalsIgnoreCase(nombre)) {
                result.add(e[i]);
            }
        }
        return result;
    }

    public LinkedList<Estudiante> buscarApellidos(LinkedList<Estudiante> lista, String text, String apellidos) throws Exception {
        LinkedList<Estudiante> lo = this.ordenarQuickSort(lista, 0, text);
        Estudiante[] e = lo.toArray();
        LinkedList<Estudiante> result = new LinkedList<Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getNombres().equalsIgnoreCase(apellidos)) {
                result.add(e[i]);
            }
        }
        return result;
    }

    public LinkedList<Estudiante> buscarTituloBachiller(LinkedList<Estudiante> lista, String text, String titulo) throws Exception {
        LinkedList<Estudiante> lo = this.ordenarQuickSort(lista, 0, text);
        Estudiante[] e = lo.toArray();
        LinkedList<Estudiante> result = new LinkedList<Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getTitulo_bachiller().equalsIgnoreCase(titulo)) {
                result.add(e[i]);
            }
        }
        return result;
    }

    public Estudiante BusquedaID(LinkedList<Estudiante> lista, Integer id, String metodo) throws VacioException {
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Estudiante[] estu = lista.toArray();
        Estudiante estudianteHallado = new Estudiante();
        int inicio = 0;
        int fin = estu.length - 1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin) / 2;
            if (metodo.equalsIgnoreCase("id")) {
                if (estu[medio].getId().intValue() == id.intValue()) {
                    band = true;
                    estudianteHallado = estu[medio];
                } else {
                    if (estu[medio].getId().intValue() > id.intValue()) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }
        }
        return estudianteHallado;
    }

    public Estudiante BusquedaCedula(LinkedList<Estudiante> lista, String dni, String metodo) throws VacioException {
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Estudiante[] estu = lista.toArray();
        Estudiante estudianteHallado = new Estudiante();
        int inicio = 0;
        int fin = estu.length - 1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin) / 2;
            if (metodo.equalsIgnoreCase("cedula")) {
                if (estu[medio].getCedula().equalsIgnoreCase(dni)) {
                    band = true;
                    estudianteHallado = estu[medio];
                } else {
                    if (estu[medio].getCedula().compareToIgnoreCase(dni) > 0) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }
        }
        return estudianteHallado;
    }
}
