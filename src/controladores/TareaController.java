/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Tarea;

/**
 *
 * @author apolo
 */
public class TareaController extends DataAccesObject<Tarea>{
    private Tarea tarea = new Tarea();
    private LinkedList<Tarea> tareas = new LinkedList<>();
    
    public TareaController() {
        super(Tarea.class);
    }

    public Boolean guardar() {
        getTarea().setId(generated_id());
        return save(getTarea());
    }

    public Boolean update(int fila) {
        return update(getTarea(), fila);
    }

    public LinkedList<Tarea> mergeSort(LinkedList<Tarea> lista, Integer type, String field) {
        Tarea[] m = lista.toArray();
        mergeSort(m, 0, m.length - 1, field, type);
        return lista = lista.toList(m);
    }

    private void mergeSort(Tarea[] m, Integer ini, Integer fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(m, ini, medio, field, type);
            mergeSort(m, medio + 1, fin, field, type);
            merge(m, ini, medio, fin, field, type);
        }
    }

    private void merge(Tarea[] m, Integer ini, Integer medio, Integer fin, String field, Integer type) {
        Integer izq = ini;
        Integer der = medio + 1;
        Integer k = 0;
        Integer n = fin - ini + 1;
        Tarea[] result = new Tarea[n];
        while (izq <= medio && der <= fin) {
            if (m[izq].comparar(m[der], type, field)) {
                result[k] = m[izq];
                izq++;
            } else {
                result[k] = m[der];
                der++;
            }
            k++;
        }
        while (izq <= medio) {
            result[k] = m[izq];
            izq++;
            k++;
        }
        while (der <= fin) {
            result[k] = m[der];
            der++;
            k++;
        }
        for (k = 0; k < n; k++) {
            m[ini + k] = result[k];
        }
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
