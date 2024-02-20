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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.Docente;

/**
 *
 * @author sakotaz
 */
public class DocenteControlador extends AdaptadorDao<Docente>{
    private LinkedList <Docente> docentes = new LinkedList <Docente>();
    private Docente docente = new Docente();
    private Conexion conexion =new Conexion();
    
    public DocenteControlador() {
        super(Docente.class);
    }

    public LinkedList <Docente> getDocentes() {
        if (docentes.isEmpty())
            docentes = listarD();
        return docentes;
    }

    public void setDocentes(LinkedList <Docente> estudiantes) {
        this.docentes = estudiantes;
    }

    public Docente getDocente() {
        if (docente == null)
            docente = new Docente();
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    public void guardar() throws Exception{
        guardarEyD(docente);
    }
    
    public void update() throws Exception{
        modificar(docente);
    }
    
    public LinkedList <Docente> listarD (){
        LinkedList <Docente> lista = new LinkedList<>();
        try {
            Statement stmt = new Conexion().getConnection().createStatement();
            String query = "SELECT * FROM docente JOIN persona using (id)";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Docente d = new Docente();
                d.setId(rs.getInt(1));
                d.setFecha_inicio_docencia(rs.getDate(2));
                d.setTitulo_tercerNivel(rs.getString(3));
                d.setTitulo_cuartoNivel(rs.getString(4));
                d.setId_rol(rs.getInt(5));
                d.setCedula(rs.getString(6));
                d.setNombres(rs.getString(7));
                d.setApellidos(rs.getString(8));
                d.setGenero(rs.getString(9));
                d.setFechaNacimiento(rs.getDate(10));
                d.setTelefonoCasa(rs.getString(11));
                d.setTelefonoCelular(rs.getString(12));
                d.setDireccionResidencia(rs.getString(13));
                lista.add(d);
            }
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return lista;
    }
    public Docente buscarDocente(Integer id) {
        Docente docente = new Docente();
        try {
            Statement stmt = conexion.getConnection().createStatement();
            String query = "SELECT * FROM docente JOIN persona on (docente.id = " + id + " and persona.id = " + id + ")";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                docente.setId(rs.getInt(1));
                docente.setFecha_inicio_docencia(rs.getDate(2));
                docente.setTitulo_tercerNivel(rs.getString(3));
                docente.setTitulo_cuartoNivel(rs.getString(4));
                docente.setId_rol(rs.getInt(6));
                docente.setCedula(rs.getString(7));
                docente.setNombres(rs.getString(8));
                docente.setApellidos(rs.getString(9));
                docente.setGenero(rs.getString(10));
                docente.setFechaNacimiento(rs.getDate(11));
                docente.setTelefonoCasa(rs.getString(12));
                docente.setTelefonoCelular(rs.getString(13));
                docente.setDireccionResidencia(rs.getString(14));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return docente;
    }
    private void intercambio(Docente d[], int i, int j){
        Docente aux = d[j];
        d[j] = d[i];
        d[i] = aux;
    }  
    
    public LinkedList <Docente> ordenarQuickSort (LinkedList <Docente> lista, Integer type, String atribute) throws VacioException{
        Docente docent[] = lista.toArray();
        LinkedList <Docente> d = ordenarQuickSort(docent, type, atribute, 0, lista.getSize()-1);
        return d;
    }
    
    public LinkedList <Docente> ordenarQuickSort(Docente docentes[], Integer type, String atribute, Integer primero, Integer ultimo) throws VacioException{
        LinkedList<Docente> dc = new LinkedList<Docente>();
        int i, j, central;
        Docente pivote;
        central = (primero + ultimo) / 2;
        pivote = docentes[central];
        i = primero;
        j = ultimo;
        do {
            while (docentes[i].comparar(pivote, type, atribute)) {
                i++;
            }
            while (docentes[j].comparar(pivote, type, atribute, 1)) {
                j--;
            }
            if (i <= j) {
                intercambio(docentes, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuickSort(docentes, type, atribute, primero, j);
        }
        if (i < ultimo) {
            ordenarQuickSort(docentes, type, atribute, i, ultimo);
        }
        dc = dc.toList(docentes);
        return dc;
    }
    
    public LinkedList <Docente> buscarNombres(LinkedList <Docente> lista, String text, String nombre) throws Exception {
        LinkedList <Docente> lo = this.ordenarQuickSort(lista,0 , text);
        Docente[] d = lo.toArray();
        LinkedList <Docente> result = new LinkedList <Docente>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (d[i].getNombres().equalsIgnoreCase(nombre)) {
                result.add(d[i]);
            }
        }
        return result;
    }
    
    public LinkedList <Docente> buscarApellidos(LinkedList <Docente> lista, String text, String apellidos) throws Exception {
        LinkedList <Docente> lo = this.ordenarQuickSort(lista,0 , text);
        Docente[] d = lo.toArray();
        LinkedList <Docente> result = new LinkedList <Docente>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (d[i].getApellidos().equalsIgnoreCase(apellidos)){
                result.add(d[i]);
            }
        }
        return result;
    }
    
    public LinkedList <Docente> buscarTituloTercerN (LinkedList <Docente> lista, String text, String titulo) throws Exception {
        LinkedList <Docente> lo = this.ordenarQuickSort(lista,0 , text);
        Docente[] d = lo.toArray();
        LinkedList <Docente> result = new LinkedList <Docente>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (d[i].getTitulo_tercerNivel().equalsIgnoreCase(titulo)){
                result.add(d[i]);
            }
        }
        return result;
    }
    
    public Docente BusquedaID (LinkedList <Docente> lista, Integer id, String metodo) throws VacioException{
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Docente [] docen = lista.toArray();
        Docente docenteHallado = new Docente();
        int inicio = 0;
        int fin = docen.length-1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin)/2;
            if (metodo.equalsIgnoreCase("id")){
                if (docen[medio].getId().intValue() == id.intValue()) {
                    band = true;
                    docenteHallado = docen[medio];
                } else {
                    if (docen[medio].getId().intValue() > id.intValue()) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }          
        }
        return docenteHallado;
    }
    
    public Docente BusquedaCedula (LinkedList <Docente> lista, String cedula, String metodo) throws VacioException{
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Docente [] docen = lista.toArray();
        Docente docenteHallado = new Docente();
        int inicio = 0;
        int fin = docen.length-1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin)/2;
            if (metodo.equalsIgnoreCase("cedula")){
                if (docen[medio].getCedula().equalsIgnoreCase(cedula)) {
                    band = true;
                    docenteHallado = docen[medio];
                } else {
                    if (docen[medio].getCedula().compareToIgnoreCase(cedula) > 0) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }          
        }
        return docenteHallado;
    }

}
