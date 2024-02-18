/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Tarea;

/**
 *
 * @author apolo
 */
public class TareaController extends AdaptadorDao<Tarea>{
    private Tarea tarea = new Tarea();
    private LinkedList<Tarea> tareas = new LinkedList<>();
    
    public TareaController() {
        super(Tarea.class);
    }

    public Integer guardar() {
        return guardar(tarea);
    }

    public void update(Tarea t) throws Exception{
        modificar(t);
    }
    
    public Tarea buscarTarea(Integer id){
        return obtener(id);
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
